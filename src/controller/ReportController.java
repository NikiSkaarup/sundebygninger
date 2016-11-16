package controller;


import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import domain.Facade;
import exceptions.PolygonException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import model.*;

/**
 * Created by Niki on 2016-10-26.
 *
 * @author Niki
 */
@WebServlet(name = "ReportController", urlPatterns = {"/report"})
public class ReportController extends javax.servlet.http.HttpServlet {

    HashMap<Integer, Room> rooms = new HashMap<>();

    /**
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest req,
                          javax.servlet.http.HttpServletResponse res)
            throws javax.servlet.ServletException, IOException {

        Facade facade = Facade.getFacade();

        Report report = new Report();
        Building b = null;
        if (req.getParameter("building") != null
                && !req.getParameter("building").equals("")) {
            try {
                int i = Integer.parseInt(req.getParameter("building"));
                b = facade.getBuilding(i);
            } catch (PolygonException ex) {
                Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        switcher(req);

        List<Comment> comments = new ArrayList<>();
        Comment roof = new Comment();
        roof.setComment(req.getParameter("roof"));
        comments.add(roof);
        Comment outerWalls = new Comment();
        outerWalls.setComment(req.getParameter("outerWalls"));
        comments.add(outerWalls);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax
            .servlet.http.HttpServletResponse response) throws javax.servlet
            .ServletException, IOException {

    }

    private void switcher(javax.servlet.http.HttpServletRequest req) {
        int rc = 1;
        String lastReTemp = "";
        while (req.getAttributeNames().hasMoreElements()) {
            String attrName = req.getAttributeNames().nextElement();
            String[] temp, reTemp, roTemp;
            Room r = new Room();

            temp = attrName.split("_");
            reTemp = temp[0].split("-");

            if (temp.length > 1) {
                roTemp = temp[1].split("-");
            } else {
                if (reTemp[0].equals("room")) {
                    lastReTemp = reTemp[1];
                    r.setNum(req.getParameter(attrName));
                    r.setComments(new ArrayList<>());
                    r.setDamageTypes(new ArrayList<>());

                    rooms.put(rc, r);
                    rc++;
                }
                continue;
            }

            if (roTemp.length == 1 && reTemp.length > 1) {
                // Room
                switch (roTemp[0]) {
                    case "damradio":
                        boolean dam = req.getParameter(attrName).equals("yes");
                        rooms.get(rc).setDamage(dam);
                        break;
                    case "date":
                        rooms.get(rc).setWhen(Timestamp.valueOf(
                                req.getParameter(attrName)));
                        break;
                    case "location":
                        rooms.get(rc).setWhere(req.getParameter(attrName));
                        break;
                    case "incident":
                        rooms.get(rc).setWhathappend(
                                req.getParameter(attrName));
                        break;
                    case "repair":
                        rooms.get(rc).setWhatwasfixed(
                                req.getParameter(attrName));
                        break;
                    case "moisture":
                    case "rot":
                    case "mold":
                    case "fire":
                    case "other":
                        DamageType dt = new DamageType();
                        dt.setId(Integer.parseInt(req.getParameter(attrName)));
                        rooms.get(rc).getDamageTypes().add(dt);
                        break;
                    case "moistradio":
                        boolean moist = req.getParameter(attrName)
                                .equals("yes");
                        rooms.get(rc).setDamage(moist);
                        break;
                    case "moisturescan":
                        rooms.get(rc).setMoistureScan(
                                req.getParameter(attrName));
                        break;
                    case "measurespot":
                        rooms.get(rc).setMoistureTarget(
                                req.getParameter(attrName));
                        break;
                    default:
                        break;
                }
            } else {
                // Comments
                switch (roTemp[0]) {
                    case "":
                        break;
                    default:
                        break;
                }
            }
        }
    }

}
