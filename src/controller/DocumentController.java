package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Niki on 2016-10-26.
 *
 * @author Niki
 */
@WebServlet(name = "DocumentController", urlPatterns = {"/document"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class DocumentController extends HttpServlet {

    private final String SAVE_DIR = "documents";

    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        int id = -1;
        if (request.getParameter("edit") != null)
            id = Integer.parseInt(request.getParameter("edit"));

        if (request.getParts().isEmpty())
            response.sendRedirect(request.getRequestURL().toString());

        int buildingId = Integer.parseInt(request.getParameter("building"));


        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + SAVE_DIR;

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists())
            fileSaveDir.mkdir();

        String name = extractFileName(request.getParts().iterator().next());

        String fileExt = name.split("\\.")[1];
        String fileName;
        do {
            fileName = generateSemiUniqueFileName() + "." + fileExt;
        }
        while (new File(savePath + File.separator + fileName).exists());

        for (Part part : request.getParts()) {
            part.write(savePath + File.separator + fileName);
        }

        Connection conn = data.DB.getConnection();
        try {
            PreparedStatement statement;
            if (id < 0) {
                statement = conn.prepareStatement("INSERT INTO Document " +
                        "(Name, FkBuildingId, Path) VALUES (?,?,?)");

                statement.setString(1, name);
                statement.setString(2, Integer.toString(buildingId));
                statement.setString(3, fileName);

            } else {
                statement = conn.prepareStatement("UPDATE Document SET " +
                        "NAME=?, Path=? WHERE Id=? AND FkBuildingId=?");

                statement.setString(1, name);
                statement.setString(2, fileName);
                statement.setString(3, Integer.toString(id));
                statement.setString(4, Integer.toString(buildingId));
            }

            statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        if (request.getParameter("edit") == null) {
            request.setAttribute("action", "add");
        } else {
            request.setAttribute("action", "edit");
        }
        RequestDispatcher rd = request.getRequestDispatcher("document.jsp");
        rd.forward(request, response);
    }

    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    private String generateSemiUniqueFileName() {
        long millis = System.currentTimeMillis();
        String datetime = (millis / 200) + "";
        return datetime + "_" + millis;
    }
}
