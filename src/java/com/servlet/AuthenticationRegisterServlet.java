package com.servlet;

import com.model.User;
import com.service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AuthenticationRegisterServlet", urlPatterns = {"/AuthenticationRegister"})
public class AuthenticationRegisterServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        this.userService = UserService.getInstance();
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
        try {
            User user = this.userService.register(request.getParameter("username"), request.getParameter("password"), request.getParameter("role"));
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath());
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AuthenticationRegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
