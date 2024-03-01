/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.TimeSlot;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ADMIN
 */
public class TimeSlotDBContext extends DBContext<TimeSlot>{

    @Override
    public ArrayList<TimeSlot> list() {
        ArrayList<TimeSlot> timeslots = new ArrayList<>();
        try {
            PreparedStatement stm = null;
            String sql ="select timeID, tname, [start], [end] from timeslot";
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                TimeSlot t = new TimeSlot();
                t.setId(rs.getInt("timeID"));
                t.setName(rs.getString("tname"));
                t.setStart(rs.getTime("start"));
                t.setEnd(rs.getTime("end"));
                timeslots.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimeSlotDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return timeslots;
    }
    
}
