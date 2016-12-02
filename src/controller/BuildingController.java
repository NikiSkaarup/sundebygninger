package controller;

import domain.Facade;
import exceptions.PolygonException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Building;
import model.Org;
import model.User;
import util.Helper;
import static util.Helper.forwardGet;
import static util.Helper.setupNavigation;

/**
 *
 * @author Menja
 */
@WebServlet(name = "BuildingController", urlPatterns = {"/building", "/building/insert", "/building/update"})

public class BuildingController extends HttpServlet {

    //DB connection
    private Facade facade = Facade.getFacade();

    /**
     * This method get the building. 
     * 
     * @param request - browser sends request to the server
     * @param response - server sends response back to the browser
     * @throws ServletException - håndtere request (data travels)
     * @throws IOException - input/output - handle hardware issue
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // setup navigation bar
        setupNavigation(request);

        switch (request.getServletPath()) {
            case "/building/insert":
                doGetInsert(request, response);
                break;
            case "/building/update":
                doGetUpdate(request, response);
                break;
            default:
                doGetView(request, response);
                break;
        }

    }

    /**
     * This method gets and show a single building
     * 
     * @param request - browser sends request to the server
     * @param response - server sends response back to the browser
     * @throws ServletException - håndtere request (data travels)
     * @throws IOException - input/output - handle hardware issue
     */
    private void doGetView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Building b = facade.getBuilding(id);
            b.setDocuments(facade.getFilesDocuments(b));
            b.setImages(facade.getFilesImages(b));
            //Save the variable
            request.setAttribute("b", b);
            Helper.forwardGet(request, response, "/viewBuilding.jsp");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            forwardGet(request, response, "/error.jsp");
        }
    }

    /**
     * This method forwards the user to an empty form in addUpdateBuilding with the organisation Id
     * 
     * @param request - browser sends request to the server
     * @param response - server sends response back to the browser
     * @throws ServletException - håndtere request (data travels)
     * @throws IOException - input/output - handle hardware issue
     */
    private void doGetInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //get the hidden "requestScope=oid" from addUpdateBuilding 
            int oId = Integer.parseInt(request.getParameter("orgid"));
            Org org = new Org();
            org.setId(oId);
            //saves the organisation Id
            request.setAttribute("org", org);
            request.setAttribute("url", request.getServletPath());
            request.setAttribute("action", "Tilføj");
            Helper.forwardGet(request, response, "/addUpdateBuilding.jsp");

        } catch (NullPointerException e) {
            request.setAttribute("error", e.getMessage());
            forwardGet(request, response, "/error.jsp");
        }
    }

    /**
     * This method forwards the user to addUpdateBuilding and fill out the form with the building information
     * - id, organisation and building is send with the request 
     * 
     * @param request - browser sends request to the server
     * @param response - server sends response back to the browser
     * @throws ServletException - handle request (data travels)
     * @throws IOException - input/output - handle hardware issue
     */
    private void doGetUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //organisation Id 
        try {
            //get the id from JSP/URL
            int id = Integer.parseInt(request.getParameter("id"));
            Building b = facade.getBuilding(id);
            //Save the variable
            request.setAttribute("b", b);
            request.setAttribute("org", b.getOrg());
            request.setAttribute("action", "Update");
            Helper.forward(request, response, "/addUpdateBuilding.jsp");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            forwardGet(request, response, "/error.jsp");
        }

    }

    /**
     * Is used when user press the add or update button  
     * 
     * @param request - browser sends request to the server
     * @param response - server sends response back to the browser
     * @throws ServletException - håndtere request (data travels)
     * @throws IOException - input/output - handle hardware issue
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getServletPath()) {
            case "/building/insert":
                doPostInsert(request, response);
                break;
            case "/building/update":
                doPostUpdate(request, response);
                break;
            default:
                forwardGet(request, response, "/home.jsp");
                break;
        }
    }

    /**
     * This method runs when a building is added.
     * When the user have filled in the form and click on submit,
     * there it connects to the DB and puts the data from the form into DB
     * 
     * @param request - browser sends request to the server
     * @param response - server sends response back to the browser
     * @throws ServletException - håndtere request (data travels)
     * @throws IOException - input/output - handle hardware issue
     */
    private void doPostInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Building b = doPostBoth(request);
            //insert to DB via facade
            int id = facade.insertBuilding(b);

            //hvis id er større end nul, så er der oprettet en bygning, og man får refereret id'et fra Databasen
            if (id > 0) {
                response.sendRedirect("/buildings?orgid=" + b.getOrg().getId());
            } else {
                Helper.forwardGet(request, response, "/building/insert?orgid=" + b.getOrg().getId());
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            forwardGet(request, response, "/error.jsp");
        }
    }

    /**
     * This method runs when a building has to be updatet
     * The fields in the form gets filled in with the building information,
     * when the user click submit the changes is being send to DB
     * 
     * @param request - browser sends request to the server
     * @param response - server sends response back to the browser
     * @throws ServletException - håndtere request (data travels)
     * @throws IOException - input/output - handle hardware issue
     */
    private void doPostUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Building b = doPostBoth(request);

            if (facade.updateBuilding(b)) {
                response.sendRedirect("/buildings?orgid=" + b.getOrg().getId());
            } else {
                Helper.forwardGet(request, response, "/building/update?id=" + b.getId());
            }
        } catch (PolygonException | NullPointerException e) {
            request.setAttribute("error", e.getMessage());
            forwardGet(request, response, "/error.jsp");
        }
    }

    /**
     * This method is used in doPostInsert and doPostUpdate
     * If user adds a building this method use the organisations Id and shows an empty form
     * and put the data into variables 
     * 
     * If user updates a building this method use the building Id to fill in the form with the information
     * 
     * 
     * @param request - browser sends request to server
     * @return This method only need to handle request and has to be used in add
     * and update doPostInsert and doPostUpdate.
     */
    private Building doPostBoth(HttpServletRequest request) {
        Building b = new Building();

        //this id is used for update
        b.setId(-1);
        if (request.getParameter("bId") != null && !request.getParameter("bId").equals("")) {
            b.setId(Integer.parseInt(request.getParameter("bId")));
        }

        //this id is used for add
        Org org = new Org();
        if (request.getParameter("oId") != null && !request.getParameter("orgId").equals("")) {
            org.setId(Integer.parseInt(request.getParameter("orgId")));
        }

        b.setOrg(org);

        //take the organisation out of the session 
        User user = (User) request.getSession().getAttribute("user");
        b.setOrg(user.getOrg());
        //BUILDING DATA from form put into variables
        String name = request.getParameter("Name");
        String address = request.getParameter("Address");
        String constructionYear = request.getParameter("ConstructionYear");
        String area = request.getParameter("Area");
        String currentUse = request.getParameter("CurrentUse");
        String previousUse = request.getParameter("PreviousUse");

        //use the variables with the data from the form
        b.setName(name);
        b.setAddress(address);
        b.setConstructionYear(Timestamp.valueOf("1990-01-01 00:00:00"));
        b.setArea(area);
        b.setCurrentUse(currentUse);
        b.setPreviousUse(previousUse);

        return b;
    }
//    /**
//     * Extracts file name from HTTP header content-disposition
//     */
//    private String extractFileName(Part part) {
//        String contentDisp = part.getHeader("content-disposition");
//        String[] items = contentDisp.split(";");
//        for (String s : items) {
//            if (s.trim().startsWith("filename")) {
//                return s.substring(s.indexOf("=") + 2, s.length() - 1);
//            }
//        }
//        return "";
//    }
}
