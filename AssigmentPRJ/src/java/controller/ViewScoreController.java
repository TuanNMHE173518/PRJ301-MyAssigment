/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import controller.base.BaseRequiredAuthentication;
import dal.EnrollmentDBContext;
import dal.GradeDBContext;
import entity.Account;
import entity.Enrollment;
import entity.Grade;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class ViewScoreController extends BaseRequiredAuthentication {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        int sid = Integer.parseInt(request.getParameter("id"));
        String suid = request.getParameter("suid");
        GradeDBContext grDb = new GradeDBContext();
        ArrayList<Grade> grades = new ArrayList<>();
        if(suid != null){
            grades = grDb.getGradeByStudentIdAndSuject(sid, suid);
        }
        
        EnrollmentDBContext enrollDb = new EnrollmentDBContext();
        ArrayList<Enrollment> enrolls = enrollDb.getEnrollmentByStudentID(sid);
        
        float average = grDb.getAverageScore(sid, suid);
        request.setAttribute("average", average);
        request.setAttribute("grades", grades);
        request.setAttribute("enrolls", enrolls);
        request.getRequestDispatcher("../view/student/score.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account)
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
