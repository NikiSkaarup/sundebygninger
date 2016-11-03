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
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static util.Helper.extractFileName;
import static util.Helper.generateSemiUniqueFileName;

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
        if (!request.getParameter("id").equals(""))
            id = Integer.parseInt(request.getParameter("id"));

        int buildingId = -1;
        if (!request.getParameter("buildingId").equals(""))
            buildingId = Integer.parseInt(request.getParameter("buildingId"));

        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + SAVE_DIR;

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists())
            fileSaveDir.mkdir();

        String name = "", fileExt, fileName = "";

        for (Part part : request.getParts()) {
            if (!part.getName().equals("file"))
                continue;
            if (name.equals("")) {
                name = extractFileName(part);
                fileExt = name.split("\\.")[1];
                do fileName = generateSemiUniqueFileName() + "." + fileExt;
                while (new File(savePath + File.separator + fileName).exists());
            }
            part.write(savePath + File.separator + fileName);
        }

        try {
            Connection conn = data.DB.getConnection();
            PreparedStatement stmt;
            if (id < 0) {
                stmt = conn.prepareStatement("INSERT INTO Image " +
                        "(Name, FkBuildingId, Path) VALUES (?,?,?);");

                stmt.setString(1, name);
                stmt.setInt(2, buildingId);
                stmt.setString(3, fileName);
            } else {
                stmt = conn.prepareStatement("UPDATE Image SET " +
                        "NAME=?, Path=? WHERE Id=? AND FkBuildingId=?");

                stmt.setString(1, name);
                stmt.setString(2, fileName);
                stmt.setInt(3, id);
                stmt.setInt(4, buildingId);
            }
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (buildingId < 0)
            response.sendRedirect("/building?id=" + buildingId);
        else
            response.sendRedirect("/allBuildings.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {

        int id = -1;
        if (request.getParameter("edit") == null) {
            request.setAttribute("action", "add");
        } else {
            request.setAttribute("action", "edit");
            id = Integer.parseInt(request.getParameter("edit"));
        }

        int buildingId = -1;
        if (request.getParameter("building") != null)
            buildingId = Integer.parseInt(request.getParameter("building"));

        if (id > 0 && buildingId > 0) {
            try {
                Connection conn = data.DB.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT " +
                        "Name, Path FROM Image WHERE Id=? AND " +
                        "FkBuildingId=?");
                stmt.setInt(1, id);
                stmt.setInt(2, buildingId);

                ResultSet rs = stmt.executeQuery();
                conn.close();

                if (rs.getFetchSize() == 1) {
                    Image img = new Image();

                    rs.next();
                    img.setId(id);
                    Building building = new Building();
                    building.setId(buildingId);
                    img.setBuilding(building);

                    img.setName(rs.getString("Name"));
                    img.setPath(rs.getString("Path"));

                    request.setAttribute("iId", id);
                    request.setAttribute("bId", buildingId);
                    request.setAttribute("name", rs.getString
                            ("Name"));
                    request.setAttribute("path", rs.getString
                            ("Path"));

                    request.setAttribute("image", img);
                } else {
                    if (buildingId < 0)
                        response.sendRedirect("MainMenuIsh");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            request.setAttribute("bId", buildingId);

        RequestDispatcher rd = request.getRequestDispatcher("image.jsp");
        rd.forward(request, response);
    }
}
