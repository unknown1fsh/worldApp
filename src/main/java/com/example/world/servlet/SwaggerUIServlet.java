package com.example.world.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/swagger")
public class SwaggerUIServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html lang='tr'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>WorldApp API - Swagger UI</title>");
        out.println("    <link rel='stylesheet' type='text/css' href='https://unpkg.com/swagger-ui-dist@5.10.3/swagger-ui.css' />");
        out.println("    <link rel='stylesheet' type='text/css' href='https://unpkg.com/swagger-ui-dist@5.10.3/swagger-ui-standalone-preset.css' />");
        out.println("    <style>");
        out.println("        html { box-sizing: border-box; overflow: -moz-scrollbars-vertical; overflow-y: scroll; }");
        out.println("        *, *:before, *:after { box-sizing: inherit; }");
        out.println("        body { margin:0; background: #fafafa; }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div id='swagger-ui'></div>");
        out.println("    <script src='https://unpkg.com/swagger-ui-dist@5.10.3/swagger-ui-bundle.js'></script>");
        out.println("    <script src='https://unpkg.com/swagger-ui-dist@5.10.3/swagger-ui-standalone-preset.js'></script>");
        out.println("    <script>");
        out.println("        window.onload = function() {");
        out.println("            const ui = SwaggerUIBundle({");
        out.println("                url: '/openapi.json',");
        out.println("                dom_id: '#swagger-ui',");
        out.println("                deepLinking: true,");
        out.println("                presets: [");
        out.println("                    SwaggerUIBundle.presets.apis,");
        out.println("                    SwaggerUIStandalonePreset");
        out.println("                ],");
        out.println("                plugins: [");
        out.println("                    SwaggerUIBundle.plugins.DownloadUrl");
        out.println("                ],");
        out.println("                layout: 'StandaloneLayout',");
        out.println("                validatorUrl: null,");
        out.println("                docExpansion: 'list',");
        out.println("                filter: true,");
        out.println("                showExtensions: true,");
        out.println("                showCommonExtensions: true");
        out.println("            });");
        out.println("        };");
        out.println("    </script>");
        out.println("</body>");
        out.println("</html>");
    }
}

