package com.example.procurement.shared.listener;

import com.example.procurement.shared.config.DatabaseInitializer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationStartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DatabaseInitializer.initializeSchema();
        sce.getServletContext().setAttribute("appName", "Departmental Procurement System");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Nothing to clean up for this simple application.
    }
}
