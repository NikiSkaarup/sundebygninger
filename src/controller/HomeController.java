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
@WebServlet(name = "HomeController", urlPatterns = {"/home", "/index", ""})
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
                res.sendRedirect("/login");
                return;
            }

            // Switch case which handles where the request gets handled based
            // on user role id
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
                    throw new PolygonException("user have no role");
            }
        } catch (NullPointerException | PolygonException e) {
            req.setAttribute("error", "doGet: " + e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

    /**
     * This method is for when user is a Customer
     * @param req request
     * @param res response
     * @param user user taken from session
     * @throws ServletException
     * @throws IOException
     */
    private void doGetCustomer(HttpServletRequest req, HttpServletResponse
            res, User user) throws ServletException, IOException {
        try {
            Home home = new Home();
            user.setOrg(facade.getOrg(user.getOrg()));
            home.setUser(user);
            home.setBuildings(facade.getBuildings(user.getOrg(), 5));
            home.setUsers(facade.getUsers(user.getOrg(), 5));

            // Get 5 Latest Reports
            // Make method 'getReportsLimitByOrg(Org org, int limit)'
            //home.setReports(facade.getReportsLimitByOrg(user.getOrg(), 5));
            home.setReports(facade.getReportsLimit(5));

            req.setAttribute("home", home);
            forwardGet(req, res, "/home.jsp");
        } catch (Exception e) {
            req.setAttribute("error", "doGetCustomer: " + e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

    /**
     * This method is for when user is a Service Employee
     * @param req request
     * @param res response
     * @param user user taken from session
     * @throws ServletException
     * @throws IOException
     */
    private void doGetEmployee(HttpServletRequest req, HttpServletResponse
            res, User user) throws ServletException, IOException {
        try {
            Home home = new Home();
            home.setUser(user);
            home.setUnacceptedRequests(facade.getRequestsUnaccepted(5));
            home.setAcceptedRequests(facade.getRequestsAcceptedByEmployee
                    (user.getId(), 5));
            home.setReports(facade.getReportsByEmployee(user.getId(), 5));

            // Figure out what else an employee needs to see
            req.setAttribute("home", home);
            forwardGet(req, res, "/home.jsp");
        } catch (PolygonException e) {
            req.setAttribute("error", "doGetEmployee: " + e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

    /**
     * This method is for when user is an Admin
     * @param req request
     * @param res response
     * @param user user taken from session
     * @throws ServletException
     * @throws IOException
     */
    private void doGetAdmin(HttpServletRequest req, HttpServletResponse res,
                            User user) throws ServletException, IOException {
        try {
            Home home = new Home();
            home.setUser(user);

            home.setOrgs(facade.getOrgs(5));
            home.setUsers(facade.getUsersLimit(5));
            home.setBuildings(facade.getBuildingsLimit(5));
            home.setRequests(facade.getRequestsLimit(5));
            home.setReports(facade.getReportsLimit(5));

            req.setAttribute("home", home);
            forwardGet(req, res, "/home.jsp");
        } catch (PolygonException e) {
            req.setAttribute("error", "doGetAdmin: " + e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }
}
