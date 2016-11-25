package view.model;

import model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * view model for home page, combines all required variables for ease of use
 * reduces amount of beans and ensures less failures due to beans being null
 *
 * @author Niki
 */
public class Home {

    private Org org;
    private List<Building> buildings;
    private List<User> users;
    private User user;
    private List<Request> requests;
    private List<Request> unacceptedRequests;
    private List<Request> acceptedRequests;
    private List<Report> reports;
    private List<Incident> incidents;
    private List<Org> orgs;

    /**
     * Constructor to ensure that ArrayLists are never null
     */
    public Home() {
        buildings = new ArrayList<>();
        users = new ArrayList<>();
        requests = new ArrayList<>();
        unacceptedRequests = new ArrayList<>();
        acceptedRequests = new ArrayList<>();
        reports = new ArrayList<>();
        incidents = new ArrayList<>();
        orgs = new ArrayList<>();
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public List<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(List<Incident> incidents) {
        this.incidents = incidents;
    }

    public List<Org> getOrgs() {
        return orgs;
    }

    public void setOrgs(List<Org> orgs) {
        this.orgs = orgs;
    }

    public List<Request> getUnacceptedRequests() {
        return unacceptedRequests;
    }

    public void setUnacceptedRequests(List<Request> unacceptedRequests) {
        this.unacceptedRequests = unacceptedRequests;
    }

    public List<Request> getAcceptedRequests() {
        return acceptedRequests;
    }

    public void setAcceptedRequests(List<Request> acceptedRequests) {
        this.acceptedRequests = acceptedRequests;
    }
}
