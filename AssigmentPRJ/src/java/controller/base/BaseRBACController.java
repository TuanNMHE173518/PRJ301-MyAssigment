/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.base;

import dal.RoleDBContext;
import entity.Account;
import entity.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public abstract class BaseRBACController extends BaseRequiredAuthentication{
    
    private ArrayList<Role> getRoles(Account account, HttpServletRequest req){
        RoleDBContext roleDb = new RoleDBContext();
        String url = req.getServletPath();
        String username = account.getUsername();
        
        return roleDb.getByUsernameAndURL(username, url);
        
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        ArrayList<Role> roles = getRoles(account, req);
        if(roles.size() < 1){
            resp.getWriter().print("access denid!");
        }else{
            HttpSession session = req.getSession();
            session.setAttribute("roles", roles);
            doGet(req, resp, account, roles);
        }
    
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles) throws ServletException, IOException {
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles) throws ServletException, IOException {
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        ArrayList<Role> roles = getRoles(account, req);
        if(roles.size() < 1){
            resp.getWriter().print("access denid!");
        }else{
            HttpSession session = req.getSession();
            session.setAttribute("roles", roles);
            doPost(req, resp, account, roles);
        }
    }
    
}
