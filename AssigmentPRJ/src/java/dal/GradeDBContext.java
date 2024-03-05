/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Assessment;
import entity.Exam;
import entity.Grade;
import entity.Student;
import entity.Subject;
import java.util.ArrayList;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class GradeDBContext extends DBContext<Grade> {

    public float getAverageScore(int sid, String suid) {
        float average = 0;
        try {
            String sql = "Select Sum(gr.Score * ass.[Weight]) as average\n"
                    + "from Grade gr join Exam e on gr.ExamID = e.ExamID\n"
                    + "					   join Student s on s.SID = gr.SID\n"
                    + "					   join Assessment ass on ass.AssID = e.AssID\n"
                    + "					   join [Subject] su on su.SuID = ass.SuID \n"
                    + "where s.SID = ? and su.SuID = ?";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.setString(2, suid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                DecimalFormat df = new DecimalFormat("0.0");
                
                average = Float.parseFloat(df.format(rs.getFloat("average")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return average;
    }

    public ArrayList<Grade> getGradeByStudentIdAndSuject(int sid, String suid) {
        ArrayList<Grade> grades = new ArrayList<>();
        try {
            String sql = "Select gr.GradeID, gr.Score,gr.Comment, \n"
                    + "	   s.[SID], e.ExamID,\n"
                    + "	   ass.AssID, ass.[Name], ass.[Weight],\n"
                    + "	   su.SuID, su.SuName\n"
                    + "from Grade gr join Exam e on gr.ExamID = e.ExamID\n"
                    + "					   join Student s on s.SID = gr.SID\n"
                    + "					   left join Assessment ass on ass.AssID = e.AssID\n"
                    + "					   join [Subject] su on su.SuID = ass.SuID \n"
                    + "where s.SID = ? and su.SuID = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.setString(2, suid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Grade gr = new Grade();
                Exam e = new Exam();
                Assessment ass = new Assessment();

                ass.setId(rs.getInt("AssID"));
                ass.setName(rs.getString("Name"));
                ass.setWeight(rs.getFloat("Weight"));

                e.setId(rs.getString("ExamID"));
                e.setAssessment(ass);

                gr.setId(rs.getString("GradeID"));
                gr.setExam(e);
                gr.setComment(rs.getString("Comment"));
                float score = rs.getFloat("Score");
                if (!rs.wasNull()) {
                    gr.setScore(score);
                } else {
                    gr.setScore(-1);
                }
                grades.add(gr);

            }

        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grades;
    }

    @Override
    public ArrayList<Grade> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
