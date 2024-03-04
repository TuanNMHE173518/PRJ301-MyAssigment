/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class Group {
    private String id;
    private String name;
    
    private Subject subject;
    private Lecture lecture;
    private ArrayList<Lession> lession = new ArrayList<>();
    private ArrayList<Enrollment> erollments = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public ArrayList<Lession> getLession() {
        return lession;
    }

    public void setLession(ArrayList<Lession> lession) {
        this.lession = lession;
    }

    public ArrayList<Enrollment> getErollments() {
        return erollments;
    }

    public void setErollments(ArrayList<Enrollment> erollments) {
        this.erollments = erollments;
    }
    
   
    
    
    
}
