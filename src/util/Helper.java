package util;

import domain.Facade;
import model.User;

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

    public static void userLoggedIn(HttpServletRequest req, HttpServletResponse
            res) {
        boolean redirect = true;
        HttpSession s = req.getSession();
        if (s != null && s.getAttribute("User") != null) {
            try {
                User user = (User) s.getAttribute("User");
                Facade facade = Facade.getFacade();
                User user2 = facade.getUser(user);
                if (user2 != null && user.getName().equals(user2.getName())) {
                    redirect = false;
                } else {
                    s.removeAttribute("User");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (redirect) {
            try {
                res.sendRedirect("/index.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
