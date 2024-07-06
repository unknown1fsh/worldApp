package com.example.world;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.example.world.servlet.CityServlet;
import com.example.world.servlet.CountryServlet;
import com.example.world.servlet.CountryLanguageServlet;

public class JettyServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8085);

        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");
        server.setHandler(handler);

        // Add servlets
        handler.addServlet(new ServletHolder(new CityServlet()), "/cities");
        handler.addServlet(new ServletHolder(new CountryServlet()), "/countries");
        handler.addServlet(new ServletHolder(new CountryLanguageServlet()), "/countrylanguages");

        server.start();
        server.join();
    }
}
