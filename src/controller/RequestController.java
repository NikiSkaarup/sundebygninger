package controller;

import domain.Facade;
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
import java.util.List;

/**
 * Created by Niki on 2016-11-04.
 *
 * @author Niki
 */
@WebServlet(name = "RequestController", urlPatterns = {"/request"})
public class RequestController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Facade facade = Facade.getFacade();

        Request r = new Request();
        r.setId(-1);
        r.setDescription(req.getParameter("description"));

        Building b = new Building();
        b.setId(-1);
        r.setBuilding(b);

        ServiceType st = new ServiceType();
        st.setId(-1);
        r.setServiceType(st);



    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Facade facade = Facade.getFacade();
        int id = Integer.parseInt(req.getParameter("id"));
        List<Request> list = facade.getRequests(id);
        req.setAttribute("requests", list);
        RequestDispatcher rd = req.getRequestDispatcher("/request.jsp");
        rd.forward(req,res);
    }
}
