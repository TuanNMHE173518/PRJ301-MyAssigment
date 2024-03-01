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
public class Room {
    private String id;
    private String number;
    private String building;
    private ArrayList<Lession> lession = new ArrayList<>();
    
    public Room() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ArrayList<Lession> getLession() {
        return lession;
    }

    public void setLession(ArrayList<Lession> lession) {
        this.lession = lession;
    }

    

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    
    
}
