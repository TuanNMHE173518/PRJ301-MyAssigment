/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author ADMIN
 */
public class TimeSlot {
    private int id;
    private String name;
    private Time start;
    private Time end;
    private ArrayList<Lession> lession = new ArrayList<>();
    
    public TimeSlot() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public ArrayList<Lession> getLession() {
        return lession;
    }

    public void setLession(ArrayList<Lession> lession) {
        this.lession = lession;
    }

   
    
    
}
