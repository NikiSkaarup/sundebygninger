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
@WebServlet(name = "ImageController", urlPatterns = {"/image"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class ImageController extends HttpServlet {

    private final String SAVE_DIR = "images";

    protected void doPost(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        Facade facade = Facade.getFacade();

        int id = -1;
        if (!req.getParameter("id").equals(""))
            id = Integer.parseInt(req.getParameter("id"));

        int bId = -1;
        if (!req.getParameter("b").equals(""))
            bId = Integer.parseInt(req.getParameter("b"));

        Building b = facade.getBuilding(bId);

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

        Image i = new Image();
        i.setName(name);
        i.setBuilding(b);
        i.setPath(fileName);

        boolean viewB = false;
        if (id > 0 && facade.updateImage(i)) {
            viewB = true;
        } else {
            int newId = facade.insertImage(i);
            if (newId > 0)
                viewB = true;
        }

        if (viewB)
            forwardGet(req, res, "/building?id=" + b.getId());
        else
            forwardGet(req, res, "/buildings?oid=" + b.getOrg().getId());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        Facade facade = Facade.getFacade();

        int id = -1;
        if (req.getParameter("id") != null)
            id = Integer.parseInt(req.getParameter("id"));

        if (req.getParameter("edit") == null) {
            req.setAttribute("action", "add");
        } else {
            req.setAttribute("action", "edit");
            id = Integer.parseInt(req.getParameter("edit"));
        }

        int b = -1;
        if (req.getParameter("b") != null)
            b = Integer.parseInt(req.getParameter("b"));
        req.setAttribute("b", b);

        if (id > 0 && b > 0) {
            Image i = facade.getImage(id);
            req.setAttribute("i", i);
        }

        if (b < 0)
            forwardGet(req, res, "home.jsp");
        else
            forwardGet(req, res, "image.jsp");
    }
}
