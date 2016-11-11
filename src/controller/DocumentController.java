package controller;

import domain.Facade;
import model.Building;
import model.Document;

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
 * Created by Niki on 2016-10-26.
 *
 * @author Niki
 */
@WebServlet(name = "DocumentController", urlPatterns = {"/document",
        "/document/insert", "/document/update"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class DocumentController extends HttpServlet {

    private final String SAVE_DIR = "documents";
    private Facade facade = Facade.getFacade();

    protected void doPost(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/document/insert":
                doPostInsert(req, res);
                break;
            case "/document/update":
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
            Document d = doPostBoth(req);
            if (facade.insertDocument(d) > 0)
                forwardGet(req, res, "/building?id=" + d.getBuilding().getId());
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
            Document d = doPostBoth(req);
            if (facade.updateDocument(d))
                forwardGet(req, res, "/building?id=" + d.getBuilding().getId());
            else
                forwardGet(req, res, req.getServletPath());
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

    private Document doPostBoth(HttpServletRequest req)
            throws ServletException, IOException {
        Document d = new Document();
        d.setId(Integer.parseInt(req.getParameter("id")));

        Building b = new Building();
        b.setId(Integer.parseInt(req.getParameter("b")));
        d.setBuilding(b);

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

        d.setName(name);
        d.setPath(fileName);
        return d;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/document/insert":
                doGetInsert(req, res);
                break;
            case "/document/update":
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
            Document d = facade.getDocument(id);
            req.setAttribute("d", d);
            forwardGet(req, res, "/document.jsp");
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
            forwardGet(req, res, "/document.jsp");
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

    private void doGetUpdate(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Document d = facade.getDocument(id);
            req.setAttribute("b", d.getBuilding().getId());
            req.setAttribute("d", d);
            req.setAttribute("url", req.getServletPath());
            req.setAttribute("action", "Update");
            forwardGet(req, res, "/document.jsp");
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }
}
