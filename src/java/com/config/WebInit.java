package com.config;

import com.repository.DatabaseConnection;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author eugen
 */

@WebListener
public class WebInit implements ServletContainerInitializer{    

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("ServletContainerInitializer.onStartup");
        try {
            DatabaseConnection.getConnection();
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(WebInit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
