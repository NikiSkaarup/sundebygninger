/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Facade;
import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Building;
import model.Org;
import util.Helper;
import static util.Helper.forwardGet;

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
        try {
            //processRequest(request, response);
            //DB connection
            Facade facade = Facade.getFacade();

            //organisation Id 
            if (request.getParameter("id") != null) {
                //get the id from JSP/URL
                int id = Integer.parseInt(request.getParameter("id"));
                Building b = facade.getBuilding(id);
                //Save the variable
                request.setAttribute("b", b);
                //forward from servlet to JSP
                if (request.getParameter("u") != null) {//from update
                    request.setAttribute("org", b.getOrg());
                    request.setAttribute("action", "Update");
                    Helper.forward(request, response, "addUpdateBuilding.jsp");
                } else {
                    Helper.forward(request, response, "/viewBuilding.jsp");
                }
            } else if (request.getParameter("oid") != null) {
                //get the hidden "requestScope=oid" from addUpdateBuilding 
                int oId = Integer.parseInt(request.getParameter("oid"));
                Org org = new Org();
                org.setId(oId);
                //saves the organisation Id
                request.setAttribute("org", org);
                request.setAttribute("action", "Tilføj");
                Helper.forwardGet(request, response, "/addUpdateBuilding.jsp");
            } else {
                //forward to view all buildings?
                Helper.forwardGet(request, response, "buildings");
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            forwardGet(request, response, "/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Facade facade = Facade.getFacade();
            Building b = new Building();

            b.setId(-1);
            if (request.getParameter("bId") != null && !request.getParameter("bId").equals("")) {
                b.setId(Integer.parseInt(request.getParameter("bId")));
            }

            Org org = new Org();
            if (request.getParameter("oId") != null && !request.getParameter("oId").equals("")) {
                org.setId(Integer.parseInt(request.getParameter("oId")));
            }

            b.setOrg(org);

            //organisation tages ud af session
            // User user = (User)request.getSession().getAttribute("user");
            //b.setOrg(user.getOrg());
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
            b.setConstructionYear(Timestamp.valueOf("1990-01-01 00:00:00"));
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
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            forwardGet(request, response, "/error.jsp");
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
}
