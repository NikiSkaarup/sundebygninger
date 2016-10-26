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
    private Timestamp fulfillment;
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

    public Timestamp getFulfillment() {
        return fulfillment;
    }

    public void setFulfillment(Timestamp fulfillment) {
        this.fulfillment = fulfillment;
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
