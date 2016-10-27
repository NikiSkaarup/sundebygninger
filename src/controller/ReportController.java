package controller;

import data.DB;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
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
            String b = request.getParameter("building");
            PreparedStatement pstmt = DB.getConnection().prepareStatement("select Id from building where Name=?");
            pstmt.setString(1, b);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            Building building = new Building();
            building.setId(rs.getInt(1));
            
            HashMap<Integer, Comment> comments;
            
            HashMap<Integer, Room> rooms;
            
            Calendar calendar = Calendar.getInstance();
            java.util.Date now = calendar.getTime();
            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
            Timestamp submission = currentTimestamp;
            
            Report r = new Report();
            r.setBuilding(building);
            r.setComments(comments);
            r.setRooms(rooms);
            r.setSubmission(submission);
            reports.put(Integer.SIZE, r);
            
        } catch (SQLException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
