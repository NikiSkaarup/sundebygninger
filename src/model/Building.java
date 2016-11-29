package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
    private Timestamp constructionYear;
    private Timestamp submission;
    private Org org;
    private List<Request> requests;
    private List<Incident> incidents;
    private List<File> images;
    private List<File> documents;
    private List<Report> reports;

    public Building() {
        id = 0;
        name = "";
        address = "";
        currentUse = "";
        previousUse = "";
        area = "";
        org = new Org();
        requests = new ArrayList<>();
        incidents = new ArrayList<>();
        images = new ArrayList<>();
        documents = new ArrayList<>();
        reports = new ArrayList<>();
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

    public Timestamp getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(Timestamp constructionYear) {
        this.constructionYear = constructionYear;
    }

    public Timestamp getSubmission() {
        return submission;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public List<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(List<Incident> incidents) {
        this.incidents = incidents;
    }

    public List<File> getImages() {
        return images;
    }

    public void setImages(List<File> images) {
        this.images = images;
    }

    public List<File> getDocuments() {
        return documents;
    }

    public void setDocuments(List<File> documents) {
        this.documents = documents;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}
