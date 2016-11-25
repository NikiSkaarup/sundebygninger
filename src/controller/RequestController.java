package controller;

import domain.Facade;
import exceptions.PolygonException;
import model.Building;
import model.Request;
import model.ServiceType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static util.Helper.forwardGet;

/**
 * Created by Niki on 2016-11-04.
 *
 * @author Niki
 */
@WebServlet(name = "RequestController", urlPatterns = {"/request",
        "/request/insert", "/request/update", "/requests"})
public class RequestController extends HttpServlet {

    private Facade facade = Facade.getFacade();

    protected void doPost(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/request/insert":
                doPostInsert(req, res);
                break;
            case "/request/update":
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
            Request r = doPostBoth(req);
            if (facade.insertRequest(r) > 0)
                forwardGet(req, res, "/requests?b=" + r.getBuilding().getId());
            else
                forwardGet(req, res, req.getServletPath());
        } catch (PolygonException e) {
            req.setAttribute("error", e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

    private void doPostUpdate(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            Request r = doPostBoth(req);
            if (facade.updateRequest(r))
                forwardGet(req, res, "/requests?b=" + r.getBuilding().getId());
            else
                forwardGet(req, res, req.getServletPath());
        } catch (PolygonException e) {
            req.setAttribute("error", e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

    private Request doPostBoth(HttpServletRequest req)
            throws ServletException, IOException {
        Request r = new Request();
        int id = Integer.parseInt(req.getParameter("b"));
        r.setId(id);
        r.setDescription(req.getParameter("description"));

        Building b = new Building();
        int bId = Integer.parseInt(req.getParameter("b"));
        b.setId(bId);
        r.setBuilding(b);

        ServiceType st = new ServiceType();
        int stId = Integer.parseInt(req.getParameter("serviceType"));
        st.setId(stId);
        r.setServiceType(st);
        return r;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        try {
            req.setAttribute("sts", facade.getServiceTypes());
            req.setAttribute("requests", new ArrayList<>());
        } catch (PolygonException e) {
            req.setAttribute("error", e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }

        switch (req.getServletPath()) {
            case "/request/insert":
                doGetInsert(req, res);
                break;
            case "/request/update":
                doGetUpdate(req, res);
                break;
            case "/requests":
                doGetViewAll(req, res);
                break;
            default:
                doGetView(req, res);
                break;
        }
    }

    private void doGetInsert(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        try {
            int b = Integer.parseInt(req.getParameter("b"));
            req.setAttribute("b", b);
            req.setAttribute("sts", facade.getServiceTypes());
            req.setAttribute("action", "Insert");
            req.setAttribute("url", req.getServletPath());
            forwardGet(req, res, "/request.jsp");
        } catch (PolygonException e) {
            req.setAttribute("error", e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

    private void doGetUpdate(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Request r = facade.getRequest(id);
            req.setAttribute("b", r.getBuilding().getId());
            req.setAttribute("r", r);
            req.setAttribute("url", req.getServletPath());
            req.setAttribute("action", "Update");
            forwardGet(req, res, "/request.jsp");
        } catch (NullPointerException | PolygonException e) {
            req.setAttribute("error", e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

    private void doGetView(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Request r = facade.getRequest(id);
            req.setAttribute("r", r);
            forwardGet(req, res, "/request.jsp");
        } catch (PolygonException e) {
            req.setAttribute("error", e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }

    private void doGetViewAll(HttpServletRequest req, HttpServletResponse
            res) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("b"));
            List<Request> list = facade.getRequests(id);
            req.setAttribute("b", id);
            req.setAttribute("requests", list);
            forwardGet(req, res, "/request.jsp");
        } catch (PolygonException e) {
            req.setAttribute("error", e.getMessage());
            forwardGet(req, res, "/error.jsp");
        }
    }
}
