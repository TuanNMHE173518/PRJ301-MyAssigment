/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Account;
import entity.Lecture;
import entity.Student;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.HashHelper;

/**
 *
 * @author ADMIN
 */
public class AccountDBContext extends DBContext<Account> {

    public Account getAccountbyToken(String token) {
        try {
            PreparedStatement stm = null;
            String sql = "select username, password, displayname, lecid,[sid] \n"
                    + "from Account a join Tokens tok on a.username = tok.account\n"
                    + "where tok.token = ? ";
            stm = connection.prepareStatement(sql);
            stm.setString(1, token);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                Account a = new Account();
                a.setUsername(rs.getString("username"));

                a.setDisplayname(rs.getString("displayname"));

                if (rs.getInt("lecid") != 0) {
                    Lecture l = new Lecture();
                    l.setId(rs.getInt("lecid"));
                    a.setLecture(l);
                }
                if (rs.getInt("sid") != 0) {
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

    public Account getAccountGoogleByEmail(String email) {

        try {
            String sql = "select a.username, a.[password], a.displayname from account a join Lecture l on a.lecid = l.LecId					 \n"
                    + "where LOWER(l.email) = ?\n"
                    + "UNION\n"
                    + "select a.username, a.[password], a.displayname from account a join Student s on s.SID = a.sid					 \n"
                    + "where LOWER(s.email) = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, email);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));

                return getAccountByUsernameAndPassword(account.getUsername(), account.getPassword());

            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Account getAccountByUsernameAndPassword(String username, String password) {
        try {
            PreparedStatement stm = null;
            String sql = "select username, password, displayname, lecid,sid from Account where username = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                String storedpass = rs.getString("password");

                if (password.equals(storedpass)) {
                    Account a = new Account();
                    a.setUsername(username);
                    a.setPassword(password);
                    a.setDisplayname(rs.getString("displayname"));

                    if (rs.getInt("lecid") != 0) {
                        Lecture l = new Lecture();
                        l.setId(rs.getInt("lecid"));
                        a.setLecture(l);
                    }
                    if (rs.getInt("sid") != 0) {
                        Student s = new Student();
                        s.setId(rs.getInt("sid"));
                        a.setStudent(s);
                    }
                    return a;
                }
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
