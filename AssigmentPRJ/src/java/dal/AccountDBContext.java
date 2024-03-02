/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Account;
import entity.Lecture;
import entity.Student;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ADMIN
 */
public class AccountDBContext extends DBContext<Account>{
    
    public Account getAccountByUsernameAndPassword(String username, String password){
        try {
            PreparedStatement stm = null;
            String sql = "select username, password, role, displayname, lecid,sid from Account where username = ? and password = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Account a = new Account();
                a.setUsername(username);
                a.setPassword(password);
                a.setDisplayname(rs.getString("displayname"));
                a.setRole(rs.getInt("role"));
                if(rs.getInt("lecid") != 0){
                    Lecture l = new Lecture();
                    l.setId(rs.getInt("lecid"));
                    a.setLecture(l);
                }
                if(rs.getInt("sid") != 0){
                    Student s = new Student();
                    s.setId(rs.getInt("sid"));
                    a.setStudent(s);
                }
                return a;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
        return null;    
    }

    @Override
    public ArrayList<Account> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
