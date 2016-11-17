package controller;

import domain.Facade;
import exceptions.PolygonException;
import model.Building;
import model.Org;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
                    doGetCustomer(req, res, user);
                    break;
            }
        } catch (NullPointerException | PolygonException e) {
            req.setAttribute("error", "doGet: " + e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

    private void doGetCustomer(HttpServletRequest req, HttpServletResponse
            res, User user) throws ServletException, IOException {
        try {
            Org org = facade.getOrg(user.getOrg());
            org.setBuildings(facade.getBuildings(org, 5));
            user.setOrg(org);
            req.setAttribute("user", user);

            // Get Organization - Make Organization mapper.
            // Get 5-10 buildings
            // Get 5 Latest Reports - Just make the HTML for it and add later
            // Make Link to all buildings
            forwardGet(req, res, "/home.jsp");
        } catch (Exception e) {
            req.setAttribute("error", "doGetCustomer: " + e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

    private void doGetEmployee(HttpServletRequest req, HttpServletResponse
            res, User user) throws ServletException, IOException {
        res.sendRedirect("/index.jsp");
        /*try {
            // Make Link to all requests
            throw new PolygonException("doGetEmployee not yet implemented");
        } catch (PolygonException e) {
            req.setAttribute("error", "doGetEmployee: " + e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }*/
    }

    private void doGetAdmin(HttpServletRequest req, HttpServletResponse res,
                            User user) throws ServletException, IOException {
        res.sendRedirect("/index.jsp");
        /*try {
            // Get 5 Latest Organizations
            // Get 5 Latest users
            // Make Link to all users
            throw new PolygonException("doGetAdmin not yet implemented");
        } catch (PolygonException e) {
            req.setAttribute("error", "doGetAdmin: " + e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }*/
    }
}
