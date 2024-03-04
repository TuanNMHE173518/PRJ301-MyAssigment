/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Enrollment;
import entity.Group;
import entity.Student;
import entity.Subject;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class EnrollmentDBContext extends DBContext<Enrollment> {

    public ArrayList<Enrollment> getEnrollmentByStudentID(int sid) {
        ArrayList<Enrollment> enrolls = new ArrayList<>();
        try {
            String sql = "SELECT  su.SuID, su.SuName, su.fullname,\n"
                    + "		e.StartDate, e.EndDate,\n"
                    + "		g.GID, g.GName, s.SName\n"
                    + "FROM Enroll e join Student s on s.[SID] = e.[SID]\n"
                    + "	         join [Group] g on g.GID = e.GID	\n"
                    + "		 join [Subject] su on su.SuID = g.SuID\n"
                    + "where s.[SID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Enrollment e = new Enrollment();
                Subject su = new Subject();
                Student s = new Student();
                Group g = new Group();
                
                e.setStartDate(rs.getDate("StartDate"));
                e.setEndDate(rs.getDate("EndDate"));
                
                su.setId(rs.getString("SuID"));
                su.setName(rs.getString("SuName"));
                su.setFullname(rs.getString("fullname"));
                g.setSubject(su);
                g.setId(rs.getString("GID"));
                g.setName(rs.getString("GName"));
                e.setGroup(g);
                
                s.setId(sid);
                s.setName(rs.getString("SName"));
                e.setStudent(s);
                
                enrolls.add(e);
                
                
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EnrollmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return enrolls;
    }

    @Override
    public ArrayList<Enrollment> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
