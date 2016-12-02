package util;

import domain.Facade;
import exceptions.PolygonException;
import model.Link;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains a few helper methods which are meant to reduce repeated code
 *
 * @author Niki
 */
public class Helper {

    /**
     * Extracts file name from part
     *
     * @param part Part which file name should be taken from
     * @return file name or "" if failed to find a filename
     * @deprecated switching to usage of BLOBs
     */
    @Deprecated
    public static String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    /**
     * Generate a sorta unique filename to prevent file name collision
     *
     * @return file name
     */
    public static String generateSemiUniqueFileName() {
        long millis = System.currentTimeMillis();
        String datetime = (millis / 200) + "";
        return datetime + "_" + millis;
    }

    /**
     * @param req Request containing a user session
     * @return User cast from session
     * @throws PolygonException if it fails to get the user from session
     */
    public static User getUser(HttpServletRequest req) throws PolygonException {
        try {
            return (User) req.getSession().getAttribute("user");
        } catch (Exception e) {
            throw new PolygonException("getUser: failed to get user: " + e
                    .getMessage());
        }
    }

    /**
     * Verify whether a user is logged in / exists in the database
     * @param user user to verify
     * @return
     */
    public static boolean userLoggedIn(User user) {
        Facade facade = Facade.getFacade();
        try {
            User user2 = facade.getUser(user);
            return user.getName().equals(user2.getName());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Request Dispatcher forward simplifier which keeps request method intact
     * @param req request which should be forwarded
     * @param res response which should be forwarded
     * @param url url which is desired to go to
     * @throws ServletException
     * @throws IOException
     */
    public static void forward(HttpServletRequest req, HttpServletResponse
            res, String url) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(url);
        rd.forward(req, res);
    }

    /**
     * Request Dispatcher forward simplifier which turns request method into GET
     * @param req request which should be forwarded
     * @param res response which should be forwarded
     * @param url url which is desired to go to
     * @throws ServletException
     * @throws IOException
     */
    public static void forwardGet(HttpServletRequest req, HttpServletResponse
            res, String url) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(url);
        rd.forward(new RequestWrap(req), res);
    }

    public static void setupNavigation(HttpServletRequest req)
            throws ServletException, IOException {
        try {
            User user = getUser(req);
            if (user == null || !userLoggedIn(user))
                return;

            // Switch case which handles where the request gets handled based
            // on user role id
            switch (user.getRole().getId()) {
                case 1: // Customer
                    doGetCustomer(req, user);
                    break;
                case 2: // Employee
                    doGetEmployee(req, user);
                    break;
                case 3: // Admin
                    doGetAdmin(req, user);
                    break;
                default:
                    throw new PolygonException("user have no role");
            }
        } catch (NullPointerException | PolygonException e) {
            req.setAttribute("error", "doGet: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * This method is for when user is a Customer
     *
     * @param req  request
     * @param user user taken from session
     * @throws ServletException
     * @throws IOException
     */
    private static void doGetCustomer(HttpServletRequest req, User user)
            throws ServletException, IOException {
        try {
            List<Link> list = new ArrayList<>();

            list.add(new Link("/home",
                              "home"));
            list.add(new Link("/buildings?orgid=" + user.getOrg().getId(),
                              "buildings"));
            list.add(new Link("/customers?oid=" + user.getOrg().getId(),
                              "customers"));

            req.setAttribute("links", list);
        } catch (Exception e) {
            req.setAttribute("error", "navigation doGetCustomer: " + e
                    .getMessage());
            e.printStackTrace();
        }
    }

    /**
     * This method is for when user is a Service Employee
     *
     * @param req  request
     * @param user user taken from session
     * @throws ServletException
     * @throws IOException
     */
    private static void doGetEmployee(HttpServletRequest req, User user)
            throws ServletException, IOException {
        try {
            List<Link> list = new ArrayList<>();

            list.add(new Link("/home",
                              "home"));

            req.setAttribute("links", list);
        } catch (Exception e) {
            req.setAttribute("error", "navigation doGetEmployee: " + e
                    .getMessage());
            e.printStackTrace();
        }
    }

    /**
     * This method is for when user is an Admin
     *
     * @param req  request
     * @param user user taken from session
     * @throws ServletException
     * @throws IOException
     */
    private static void doGetAdmin(HttpServletRequest req, User user)
            throws ServletException, IOException {
        try {
            List<Link> list = new ArrayList<>();

            list.add(new Link("/home",
                              "home"));
            list.add(new Link("/orgs",
                              "organizations"));
            list.add(new Link("/buildings",
                              "buildings"));
            list.add(new Link("/customers",
                              "customers"));

            req.setAttribute("links", list);
        } catch (Exception e) {
            req.setAttribute("error", "navigation doGetAdmin: " + e
                    .getMessage());
            e.printStackTrace();
        }
    }

}
