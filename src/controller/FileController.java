package controller;

import domain.Facade;
import model.Building;
import model.File;
import sun.misc.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;

import static util.Helper.forwardGet;

/**
 * Created by Niki on 2016-11-16.
 *
 * @author Niki
 */
@WebServlet(name = "FileController", urlPatterns = {"/file",
        "/file/insert", "/file/update"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class FileController extends HttpServlet {

    private int maxFileSize = 10485760; // 10 MB
    private Facade facade = Facade.getFacade();

    /***
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/file/insert":
                doPostInsert(req, res);
                break;
            case "/file/update":
                doPostUpdate(req, res);
                break;
            default:
                forwardGet(req, res, "/home.jsp");
                break;
        }
    }

    /***
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    private void doPostInsert(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        try {
            File f = doPostBoth(req);
            if (facade.insertFile(f) > 0)
                forwardGet(req, res, "/building?id=" + f.getBuilding().getId());
            else
                forwardGet(req, res, req.getServletPath());
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

    /***
     *
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    private void doPostUpdate(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            File f = doPostBoth(req);
            if (facade.updateFile(f))
                forwardGet(req, res, "/building?id=" + f.getBuilding().getId());
            else
                forwardGet(req, res, req.getServletPath());
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

    /***
     *
     * @param req
     * @return
     * @throws ServletException
     * @throws IOException
     */
    private File doPostBoth(HttpServletRequest req)
            throws ServletException, IOException {
        File f = new File(Integer.parseInt(req.getParameter("id")));

        Building b = new Building();
        b.setId(Integer.parseInt(req.getParameter("b")));
        f.setBuilding(b);

        Part filePart = req.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName())
                .getFileName().toString(); // MSIE fix.
        InputStream is = filePart.getInputStream();

        f.setData(is, maxFileSize);
        f.setName(fileName);
        return f;
    }

    /***
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/file/insert":
                doGetInsert(req, res);
                break;
            case "/file/update":
                doGetUpdate(req, res);
                break;
            default:
                doGetView(req, res);
                break;
        }
    }

    /***
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    private void doGetView(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            File f = facade.getFile(id);
            req.setAttribute("f", f);
            forwardGet(req, res, "/file.jsp");
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

    /***
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    private void doGetInsert(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        try {
            int b = Integer.parseInt(req.getParameter("b"));
            req.setAttribute("b", b);
            req.setAttribute("action", "Insert");
            req.setAttribute("url", req.getServletPath());
            forwardGet(req, res, "/file.jsp");
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

    /***
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    private void doGetUpdate(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            model.File f = facade.getFile(id);
            req.setAttribute("b", f.getBuilding().getId());
            req.setAttribute("f", f);
            req.setAttribute("url", req.getServletPath());
            req.setAttribute("action", "Update");
            forwardGet(req, res, "/file.jsp");
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }
}
