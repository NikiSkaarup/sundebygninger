package model;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 * Created by Niki on 2016-10-26.
 *
 * @author Niki
 */
public class Building {
    private int id;
    private String name;
    private String address;
    private String currentUse;
    private String previousUse;
    private String area;
    private Timestamp construtionYear;
    private Org org;
    private HashMap<Integer,Request> requests;
    private HashMap<Integer,Incident> incidents;
    private HashMap<Integer,Image> images;
    private HashMap<Integer,Report> reports;

    public Building() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCurrentUse() {
        return currentUse;
    }

    public void setCurrentUse(String currentUse) {
        this.currentUse = currentUse;
    }

    public String getPreviousUse() {
        return previousUse;
    }

    public void setPreviousUse(String previousUse) {
        this.previousUse = previousUse;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Timestamp getConstrutionYear() {
        return construtionYear;
    }

    public void setConstrutionYear(Timestamp construtionYear) {
        this.construtionYear = construtionYear;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public HashMap<Integer, Request> getRequests() {
        return requests;
    }

    public void setRequests(HashMap<Integer, Request> requests) {
        this.requests = requests;
    }

    public HashMap<Integer, Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(HashMap<Integer, Incident> incidents) {
        this.incidents = incidents;
    }

    public HashMap<Integer, Image> getImages() {
        return images;
    }

    public void setImages(HashMap<Integer, Image> images) {
        this.images = images;
    }

    public HashMap<Integer, Report> getReports() {
        return reports;
    }

    public void setReports(HashMap<Integer, Report> reports) {
        this.reports = reports;
    }
}
