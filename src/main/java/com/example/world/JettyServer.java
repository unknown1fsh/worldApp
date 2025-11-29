package com.example.world;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.example.world.config.DatabaseConnection;
import com.example.world.servlet.CityServlet;
import com.example.world.servlet.CountryServlet;
import com.example.world.servlet.CountryLanguageServlet;
import com.example.world.servlet.OpenApiServlet;
import com.example.world.servlet.SwaggerUIServlet;
import com.example.world.servlet.WelcomeServlet;

public class JettyServer {
    public static void main(String[] args) throws Exception {
        System.out.println("WorldApp başlatılıyor...");
        
        // Veritabanı bağlantısını test et
        try {
            System.out.println("Veritabanı bağlantısı kontrol ediliyor...");
            DatabaseConnection.testConnection();
            System.out.println("Veritabanı bağlantısı başarılı!");
        } catch (Exception e) {
            System.err.println("HATA: Veritabanı bağlantısı başarısız!");
            System.err.println("Lütfen MySQL'in çalıştığından ve 'world' veritabanının mevcut olduğundan emin olun.");
            System.err.println("Hata detayı: " + e.getMessage());
            System.exit(1);
        }
        
        Server server = new Server(8085);

        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");
        server.setHandler(handler);

        // Add servlets
        handler.addServlet(new ServletHolder(new WelcomeServlet()), "/");
        handler.addServlet(new ServletHolder(new SwaggerUIServlet()), "/swagger");
        handler.addServlet(new ServletHolder(new OpenApiServlet()), "/openapi.json");
        handler.addServlet(new ServletHolder(new CityServlet()), "/cities");
        handler.addServlet(new ServletHolder(new CountryServlet()), "/countries");
        handler.addServlet(new ServletHolder(new CountryLanguageServlet()), "/countrylanguages");

        server.start();
        System.out.println("Server başlatıldı: http://localhost:8085");
        System.out.println("Swagger UI: http://localhost:8085/swagger");
        System.out.println("OpenAPI Spec: http://localhost:8085/openapi.json");
        System.out.println("Test sayfası: test.html dosyasını tarayıcıda açın");
        server.join();
    }
}
