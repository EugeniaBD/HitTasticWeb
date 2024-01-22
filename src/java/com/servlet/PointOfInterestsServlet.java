package com.servlet;

import com.service.PointOfInterestService;
import com.enums.PointOfInterestType;
import com.model.PointOfInterest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PointOfInterestsServlet", urlPatterns = {"/PointOfInterestsServlet"})
public class PointOfInterestsServlet extends HttpServlet {

    private PointOfInterestService pointOfInterestService;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        this.pointOfInterestService = new PointOfInterestService();
    }

    @Override
    public void destroy() {
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
        System.out.println("destory()");
    }

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

        String idString = request.getParameter("id");
        String action = request.getParameter("action");
        String searchType = request.getParameter("searchType");
        
        try {
            if(action != null && action.equals("delete")) {
                this.pointOfInterestService.deleteById(Integer.parseInt(idString));
                StringBuilder urlBuilder = new StringBuilder(request.getContextPath().concat(request.getServletPath()).concat(String.format("?searchType=%s", searchType)));
                
                if(searchType.equals("type")){
                    urlBuilder.append(String.format("&searchByType=%s", request.getParameter("searchByType")));
                } else if(searchType.equals("location")){
                    urlBuilder.append(String.format("&searchByLocation=%s", request.getParameter("searchByLocation")));
                }
                response.sendRedirect(urlBuilder.toString());
                return;
            }
            List<PointOfInterest> pointOfInterests = null;
            if (searchType.equals("location")) {
                String searchByLocation = request.getParameter("searchByLocation");
                if (searchByLocation != null && !searchByLocation.isEmpty()) {
                        pointOfInterests = this.pointOfInterestService.searchByLocation(request.getParameter("searchByLocation"));
                }
                request.setAttribute("searchByLocation", searchByLocation == null ? "" : searchByLocation);
            }

            if (searchType.equals("type")) {
                String searchByType = request.getParameter("searchByType");
                if (searchByType != null && !searchByType.isEmpty()) {
                    try {
                        pointOfInterests = this.pointOfInterestService.seacrhByType(PointOfInterestType.valueOf(request.getParameter("searchByType")));
                    } catch (IOException | ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(PointOfInterestsServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                request.setAttribute("searchByLocation", "");
                request.setAttribute("searchByType", request.getParameter("searchByType"));
            }

            request.setAttribute("pointOfInterests", pointOfInterests);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PointOfInterestsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
