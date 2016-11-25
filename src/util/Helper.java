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

}
