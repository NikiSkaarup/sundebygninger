package controller;

import domain.Facade;
import model.Building;
import model.Image;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

import static util.Helper.extractFileName;
import static util.Helper.forwardGet;
import static util.Helper.generateSemiUniqueFileName;

/**
 * Created by Niki on 2016-10-27.
 *
 * @author Niki
 */
@WebServlet(name = "ImageController", urlPatterns = {"/image",
        "/image/insert" ,"/image/update"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class ImageController extends HttpServlet {

    private final String SAVE_DIR = "images";
    private Facade facade = Facade.getFacade();

    protected void doPost(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/image/insert":
                doPostInsert(req, res);
                break;
            case "/image/update":
                doPostUpdate(req, res);
                break;
            default:
                forwardGet(req, res, "/home.jsp");
                break;
        }
    }

    private void doPostInsert(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        try {
            Image i = doPostBoth(req);
            if (facade.insertImage(i) > 0)
                forwardGet(req, res, "/building?id=" + i.getBuilding().getId());
            else
                forwardGet(req, res, req.getServletPath());
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

    private void doPostUpdate(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            Image d = doPostBoth(req);
            if (facade.updateImage(d))
                forwardGet(req, res, "/building?id=" + d.getBuilding().getId());
            else
                forwardGet(req, res, req.getServletPath());
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

    private Image doPostBoth(HttpServletRequest req)
            throws ServletException, IOException {
        Image i = new Image();
        i.setId(Integer.parseInt(req.getParameter("id")));

        Building b = new Building();
        b.setId(Integer.parseInt(req.getParameter("b")));
        i.setBuilding(b);

        String appPath = req.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + SAVE_DIR;

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists())
            fileSaveDir.mkdir();

        String name = "", fileExt, fileName = "";

        for (Part part : req.getParts()) {
            if (!part.getName().equals("file"))
                continue;
            if (name.equals("")) {
                name = extractFileName(part);
                fileExt = name.split("\\.")[1];
                do fileName = generateSemiUniqueFileName() + "." + fileExt;
                while (new File(savePath + File.separator + fileName).exists());
            }
            part.write(savePath + File.separator + fileName);
        }

        i.setName(name);
        i.setPath(fileName);
        return i;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/image/insert":
                doGetInsert(req, res);
                break;
            case "/image/update":
                doGetUpdate(req, res);
                break;
            default:
                doGetView(req, res);
                break;
        }
    }

    private void doGetView(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Image i = facade.getImage(id);
            req.setAttribute("i", i);
            forwardGet(req, res, "/image.jsp");
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

    private void doGetInsert(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        try {
            int b = Integer.parseInt(req.getParameter("b"));
            req.setAttribute("b", b);
            req.setAttribute("action", "Insert");
            req.setAttribute("url", req.getServletPath());
            forwardGet(req, res, "/image.jsp");
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

    private void doGetUpdate(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Image i = facade.getImage(id);
            req.setAttribute("b", i.getBuilding().getId());
            req.setAttribute("i", i);
            req.setAttribute("url", req.getServletPath());
            req.setAttribute("action", "Update");
            forwardGet(req, res, "/image.jsp");
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }
}
