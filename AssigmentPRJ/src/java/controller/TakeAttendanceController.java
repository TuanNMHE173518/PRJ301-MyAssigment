/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import controller.base.BaseRBACController;
import controller.base.BaseRequiredAuthentication;
import dal.LessionDBContext;
import dal.StudentDBContext;
import entity.Account;
import entity.Attendance;
import entity.Lession;
import entity.Role;
import entity.Student;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class TakeAttendanceController extends BaseRBACController {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @param account
     * @param roles
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account,ArrayList<Role> roles)
    throws ServletException, IOException {
        String lesid = request.getParameter("lesid");
        LessionDBContext lessDB = new LessionDBContext();
        ArrayList<Attendance> atts = lessDB.getAttendanceByLession(lesid);
        
        
        String group = request.getParameter("group");
        request.setAttribute("group", group);
        request.setAttribute("atts", atts);
        request.getRequestDispatcher("../view/lecture/takeatt.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @param account
     * @param roles
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account,ArrayList<Role> roles)
    throws ServletException, IOException {
        String lesid = request.getParameter("lesid");
        LessionDBContext lesDb = new LessionDBContext();
        StudentDBContext stuDb = new StudentDBContext();
        Lession les = new Lession();
        les.setId(lesid);
        ArrayList<Student> students = stuDb.getStudentByLession(lesid);
        ArrayList<Attendance> atts = new ArrayList<>();
        
        
        for (Student s : students) {
            Attendance a = new Attendance();
            a.setLession(les);
            a.setStudent(s);
            a.setIspresent(request.getParameter("ispresent"+s.getId()).equals("attended"));
            a.setDescription(request.getParameter("description"+s.getId()));
            atts.add(a);
        }
        
        if(!request.getParameter("lesattend").equals("true")){
            lesDb.takeAttendanceWhenAbsent(lesid, atts);
            response.sendRedirect("timetable?id="+account.getLecture().getId());
        }else{
            lesDb.takeAttendanceWhenAttend(lesid, atts);
            response.sendRedirect("timetable?id="+account.getLecture().getId());
        }
        
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
