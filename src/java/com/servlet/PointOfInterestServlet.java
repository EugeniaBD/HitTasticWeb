package com.servlet;

import com.enums.PointOfInterestType;
import com.service.PointOfInterestService;
import com.model.PointOfInterest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eugen
 */
@WebServlet(name = "PointOfInterestServlet", urlPatterns = {"/PointOfInterestServlet"})
public class PointOfInterestServlet extends HttpServlet {

    private PointOfInterestService pointOfInterestService;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        this.pointOfInterestService = new PointOfInterestService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idString = request.getParameter("id");
            String action = request.getParameter("action");
            PointOfInterest poi = null;
            
            if(action != null && action.equals("like")){
                this.pointOfInterestService.like(Integer.parseInt(idString));
            }
            
            poi = this.pointOfInterestService.findById(Integer.parseInt(idString));
            request.setAttribute("pointOfInterest", poi);
            
            if(action != null && action.equals("update")) {
                request.getRequestDispatcher("pointofinterestform.jsp").forward(request, response);            
            } else {
                request.getRequestDispatcher("pointofinterest.jsp").forward(request, response);            
            }
            
        } catch (IOException | ClassNotFoundException | NumberFormatException | SQLException ex) {
            Logger.getLogger(PointOfInterestServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        try {
            System.out.println(request.getServletPath().concat("-calling"));
            String idString = request.getParameter("id");
            String name = request.getParameter("name");
            String borough = request.getParameter("borough");
            String location = request.getParameter("location");
            String description = request.getParameter("description");
            String type = request.getParameter("type");
            System.out.println(idString);
            if(idString != null) {
                id = Integer.parseInt(idString);
                this.pointOfInterestService.updatePointOfInterest(id, name, borough, location, description, PointOfInterestType.valueOf(type));
            } else {
                id = this.pointOfInterestService.savePointOfInterest(name, borough, location, description, PointOfInterestType.valueOf(type));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PointOfInterestServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.sendRedirect(request.getContextPath().concat(request.getServletPath()).concat("?id=" + id));
    }

}
