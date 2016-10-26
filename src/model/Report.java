package model;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 * Created by Niki on 2016-10-26.
 *
 * @author Niki
 */
public class Report {
    private int id;
    private Building building;
    private HashMap<Integer,Comment> comments;
    private HashMap<Integer,Room> rooms;
    private Timestamp submission;

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

    public HashMap<Integer, Comment> getComments() {
        return comments;
    }

    public void setComments(HashMap<Integer, Comment> comments) {
        this.comments = comments;
    }

    public HashMap<Integer, Room> getRooms() {
        return rooms;
    }

    public void setRooms(HashMap<Integer, Room> rooms) {
        this.rooms = rooms;
    }

    public Timestamp getSubmission() {
        return submission;
    }

    public void setSubmission(Timestamp submission) {
        this.submission = submission;
    }
}
