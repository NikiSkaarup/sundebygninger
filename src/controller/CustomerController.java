/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Facade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Org;
import model.User;

/**
 *
 * @author Tanja
 */
@WebServlet(name = "CustomerController", urlPatterns = {"/CustomerController"})
public class CustomerController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        Facade facade = Facade.getFacade();

        if (request.getParameter("Name") != null) {
            String name = request.getParameter("Name");
            String email = request.getParameter("Email");

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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);

            User u = new User();
            u.setId(-1);

            if (request.getParameter("uId") != null
                    && !request.getParameter("uId").equals("")) {

                u.setId(Integer.parseInt(request.getParameter("uId")));
            }
            if (request.getParameter("orgId") != null
                    && !request.getParameter("orgId").equals("")) {

                Org org = new Org();
                org.setId(Integer.parseInt(request.getParameter("orgid")));

                u.setOrg(org);
            }

            Integer userid = Integer.parseInt(request.getParameter("UserId"));
            String name = request.getParameter("Name");
            String email = request.getParameter("Email");
            String phone = request.getParameter("Phone");

            u.setId(userid);
            u.setName(name);
            u.setEmail(email);
            u.setPhone(phone);

        }
        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
    private void forward(HttpServletRequest request, HttpServletResponse response, String string) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/" + string);
        rd.forward(request, response);
    }

}
