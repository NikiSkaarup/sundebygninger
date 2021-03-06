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
//import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import static util.Helper.forwardGet;
//import java.lang.Throwable;
import exceptions.PolygonException;

/**
 *
 * @author Tanja
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController",
        "/login"})
public class LoginController extends HttpServlet {

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
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param req servlet request
     * @param res servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        //processRequest(request, response);
        forwardGet(req, res, "/login.jsp");
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

        Facade facade = Facade.getFacade();
        try {
            String mail = request.getParameter("email");
            String pass = request.getParameter("password");

            try {

                User u = facade.getUserLogin(mail, pass);

                if (u != null) {
                    request.getSession().setAttribute("user", u);
                    response.sendRedirect(request.getContextPath() + "/home");

                } else {
                    request.setAttribute("error", "Unknown login, please try again");
                    forwardGet(request, response, "/login.jsp");
                }
            } catch (PolygonException e) {
                request.setAttribute("error", "Unknown login, please try again");
                forwardGet(request, response, "/login.jsp");
            }

        } catch (NullPointerException e) {
            request.setAttribute("error", "doPost: " + e.getMessage());
            forwardGet(request, response, "/error.jsp");
        }
    }

}
