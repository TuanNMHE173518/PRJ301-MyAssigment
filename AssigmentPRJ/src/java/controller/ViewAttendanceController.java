/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import controller.base.BaseRBACController;
import dal.EnrollmentDBContext;
import dal.LessionDBContext;
import entity.Account;
import entity.Attendance;
import entity.Enrollment;
import entity.Role;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import util.TimeHelper;

/**
 *
 * @author ADMIN
 */
public class ViewAttendanceController extends BaseRBACController {

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
        TimeHelper timehelp = new TimeHelper();
        
        int sid = Integer.parseInt(request.getParameter("id"));
        EnrollmentDBContext enDb = new EnrollmentDBContext();
        LessionDBContext lessDb = new LessionDBContext();
        ArrayList<Enrollment> enrolls = enDb.getEnrollmentByStudentID(sid);
        String suid = request.getParameter("suid");
        ArrayList<Attendance> atts = new ArrayList<>();
        if (suid != null) {
            atts = lessDb.getAttendanceByStudentIdAndSubject(sid, suid);
        }

        int absent = 0;
        for (Attendance att : atts) {
            if (att.getId() != 0 && att.isIspresent() == false) {
                absent++;
            }
        }

        try {
            if (!atts.isEmpty()) {
                float percent = Math.round(((float)absent / atts.size()) * 100);
                request.setAttribute("percent", percent);
            }
        } catch (NumberFormatException e) {
        }
        ArrayList<String> days = new ArrayList<>();
        for (int i = 0; i < atts.size(); i++) {
            
            String d = timehelp.getDayofWeek( atts.get(i).getLession().getDate());
            days.add(d);
            
        }
        
        request.setAttribute("days", days);
        request.setAttribute("absent", absent);
        request.setAttribute("atts", atts);
        request.setAttribute("enrolls", enrolls);
        request.getRequestDispatcher("../view/student/viewattend.jsp").forward(request, response);

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
