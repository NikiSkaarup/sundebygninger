package util;

import domain.Facade;
import exceptions.PolygonException;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * Created by Niki on 2016-11-03.
 *
 * @author Niki
 */
public class Helper {
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

    public static String generateSemiUniqueFileName() {
        long millis = System.currentTimeMillis();
        String datetime = (millis / 200) + "";
        return datetime + "_" + millis;
    }

    public static User getUser(HttpServletRequest req) throws PolygonException {
        try {
            return (User) req.getSession().getAttribute("user");
        } catch (Exception e) {
            throw new PolygonException("getUser: failed to get user: " + e
                    .getMessage());
        }
    }

    public static boolean userLoggedIn(User user) {
        Facade facade = Facade.getFacade();
        try {
            User user2 = facade.getUser(user);
            return user2 != null && user.getName().equals(user2.getName());
        } catch (PolygonException e) {
            return false;
        }
    }

    public static void forward(HttpServletRequest req, HttpServletResponse
            res, String url) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(url);
        rd.forward(req, res);
    }

    public static void forwardGet(HttpServletRequest req, HttpServletResponse
            res, String url) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(url);
        rd.forward(new RequestWrap(req), res);
    }

}
