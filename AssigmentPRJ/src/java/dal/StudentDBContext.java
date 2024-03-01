/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Student;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class StudentDBContext extends DBContext<Student>{

    @Override
    public ArrayList<Student> list() {
        PreparedStatement stm = null;
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "select * from student";
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                Student s = new Student();
                s.setSid(rs.getInt("SID"));
                s.setSname(rs.getString("SName"));
                s.setDate(rs.getDate("Date"));
                s.setGender(rs.getBoolean("gender"));
                s.setEmail(rs.getString("email"));
                students.add(s);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(!stm.isClosed()){
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return students;
    }
    
}
