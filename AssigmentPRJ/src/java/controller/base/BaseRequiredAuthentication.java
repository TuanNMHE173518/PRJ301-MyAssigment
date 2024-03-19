/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.base;

import dal.AccountDBContext;
import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import util.HashHelper;

/**
 *
 * @author ADMIN
 */
public abstract class BaseRequiredAuthentication extends HttpServlet{
    
    private Account getAuthentication(HttpServletRequest req){
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        if(account == null){
            Cookie[] cookies = req.getCookies();
            if(cookies != null){
                String user = null;
                String token = null;
                for (Cookie cooky : cookies) {
                    if(cooky.getName().equals("username")){
                        user = cooky.getValue();
                    }
                    
                    if(cooky.getName().equals("token")){
                        token = cooky.getValue();
                    }
                    if(user != null && token != null){
                        break;
                    }
                }
                
                if(user == null || token == null){
                    return null;
                }else{
                    AccountDBContext accDb = new AccountDBContext();
                    
                    Account test = accDb.getAccountbyToken(token);
                    if(test != null){
                        session.setAttribute("account", test);
                    }
                    return test;
                }
                
            }
        }
        return account;
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = getAuthentication(req);
        if(account != null){
            doPost(req, resp, account);
        }else{
            resp.getWriter().print("acess denied!");
        }
    
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = getAuthentication(req);
        if(account != null){
            doGet(req, resp, account);
        }else{
            resp.getWriter().print("acess denied!");
        }
    }
    
}
