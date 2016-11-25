/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Facade;
import java.io.IOException;
import java.io.PrintWriter;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Helper;
import static util.Helper.forwardGet;
import model.Org;
import model.User;

/**
 * 
 * 
 * @author Tanja
 */
@WebServlet(name = "CustomerController", urlPatterns = {"/customer", "/custommer/insert","/customer/update"})
public class CustomerController extends HttpServlet {

    private final Facade facade = Facade.getFacade();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CustomerController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getServletPath()) {
            case "/custommer/insert":
                doGetInsert(request, response);
                break;
            case "/customer/update":
                doGetUpdate(request, response);
                break;
            default:
                doGetView(request, response);
        }

    }
    /**When this servlet is called, the doGetView method is invoked to send a form to the web browser
     * to pick up and show the HTML-document for already existing customer: viewCustomer.jsp page. 
     * By sending "id" and using
     * method setAttribute with "user" parameter, makes it possible to choose the right user.
     * 
     * @param request user id 
     * @param response is sent to viewCustomer HTTP protocol on "/viewCustomer" browser
     * @throws ServletException
     * @throws IOException 
     */

    private void doGetView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            User u = facade.getUser(id);

            request.setAttribute("u", u);
            Helper.forwardGet(request, response, "/viewCustomer.jsp");

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            forwardGet(request, response, "/error.jsp");
        }
    }
    /**doGetInsert method used to add a new customer by admin, sending "oid" object with getParameter methode.
     * Useren is forwarded by browser over to webside "addUpdateCustomer.jsp"
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */

    private void doGetInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int Oid = Integer.parseInt(request.getParameter("orgid"));
            Org org = new Org();
            org.setId(Oid);

            request.setAttribute("org", org);
            request.setAttribute("action", "Tilføj ny kunde");
            Helper.forwardGet(request, response, "/addUpdateCustomer.jsp");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            forwardGet(request, response, "/error.jsp");
        }
    }
    /**doGetUpdate method update user.
     * The admin is forwarded by browser over to webside "addUpdateCustomer.jsp" 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */

    private void doGetUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt("id");
            int u = Integer.parseInt("u");

            request.setAttribute("u", u);
            request.setAttribute("orgid", id);
            request.setAttribute("action", "Update");
            Helper.forwardGet(request, response, "/addUpdateCustomer.jsp");

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            forwardGet(request, response, "/error.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getServletPath()) {
            case "/custommer/insert":
                doGetInsert(request, response);
                break;
            case "/customer/update":
                doGetUpdate(request, response);
                break;
            default:
                forwardGet(request, response, "/home.jsp");

        }
    }
    /**doPost method is used for already existing customers. Method is used 
     * to communicate data from the HTML form addUpdateCustomer.jsp. The method
     * avoid showing parameters in the URL (treats the sensitive information)
     * 
     * @param request only from admin
     * @param response sending to http browser "/customer/insert?orgid="
     * @throws ServletException
     * @throws IOException 
     */

    private void doPostInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            User u = doPostgetCustomerFromForm(request);
            int id = facade.insertUser(u);

            if (id > 0) {
                Helper.forwardGet(request, response, "/customers?id=" + u.getOrg().getUsers());
            } else {
                Helper.forwardGet(request, response, "/customer/insert?orgid=" + u.getOrg().getUsers());
            }

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            forwardGet(request, response, "/error.jsp");
        }

    }
    /**doPostUpdate used to insert a sensitive information by admin
     * 
     * @param request
     * @param responseused 
     * @return
     * @throws ServletException
     * @throws IOException 
     */

    private User doPostUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            User u = doPostgetCustomerFromForm(request);
            int id = facade.insertUser(u);

            if (id > 0) {
                Helper.forwardGet(request, response, "/customers?id=" + u.getOrg().getUsers());

            } else {
                Helper.forwardGet(request, response, "/customer/update?orgid=" + u.getOrg().getUsers());
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            forwardGet(request, response, "/error.jsp");
        }
        return null;

    }
    /**The method used to insert infromation for a new customer and an organisation; 
     * to search for the existing customer and organisation in the DB
     * @param request
     * @return
     * @throws ServletException
     * @throws IOException 
     */

    private User doPostgetCustomerFromForm(HttpServletRequest request)
            throws ServletException, IOException {

        User u = new User();

        u.setId(-1);

        if (request.getParameter("uid") != null
                && !request.getParameter("uid").equals("")) {

            u.setId(Integer.parseInt(request.getParameter("uid")));
        }
        if (request.getParameter("orgid") != null
                && !request.getParameter("orgid").equals("")) {

            Org org = new Org();
            org.setId(Integer.parseInt(request.getParameter("orgid")));

            u.setOrg(org);

            Integer userid = Integer.parseInt(request.getParameter("uid"));
            String name = request.getParameter("Name");
            String email = request.getParameter("Email");
            String phone = request.getParameter("Phone");

            u.setId(userid);
            u.setName(name);
            u.setEmail(email);
            u.setPhone(phone);

        }
        return u;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
}
