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
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import static manipulator.File.generateSemiUniqueFileName;

/**
 *
 * @author Menja
 */
@WebServlet(name = "BuildingController", urlPatterns = {"/Building"})
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
        try {
            //data from form put into variables
            processRequest(request, response);
            String name = request.getParameter("Name");
            String address = request.getParameter("Address");
            String constructionYear = request.getParameter("ConstructionYear");
            String area = request.getParameter("Area");
            String currentUse = request.getParameter("CurrentUse");
            String previousUse = request.getParameter("PreviousUse");

            int buildingId = -1;
            if (request.getParameter("edit") != null) {
                buildingId = Integer.parseInt(request.getParameter("building"));
            }

            //image data
            // gets absolute path of the web application
            String appPath = request.getServletContext().getRealPath("");
            // constructs path of the directory to save uploaded file
            String savePath = appPath + File.separator + saveDirectory;
            // creates the save directory if it does not exists
            File fileSaveDir = new File(savePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }

            String imageName = extractFileName(request.getParts().iterator().next());

            String fileExtension = imageName.split("\\.")[1];
            String fileName;
      
            do {
                fileName = generateSemiUniqueFileName() + "." + fileExtension;
            } while (new File(savePath + File.separator + fileName).exists());

            for (Part part : request.getParts()) {
                part.write(savePath + File.separator + fileName);
            }

            //get the database connection
            Connection conn = data.DB.getConnection();

            // Execute SQL query
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("INSERT INTO"
                    + "building ([building].[Name], [building].[Address], [building].[ConstructionYear], [building].[CurrentUse], [building].[Area], [building].PreviousUse) VALUES (?, ?, ?, ?, ?, ?); SELECT LAST_INSERT_ID();");

            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setString(3, constructionYear);
            pstmt.setString(4, currentUse);
            pstmt.setString(5, area);
            pstmt.setString(6, previousUse);

            pstmt = conn.prepareStatement("INSERT INTO image ([image].[Name], [image].[FkBuildingId], [image].[Path]) VALUES (?,?,?)");

            pstmt.setString(1, imageName);
            pstmt.setInt(2, buildingId);
            pstmt.setString(3, fileName);

            ResultSet rs = pstmt.executeQuery();

            if (rs.getFetchSize() == 1) {
                rs.next();

            } else {

            }

        } catch (SQLException ex) {
            Logger.getLogger(BuildingController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

}
