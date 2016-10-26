/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
        try {
            processRequest(request, response);
            String name = request.getParameter("Name");
            String address = request.getParameter("Address");
            String constructionYear = request.getParameter("ConstructionYear");
            String area = request.getParameter("Area");
            String currentUse = request.getParameter("CurrentUse");
            String previousUse = request.getParameter("PreviousUse");
            
            // gets absolute path of the web application
            String appPath = request.getServletContext().getRealPath("");
            // constructs path of the directory to save uploaded file
            String savePath = appPath + File.separator + saveDirectory;
            // creates the save directory if it does not exists
            File fileSaveDir = new File(savePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }
            for (Part part : request.getParts()) {
                String fileName = extractFileName(part);
                part.write(savePath + File.separator + fileName);
            }
            request.setAttribute("message", "Billede(r) uploaded med succes");
            getServletContext().getRequestDispatcher("/message.jsp").forward(
                    request, response);
            
            //get the database connection
            Connection conn = data.DB.getConnection();
            
            // Execute SQL query
            Statement stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO building";
            ResultSet rs = stmt.executeQuery(sql);
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
