/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet;

import com.model.PointOfInterest;
import com.service.PointOfInterestService;
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
@WebServlet(name = "CommentServlet", urlPatterns = {"/CommentServlet"})
public class CommentServlet extends HttpServlet {

    private PointOfInterestService pointOfInterestService;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        this.pointOfInterestService = new PointOfInterestService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String pointOfInterestId = request.getParameter("pointOfInterestId");
            String action = request.getParameter("action");
            String commentId = request.getParameter("commentId");

            if(action.equals("delete")) {
                this.pointOfInterestService.deleteComment(Integer.parseInt(commentId));
            } else if (action.equals("authorise")){
                this.pointOfInterestService.auhtoriseComment(Integer.parseInt(commentId));
            }
            response.sendRedirect(request.getContextPath().concat("/PointOfInterestServlet?id=").concat(pointOfInterestId));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CommentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String pointOfInterestId = request.getParameter("pointOfInterestId");
            String commentId = request.getParameter("commentId");
            String text = request.getParameter("text");

            this.pointOfInterestService.saveComment(Integer.parseInt(pointOfInterestId), commentId, text);
            response.sendRedirect(request.getContextPath().concat("/PointOfInterestServlet?id=").concat(pointOfInterestId));
        } catch (IOException | ClassNotFoundException | NumberFormatException | SQLException ex) {
            Logger.getLogger(CommentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
