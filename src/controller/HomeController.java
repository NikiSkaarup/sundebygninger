package controller;

import domain.Facade;
import exceptions.PolygonException;
import view.model.Home;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Helper.forwardGet;
import static util.Helper.getUser;
import static util.Helper.userLoggedIn;

/**
 * Created by Niki on 2016-11-16.
 *
 * @author Niki
 */
@WebServlet(name = "HomeController", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

    private Facade facade = Facade.getFacade();

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doGet(req, res);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            User user;
            user = getUser(req);
            if (user == null || !userLoggedIn(user)) {
                res.sendRedirect("/login.jsp");
                return;
            }

            switch (user.getRole().getId()) {
                case 1: // Customer
                    doGetCustomer(req, res, user);
                    break;
                case 2: // Employee
                    doGetEmployee(req, res, user);
                    break;
                case 3: // Admin
                    doGetAdmin(req, res, user);
                    break;
                default:
                    throw new PolygonException("doGet: user have no role");
            }
        } catch (NullPointerException | PolygonException e) {
            req.setAttribute("error", "doGet: " + e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

    private void doGetCustomer(HttpServletRequest req, HttpServletResponse
            res, User user) throws ServletException, IOException {
        try {
            Home home = new Home();
            home.setRole(user.getRole());

            home.setRole(user.getRole());
            // Get Organization - Make Organization mapper.
            home.setOrg(facade.getOrg(user.getOrg()));
            // Get 5-10 buildings
            home.setBuildings(facade.getBuildings(home.getOrg(), 5));
            // Make Link to all buildings

            home.setUsers(facade.getUsers(user.getOrg(), 5));

            // Get 5 Latest Reports - Just make the HTML for it and add later

            req.setAttribute("home", home);
            forwardGet(req, res, "/home.jsp");
        } catch (Exception e) {
            req.setAttribute("error", "doGetCustomer: " + e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

    private void doGetEmployee(HttpServletRequest req, HttpServletResponse
            res, User user) throws ServletException, IOException {
        try {
            Home home = new Home();
            home.setRole(user.getRole());

            home.setRequests(facade.getRequests());
            home.setBuildings(facade.getBuildings(home.getOrg(), 5));
            home.setUsers(facade.getUsers(user.getOrg(), 5));

            // View 10 unaccepted requests
            home.setUnacceptedRequests(facade.getRequestsUnaccepted(user.getId(), 5));
            // View 10 accepted requests by employee
            home.setAcceptedRequests(facade.getRequestsAcceptedByEmployee(user.getId(), 5));
            // Link to all requests

            // View last 10 reports by the employee
            home.setReports(facade.getReportsByEmployee(user.getId(), 5));
            // Link to all reports

            // Figure out what else an employee needs to see
            req.setAttribute("home", home);
            forwardGet(req, res, "/home.jsp");
            throw new PolygonException("doGetEmployee not yet implemented");
        } catch (PolygonException e) {
            req.setAttribute("error", "doGetEmployee: " + e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

    private void doGetAdmin(HttpServletRequest req, HttpServletResponse res,
                            User user) throws ServletException, IOException {
        try {
            Home home = new Home();
            home.setRole(user.getRole());

            // Get 5 Latest Organizations
            home.setOrgs(facade.getOrgs(5));
            // Get 5 Latest users
            home.setUsers(facade.getUsersLimit(5));

            home.setBuildings(facade.getBuildingsLimit(5));

            home.setRequests(facade.getRequestsLimit(5));

            home.setReports(facade.getReportsLimit(5));

            req.setAttribute("home", home);
            forwardGet(req, res, "/home.jsp");
            throw new PolygonException("doGetAdmin not yet implemented");
        } catch (PolygonException e) {
            req.setAttribute("error", "doGetAdmin: " + e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }
}
