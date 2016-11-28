package controller;

import domain.Facade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
//import model.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import exceptions.PolygonException;
import model.Org;
import static util.Helper.forwardGet;

/**
 * 
 *
 * @author Tanja
 */
@WebServlet(name = "AllCustomerController", urlPatterns = {"/customers"})
public class AllCustomerController extends HttpServlet {

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
            out.println("<title>Servlet AllCustomerController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AllCustomerController at " + request.getContextPath() + "</h1>");
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

        try {

            Facade facade = Facade.getFacade();
            int id = Integer.parseInt(request.getParameter("oid"));
            Org org = new Org(id);

            List<User> userList = facade.getUsers(id);
            
            //save the variable
            request.setAttribute("org", org); 
            request.setAttribute("customers", userList);

            RequestDispatcher rd = request.getRequestDispatcher("/allCustomers.jsp");
            rd.forward(request, response);

        } catch (PolygonException p) {
            request.setAttribute("error", p.getMessage());
            forwardGet(request, response, "/error.jsp");

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
