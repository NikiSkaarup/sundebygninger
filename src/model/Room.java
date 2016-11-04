package model;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Niki on 2016-10-26.
 *
 * @author Niki
 */
public class Room {
    private int id;
    private String num;
    private Timestamp when;
    private String where;
    private String whathappend;
    private String whatwasfixed;
    private boolean damage;
    private List<DamageType> damageTypes;
    private List<Comment> comments;
    private String moistureScan;
    private String moistureTarget;
    private Report report;

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Timestamp getWhen() {
        return when;
    }

    public void setWhen(Timestamp when) {
        this.when = when;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getWhathappend() {
        return whathappend;
    }

    public void setWhathappend(String whathappend) {
        this.whathappend = whathappend;
    }

    public String getWhatwasfixed() {
        return whatwasfixed;
    }

    public void setWhatwasfixed(String whatwasfixed) {
        this.whatwasfixed = whatwasfixed;
    }

    public boolean isDamage() {
        return damage;
    }

    public void setDamage(boolean damage) {
        this.damage = damage;
    }

    public List<DamageType> getDamageTypes() {
        return damageTypes;
    }

    public void setDamageTypes(List<DamageType> damageTypes) {
        this.damageTypes = damageTypes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getMoistureScan() {
        return moistureScan;
    }

    public void setMoistureScan(String moistureScan) {
        this.moistureScan = moistureScan;
    }

    public String getMoistureTarget() {
        return moistureTarget;
    }

    public void setMoistureTarget(String moistureTarget) {
        this.moistureTarget = moistureTarget;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
