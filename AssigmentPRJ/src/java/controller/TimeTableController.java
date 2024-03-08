/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import controller.base.BaseRBACController;
import controller.base.BaseRequiredAuthentication;
import dal.LessionDBContext;
import dal.TimeSlotDBContext;
import entity.Account;
import entity.Lession;
import entity.Role;
import entity.TimeSlot;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.TimeHelper;

/**
 *
 * @author ADMIN
 */
public class TimeTableController extends BaseRBACController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account, ArrayList<Role> roles)
            throws ServletException, IOException {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            TimeSlotDBContext tDb = new TimeSlotDBContext();
            ArrayList<TimeSlot> timeslots = tDb.list();
            TimeHelper helper = new TimeHelper();
            String raw_year = request.getParameter("year");
            String week = request.getParameter("week");

            ArrayList<String> weeks = new ArrayList<>();
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            int year = 0;
            if (raw_year == null) {
                if (week == null) {
                    year = currentYear;
                    week = helper.getCurrentWeek();
                    weeks = helper.generateWeeks(year);
                }
            } else {
                
                year = Integer.parseInt(raw_year);
                if(year == currentYear && week == null){
                    week = helper.getCurrentWeek();
                }
                if (week == null) {
                    week = helper.generateFirstWeek(year);
                }

                weeks = helper.generateWeeks(year);
            }

            java.util.Date from = dateFormat.parse(week.split(" ")[0]);
            java.util.Date to = dateFormat.parse(week.split(" ")[2]);
            request.setAttribute("dates", helper.toList(helper.convertUtilToSql(from), helper.convertUtilToSql(to)));

            int lecid = Integer.parseInt(request.getParameter("id"));
            LessionDBContext lessDB = new LessionDBContext();
            ArrayList<Lession> lessions = lessDB.getLessionBy(lecid, helper.convertUtilToSql(from), helper.convertUtilToSql(to));

            java.util.Date currentDate = Calendar.getInstance().getTime();
            request.setAttribute("currentDate", helper.convertUtilToSql(currentDate));
            
            ArrayList<Integer> years = helper.generateYears();
            request.setAttribute("year", year);
            request.setAttribute("week", week);
            request.setAttribute("weeks", weeks);
            request.setAttribute("currentWeek", helper.getCurrentWeek());
            request.setAttribute("currentYear", currentYear);
            request.setAttribute("years", years);
            request.setAttribute("lessions", lessions);
            request.setAttribute("timeslots", timeslots);
            request.getRequestDispatcher("../view/lecture/timetable.jsp").forward(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account, ArrayList<Role> roles)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
