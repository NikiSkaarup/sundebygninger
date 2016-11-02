/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import static manipulator.File.extractFileName;
import static manipulator.File.generateSemiUniqueFileName;

/**
 *
 * @author Menja
 */
@WebServlet(name = "BuildingController", urlPatterns = {"/BuildingController"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB

public class BuildingController extends HttpServlet {

    private static String saveDirectory = "imageUpload";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int buildingId = -1;
        if (!request.getParameter("buildingId").equals("")) {
            buildingId = Integer.parseInt(request.getParameter("buildingId"));
        }

        //BUILDING DATA from form put into variables
        processRequest(request, response);
        String name = request.getParameter("Name");
        String address = request.getParameter("Address");
        String constructionYear = request.getParameter("ConstructionYear");
        String area = request.getParameter("Area");
        String currentUse = request.getParameter("CurrentUse");
        String previousUse = request.getParameter("PreviousUse");

        try {
            //get the database connection
            Connection conn = data.DB.getConnection();
            assert conn != null;
            // Execute SQL query
            PreparedStatement pstmt;

            pstmt = (PreparedStatement) conn.prepareStatement("INSERT INTO"
                    + "building (Name, Address, ConstructionYear, CurrentUse, Area, PreviousUse)"
                    + "VALUES (?, ?, ?, ?, ?, ?);" 
                    + "SELECT LAST_INSERT_ID() AS Id;");

            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setString(3, constructionYear);
            pstmt.setString(4, currentUse);
            pstmt.setString(5, area);
            pstmt.setString(6, previousUse);

            ResultSet rs = pstmt.executeQuery();

            //take the Id from building 
            if (rs.getFetchSize() == 1) {
                rs.next();
                buildingId = rs.getInt("Id");
            }
            conn.close();



        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(BuildingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (buildingId < 0) {
            forward(request, response, "allBuildings.jsp?id=" + buildingId);
        } else {
            forward(request, response, "allBuildings.jsp");
        }
    }

//    /**
//     * Extracts file name from HTTP header content-disposition
//     */
//    private String extractFileName(Part part) {
//        String contentDisp = part.getHeader("content-disposition");
//        String[] items = contentDisp.split(";");
//        for (String s : items) {
//            if (s.trim().startsWith("filename")) {
//                return s.substring(s.indexOf("=") + 2, s.length() - 1);
//            }
//        }
//        return "";
//    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String string) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/" + string);
        rd.forward(request, response);
    }
}
