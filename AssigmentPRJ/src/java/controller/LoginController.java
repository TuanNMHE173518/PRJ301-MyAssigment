/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDBContext;
import dal.TokensDBContext;
import entity.Account;
import entity.Tokens;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;
import util.HashHelper;


/**
 *
 * @author ADMIN
 */
public class LoginController extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("view/login.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        AccountDBContext accDB = new AccountDBContext();
        HashHelper hash = new HashHelper();
        String hashPass = hash.hasPassword(password);
        Account account = accDB.getAccountByUsernameAndPassword(username, hashPass);
        String token =  UUID.randomUUID().toString();
        Date expirationtime = new Date(System.currentTimeMillis() + 7*24*3600*1000);
        
        Cookie c_username = new Cookie("username", username);
//        Cookie c_password = new Cookie("password", password);
        Cookie c_remember = new Cookie("remember", remember);
        Cookie c_tokens = new Cookie("token", token);
        if (remember != null) {
            c_username.setMaxAge(3600 * 24 * 7);
            c_tokens.setMaxAge(3600 * 24 * 7);
            c_remember.setMaxAge(3600 * 24 * 7);
        } else {
            c_username.setMaxAge(-1);
            c_tokens.setMaxAge(-1);
            c_remember.setMaxAge(-1);
        }
        response.addCookie(c_remember);
        response.addCookie(c_tokens);
        response.addCookie(c_username);
        if (account != null) {
            HttpSession session = request.getSession();
            Tokens t = new Tokens();
            t.setToken(token);
            t.setExpirationtime(expirationtime);
            t.setAccount(account);
            
            TokensDBContext tokenDB = new TokensDBContext();
            tokenDB.insertToken(t);
            
            
            session.setAttribute("account", account);
            response.sendRedirect("home");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("loginError", true);
            response.sendRedirect("login");

        }

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
