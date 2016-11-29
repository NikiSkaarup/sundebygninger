package controller;

import domain.Facade;
import exceptions.PolygonException;
import model.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;

import static util.Helper.forwardGet;

/**
 * Created by Niki on 2016-10-26.
 *
 * @author Niki
 */
@WebServlet(name = "DocumentController", urlPatterns = {"/document/*"})
public class DocumentController extends HttpServlet {

    private Facade facade = Facade.getFacade();

    protected void doPost(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        try {
            String idString = req.getPathInfo().substring(1);
            int id = Integer.parseInt(idString);
            File f = facade.getFileDocument(id);
            req.setAttribute("f", f);
            String[] arr = f.getName().split("\\.");
            if (arr.length > 1)
                res.setContentType("application/" + arr[1]);
            BufferedOutputStream bos = new BufferedOutputStream(res.getOutputStream());
            bos.write(f.getData());
            bos.flush();
            bos.close();
        } catch (NullPointerException | PolygonException e) {
            req.setAttribute("error", e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

}
