package model;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Niki on 2016-10-26.
 *
 * @author Niki
 */
public class Report {
    private int id;
    private Building building;
    private List<Comment> comments;
    private List<Room> rooms;
    private Timestamp submission;
    private User user;

    public Report() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Timestamp getSubmission() {
        return submission;
    }

    public void setSubmission(Timestamp submission) {
        this.submission = submission;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
