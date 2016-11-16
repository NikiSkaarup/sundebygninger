package controller;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            User user = getUser(req);
            if (!userLoggedIn(user)) {
                forwardGet(req, res, "/login");
                return;
            }

            // Get Organization - Make Organization mapper.
            // Get 5-10 buildings
            // Get 5 Latest Reports - Just make the HTML for it and add later
            // Get 5 Users

            // Make Link to all buildings
            // Make Link to all users

        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }
}
