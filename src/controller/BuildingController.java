/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Facade;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
import model.Building;

/**
 *
 * @author Menja
 */
@WebServlet(name = "BuildingController", urlPatterns = {"/Building"})

public class BuildingController extends HttpServlet {

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

        Facade facade = new Facade();
        Building b = new Building();
        b.setId(-1);
        if (!request.getParameter("buildingId").equals("")) {
            b.setId(Integer.parseInt(request.getParameter("buildingId")));
        }

        //BUILDING DATA from form put into variables
        String name = request.getParameter("Name");
        String address = request.getParameter("Address");
        String constructionYear = request.getParameter("ConstructionYear");
        String area = request.getParameter("Area");
        String currentUse = request.getParameter("CurrentUse");
        String previousUse = request.getParameter("PreviousUse");

        //use the variables with the data from the form
        b.setName(name);
        b.setAddress(address);
        b.setConstructionYear(Timestamp.valueOf(constructionYear));
        b.setArea(area);
        b.setCurrentUse(currentUse);
        b.setPreviousUse(previousUse);

        //insert to DB via facade
        facade.insertBuilding(b);

        if (b.getId() < 0) {
            forward(request, response, "allBuildings.jsp?id=" + b.getId());
        } else {
            forward(request, response, "addUpdateBuilding.jsp");
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
