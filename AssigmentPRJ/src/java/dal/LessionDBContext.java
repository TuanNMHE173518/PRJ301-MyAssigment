/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Attendance;
import entity.Group;
import entity.Lecture;
import entity.Lession;
import entity.Room;
import entity.Student;
import entity.Subject;
import entity.TimeSlot;
import java.util.ArrayList;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.TimeHelper;

/**
 *
 * @author ADMIN
 */
public class LessionDBContext extends DBContext<Lession> {

    public ArrayList<Attendance> getAttendanceByStudentIdAndSubject(int sid, String suid) {
        ArrayList<Attendance> atts = new ArrayList<>();
        try {
            String sql = "SELECT le.LesID, le.[Date],\n"
                    + "	   t.timeID, t.tname, t.[start], t.[end],\n"
                    + "	   r.RID, r.Rnumber,\n"
                    + "	   l.LecId, l.LecName, l.email,\n"
                    + "	   g.GID, g.GName,\n"
                    + "	   a.attendID, a.ispresent, a.[description]\n"
                    + "FROM Student s	join Enroll e on s.[SID] = e.[SID]\n"
                    + "					        join [Group] g on g.GID = e.GID\n"
                    + "						join Lession le on le.GID = g.GID\n"
                    + "						join TimeSlot t on t.timeID = le.TimeID\n"
                    + "						join Room r on r.RID = le.RID\n"
                    + "						join Lecture l on le.LecID = l.LecId\n"
                    + "						left join Attendance a on a.lesid = le.LesID and a.[sid] =s.[SID]\n"
                    + "where s.[SID] = ?  and g.SuID = ?\n"
                    + "order by le.[Date]";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.setString(2, suid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                Lession le = new Lession();
                TimeSlot t = new TimeSlot();
                Room r = new Room();
                Lecture l = new Lecture();
                Group g = new Group();

                a.setId(rs.getInt("attendID"));
                if (a.getId() != 0) {
                    a.setIspresent(rs.getBoolean("ispresent"));
                    a.setDescription(rs.getString("description"));
                }

                le.setId(rs.getString("LesID"));
                le.setDate(rs.getDate("Date"));

                t.setId(rs.getInt("timeID"));
                t.setName(rs.getString("tname"));
                t.setStart(rs.getTime("start"));
                t.setEnd(rs.getTime("end"));
                le.setTimeslot(t);

                r.setId(rs.getString("RID"));
                r.setNumber(rs.getString("Rnumber"));
                le.setRoom(r);

                l.setId(rs.getInt("LecId"));
                l.setName(rs.getString("LecName"));
                l.setEmail(rs.getString("email"));
                le.setLecture(l);

                g.setId(rs.getString("GID"));
                g.setName(rs.getString("GName"));
                le.setGroup(g);

                a.setLession(le);
                atts.add(a);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return atts;

    }

    public void takeAttendanceWhenAttend(String lesid, ArrayList<Attendance> newatts) {
        try {
            connection.setAutoCommit(false);
            String sql_update_change = "UPDATE Attendance SET description = ?, isPresent = ?, datetime = GETDATE()"
                    + " WHERE lesid = ? AND sid = ? AND (isPresent <> ? OR description <> ?)";
            PreparedStatement stm_update_change = connection.prepareStatement(sql_update_change);
            for (Attendance att : newatts) {
                stm_update_change.setString(1, att.getDescription());
                stm_update_change.setBoolean(2, att.isIspresent());
                stm_update_change.setString(3, lesid);
                stm_update_change.setInt(4, att.getStudent().getId());
                stm_update_change.setString(6, att.getDescription());
                stm_update_change.setBoolean(5, att.isIspresent());
                stm_update_change.addBatch();
            }
            stm_update_change.executeBatch();

            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void takeAttendanceWhenAbsent(String lesid, ArrayList<Attendance> atts) {
        try {
            String sql_insert_att = "INSERT INTO [dbo].[Attendance]\n"
                    + "           ([lesid]\n"
                    + "           ,[sid]\n"
                    + "           ,[ispresent]\n"
                    + "           ,[description]\n"
                    + "           ,[datetime])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,GETDATE())";
            for (Attendance att : atts) {
                PreparedStatement stm_insert_att = connection.prepareStatement(sql_insert_att);
                stm_insert_att.setString(1, lesid);
                stm_insert_att.setInt(2, att.getStudent().getId());
                stm_insert_att.setBoolean(3, att.isIspresent());
                stm_insert_att.setString(4, att.getDescription());
                stm_insert_att.executeUpdate();
            }

            String sql_update_less = "UPDATE Lession SET isAttend = 1 WHERE LesID = ?";
            PreparedStatement stm_update_less = connection.prepareStatement(sql_update_less);
            stm_update_less.setString(1, lesid);
            stm_update_less.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            try {
                Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }

        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public ArrayList<Attendance> getAttendanceByLession(String lesid) {
        ArrayList<Attendance> atts = new ArrayList<>();
        try {
            String sql = "SELECT  s.SID, s.SName, s.image,\n"
                    + "a.attendID, a.[datetime], a.[description], a.ispresent\n"
                    + "FROM Student s join Enroll e on e.SID = s.SID\n"
                    + "			   join [Group] g on g.GID = e.GID\n"
                    + "			   join Lession les on les.GID = g.GID\n"
                    + "		           left join Attendance a on a.lesid = les.LesID\n"
                    + "			   and a.sid = s.SID\n"
                    + "where les.LesID = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lesid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance att = new Attendance();
                Group g = new Group();
                Student s = new Student();
                Lession les = new Lession();

                les.setId(lesid);
                att.setLession(les);

                s.setId(rs.getInt("SID"));
                s.setName(rs.getString("SName"));
                s.setImage(rs.getString("image"));
                att.setStudent(s);

                att.setId(rs.getInt("attendID"));
                if (att.getId() != 0) {
                    att.setDatetime(rs.getTimestamp("datetime"));
                    att.setDescription(rs.getString("description"));
                    att.setIspresent(rs.getBoolean("ispresent"));
                }
                atts.add(att);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return atts;
    }

    public ArrayList<Lession> getLessionBy(int lecId, Date from, Date to) {
        ArrayList<Lession> lession = new ArrayList<>();
        try {
            PreparedStatement stm = null;
            String sql = "select les.LesID, les.[Date], les.isAttend,\n"
                    + "g.GID, g.GName, su.SuID, su.SuName,\n"
                    + "t.timeID,t.tname,t.[start], t.[end],\n"
                    + "r.RID, r.Rnumber, l.LecId, l.LecName\n"
                    + "from Lession les join [Group] g on g.GID = les.GID \n"
                    + "						  join Room r on r.RID = les.RID\n"
                    + "						  join TimeSlot t on t.timeID = les.TimeID\n"
                    + "						  join [Subject] su on su.SuID = g.SuID\n"
                    + "						  join Lecture l on l.LecId = les.LecID\n"
                    + "where les.LecID = ? and les.[Date] >= ? and les.[Date] <= ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, lecId);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lession less = new Lession();
                Group g = new Group();
                TimeSlot t = new TimeSlot();
                Room r = new Room();
                Lecture lec = new Lecture();

                g.setId(rs.getString("GID"));
                g.setName(rs.getString("GName"));
                Subject su = new Subject();
                su.setId(rs.getString("SuID"));
                su.setName(rs.getString("SuName"));
                g.setSubject(su);

                t.setId(rs.getInt("timeID"));
                t.setName(rs.getString("tname"));
                t.setStart(rs.getTime("start"));
                t.setEnd(rs.getTime("end"));

                r.setId(rs.getString("RID"));
                r.setNumber(rs.getString("Rnumber"));

                lec.setId(lecId);
                lec.setName(rs.getString("LecName"));

                less.setDate(rs.getDate("Date"));
                less.setGroup(g);
                less.setLecture(lec);
                less.setRoom(r);
                less.setTimeslot(t);
                less.setId(rs.getString("LesID"));
                less.setIsAttend(rs.getBoolean("isAttend"));

                lession.add(less);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lession;

    }

    @Override
    public ArrayList<Lession> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
