package model;

import java.sql.Timestamp;

/**
 * Created by Niki on 2016-10-26.
 *
 * @author Niki
 */
public class Request {
    private int id;
    private Timestamp submission;
    private Report report;
    private User user;
    private String description;
    private Building building;
    private ServiceType serviceType;

    public Request() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getSubmission() {
        return submission;
    }

    public void setSubmission(Timestamp submission) {
        this.submission = submission;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }
}
