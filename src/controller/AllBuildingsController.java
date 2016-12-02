/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Facade;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Building;
import exceptions.PolygonException;
import model.Org;
import model.User;
import util.Helper;

import static util.Helper.*;

/**
 * @author Menja
 */
@WebServlet(name = "AllBuildingsController", urlPatterns = {"/buildings"})
public class AllBuildingsController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AllBuildingsController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AllBuildingsController at " + request
                    .getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click
    // on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response)
            throws ServletException, IOException {
        // setup navigation bar
        setupNavigation(request);

        try {
            //get DB conn
            Facade facade = Facade.getFacade();

            User user = getUser(request);
            if (user == null || !userLoggedIn(user)) {
                response.sendRedirect("/login");
                return;
            }

            Org org = new Org(-1);
            List<Building> list = new ArrayList<>();
            try {
                int id = Integer.parseInt(request.getParameter("orgid"));
                org = facade.getOrg(id);
                list = facade.getBuildings(org.getId());
            } catch (Exception ignored) {
                if (user.getRole().getId() == 1) {
                    org = user.getOrg();
                    list = facade.getBuildings(user.getOrg().getId());
                } else if (user.getRole().getId() == 3) {
                    list = facade.getBuildings();
                }
            }

            //save the variables
            request.setAttribute("org", org);
            request.setAttribute("buildings", list);
            //forward from servlet to JSP
            forwardGet(request, response, "/allBuildings.jsp");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            forwardGet(request, response, "/error.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response)
            throws ServletException, IOException {
        //processRequest(request, response);
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
