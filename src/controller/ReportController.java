package controller;

import data.DB;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Building;
import model.Comment;
import model.Report;
import model.Room;

/**
 * Created by Niki on 2016-10-26.
 *
 * @author Niki
 */
@WebServlet(name = "ReportController", urlPatterns = {"/report"})
public class ReportController extends javax.servlet.http.HttpServlet {

    HashMap<Integer, Report> reports;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            Report report = new Report();

            String b = request.getParameter("building");
            PreparedStatement pstmt = DB.getConnection().prepareStatement("select Id from building where Name=?");
            pstmt.setString(1, b);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            Building building = new Building();
            building.setId(rs.getInt(1));

            HashMap<Integer, Comment> comments = new HashMap<>();
            Comment roof = new Comment();
            roof.setComment(request.getParameter("roof"));
            comments.put(Integer.SIZE, roof);
            Comment outerWalls = new Comment();
            outerWalls.setComment(request.getParameter("outerWalls"));
            comments.put(Integer.SIZE, outerWalls);
            
            

            HashMap<Integer, Room> rooms = new HashMap<>();
            Room r = new Room();
            r.setNum(request.getParameter("room"));
            r.setDamage(request.getParameter("optradio").equals("Ja"));
            Date d = new Date(request.getParameter("date"));
            r.setWhen(new java.sql.Timestamp(d.getTime()));
            r.setWhere(request.getParameter("location"));
            r.setWhathappend(request.getParameter("incident"));
            r.setWhatwasfixed(request.getParameter("repair"));
            // TODO: damagetypes from checkboxes
            
            
            
            r.setMoistureScan(request.getParameter("moistureScan"));
            r.setMoistureTarget(request.getParameter("measureSpot"));
            r.setReport(report);

            Calendar calendar = Calendar.getInstance();
            java.util.Date now = calendar.getTime();
            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
            Timestamp submission = currentTimestamp;

            report.setBuilding(building);
            report.setComments(comments);
            report.setRooms(rooms);
            report.setSubmission(submission);
            reports.put(Integer.SIZE, report);

        } catch (SQLException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
