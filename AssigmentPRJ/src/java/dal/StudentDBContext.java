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
public class StudentDBContext extends DBContext<Student> {

    public ArrayList<Student> getStudentByLession(String lesid) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            PreparedStatement stm = null;
            String sql = "Select s.[SID], s.SName from Student s join Enroll e on e.SID = s.SID\n"
                    + "						join [Group] g on g.GID = e.GID\n"
                    + "						join Lession les on les.GID = g.GID\n"
                    + "where les.LesID = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, lesid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Student s = new Student();
                s.setId(rs.getInt("SID"));
                s.setName(rs.getString("SName"));
                students.add(s);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

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
                s.setId(rs.getInt("SID"));
                s.setName(rs.getString("SName"));
                s.setDate(rs.getDate("Date"));
                s.setGender(rs.getBoolean("gender"));
                s.setEmail(rs.getString("email"));
                students.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (!stm.isClosed()) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return students;
    }

}
