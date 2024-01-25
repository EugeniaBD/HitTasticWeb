/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet;

import com.model.User;
import com.service.UserService;
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
@WebServlet(name = "AuthenticationLoginServlet", urlPatterns = {"/AuthenticationLogin"})
public class AuthenticationLoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        this.userService = UserService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            user = this.userService.login(username, password);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AuthenticationLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (user != null) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath());
            return;
        }

        req.setAttribute("error", "Incorrect username or password");
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

}
