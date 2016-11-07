/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Facade;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Building;
import model.Org;
import model.User;
import util.Helper;

/**
 *
 * @author Menja
 */
@WebServlet(name = "BuildingController", urlPatterns = {"/building"})

public class BuildingController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        //DB connection
        Facade facade = Facade.getFacade();

        //organisation Id 
        if (request.getParameter("id") != null) {
            //get the id from jsp/url
            int id = Integer.parseInt(request.getParameter("id"));
            
            Building b = facade.getBuilding(id);
            
            //Save the variable
            request.setAttribute("b", b);

            //forward from servlet to JSP
            Helper.forward(request, response, "/viewBuilding.jsp");
        } else {
            //forward to view all buildings?
            Helper.forwardGet(request, response, "buildings");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Facade facade = Facade.getFacade();
        Building b = new Building();
        
        b.setId(-1);
        if (request.getParameter("bid") != null
                && !request.getParameter("bid").equals("")) {
            b.setId(Integer.parseInt(request.getParameter("bid")));
        }
        if (request.getParameter("oid") != null
                && !request.getParameter("oid").equals("")) {

            Org org = new Org();
            org.setId(Integer.parseInt(request.getParameter("oid")));

            b.setOrg(org);
        }

        //organisation tages ud af session
        // User user = (User)request.getSession().getAttribute("user");
        //b.setOrg(user.getOrg());
        //BUILDING DATA from form put into variables
        String name = request.getParameter("Name");
        String address = request.getParameter("Address");
        //  String constructionYear = request.getParameter("ConstructionYear");
        String area = request.getParameter("Area");
        String currentUse = request.getParameter("CurrentUse");
        String previousUse = request.getParameter("PreviousUse");

        //use the variables with the data from the form
        b.setName(name);
        b.setAddress(address);
        // b.setConstructionYear(Timestamp.valueOf(constructionYear));
        b.setArea(area);
        b.setCurrentUse(currentUse);
        b.setPreviousUse(previousUse);

        //insert to DB via facade
        int id = facade.insertBuilding(b);

        if (id > 0) {
            Helper.forwardGet(request, response, "buildings?oid=" + b.getOrg().getId());
        } else {
            Helper.forwardGet(request, response, "addUpdateBuilding.jsp?oid=" + b.getOrg().getId());
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
