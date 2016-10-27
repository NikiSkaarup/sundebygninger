package controller;

import model.Building;
import model.Image;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.jsp.jstl.sql.Result;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static manipulator.File.extractFileName;
import static manipulator.File.generateSemiUniqueFileName;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {

        int id = -1;
        if (request.getParameter("edit") != null)
            id = Integer.parseInt(request.getParameter("edit"));

        if (request.getParts().isEmpty())
            response.sendRedirect(request.getRequestURL().toString());

        int buildingId = -1;
        if (request.getParameter("edit") != null)
            buildingId = Integer.parseInt(request.getParameter("building"));

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
                statement = conn.prepareStatement("INSERT INTO Image " +
                        "(Name, FkBuildingId, Path) VALUES (?,?,?)");

                statement.setString(1, name);
                statement.setInt(2, buildingId);
                statement.setString(3, fileName);

            } else {
                statement = conn.prepareStatement("UPDATE Image SET " +
                        "NAME=?, Path=? WHERE Id=? AND FkBuildingId=?");

                statement.setString(1, name);
                statement.setString(2, fileName);
                statement.setInt(3, id);
                statement.setInt(4, buildingId);
            }

            statement.executeQuery();
            if (buildingId < 0)
                response.sendRedirect("/building?id=" + buildingId);
            else
                response.sendRedirect("MainMenuIsh");
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
            int id = Integer.parseInt(request.getParameter("edit"));
            int buildingId = -1;
            if (request.getParameter("building") != null)
                buildingId = Integer.parseInt(request.getParameter("building"));

            Connection conn = data.DB.getConnection();

            try {
                PreparedStatement stmt = conn.prepareStatement("SELECT Name, " +
                        "Path FROM Image WHERE Id=? AND FkBuildingId=?");
                stmt.setInt(1, id);
                stmt.setInt(2, buildingId);

                ResultSet resultSet = stmt.executeQuery();

                if (resultSet.getFetchSize() == 1) {
                    Image img = new Image();

                    resultSet.next();
                    img.setId(id);
                    Building building = new Building();
                    building.setId(buildingId);
                    img.setBuilding(building);

                    img.setName(resultSet.getString("Name"));
                    img.setPath(resultSet.getString("Path"));

                    request.setAttribute("image", img);
                } else {
                    if (buildingId < 0)
                        response.sendRedirect("/building?id=" + buildingId);
                    else
                        response.sendRedirect("MainMenuIsh");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        RequestDispatcher rd = request.getRequestDispatcher("image.jsp");
        rd.forward(request, response);
    }
}
