package com.example.world.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class WelcomeServlet extends HttpServlet {
    
    private void setCorsHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        setCorsHeaders(response);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html lang='tr'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>WorldApp API</title>");
        out.println("    <style>");
        out.println("        body { font-family: Arial, sans-serif; margin: 40px; background: #f5f5f5; }");
        out.println("        .container { max-width: 800px; margin: 0 auto; background: white; padding: 30px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
        out.println("        h1 { color: #667eea; }");
        out.println("        .endpoint { background: #f9f9f9; padding: 15px; margin: 10px 0; border-left: 4px solid #667eea; border-radius: 4px; }");
        out.println("        .method { display: inline-block; padding: 4px 8px; border-radius: 4px; font-weight: bold; margin-right: 10px; }");
        out.println("        .get { background: #4caf50; color: white; }");
        out.println("        .post { background: #2196f3; color: white; }");
        out.println("        .put { background: #ff9800; color: white; }");
        out.println("        .delete { background: #f44336; color: white; }");
        out.println("        code { background: #e0e0e0; padding: 2px 6px; border-radius: 3px; }");
        out.println("        a { color: #667eea; text-decoration: none; }");
        out.println("        a:hover { text-decoration: underline; }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");
        out.println("        <h1>🌍 WorldApp API</h1>");
        out.println("        <p>WorldApp RESTful API'ye hoş geldiniz!</p>");
        out.println("        <h2>API Endpoints</h2>");
        out.println("        <div class='endpoint'>");
        out.println("            <span class='method get'>GET</span>");
        out.println("            <code>/cities</code> - Tüm şehirleri listele veya filtrele");
        out.println("        </div>");
        out.println("        <div class='endpoint'>");
        out.println("            <span class='method post'>POST</span>");
        out.println("            <code>/cities</code> - Yeni şehir ekle");
        out.println("        </div>");
        out.println("        <div class='endpoint'>");
        out.println("            <span class='method put'>PUT</span>");
        out.println("            <code>/cities</code> - Şehir güncelle");
        out.println("        </div>");
        out.println("        <div class='endpoint'>");
        out.println("            <span class='method delete'>DELETE</span>");
        out.println("            <code>/cities</code> - Şehir sil");
        out.println("        </div>");
        out.println("        <div class='endpoint'>");
        out.println("            <span class='method get'>GET</span>");
        out.println("            <code>/countries</code> - Tüm ülkeleri listele veya filtrele");
        out.println("        </div>");
        out.println("        <div class='endpoint'>");
        out.println("            <span class='method post'>POST</span>");
        out.println("            <code>/countries</code> - Yeni ülke ekle");
        out.println("        </div>");
        out.println("        <div class='endpoint'>");
        out.println("            <span class='method put'>PUT</span>");
        out.println("            <code>/countries</code> - Ülke güncelle");
        out.println("        </div>");
        out.println("        <div class='endpoint'>");
        out.println("            <span class='method delete'>DELETE</span>");
        out.println("            <code>/countries</code> - Ülke sil");
        out.println("        </div>");
        out.println("        <div class='endpoint'>");
        out.println("            <span class='method get'>GET</span>");
        out.println("            <code>/countrylanguages</code> - Tüm ülke dillerini listele veya filtrele");
        out.println("        </div>");
        out.println("        <div class='endpoint'>");
        out.println("            <span class='method post'>POST</span>");
        out.println("            <code>/countrylanguages</code> - Yeni ülke dili ekle");
        out.println("        </div>");
        out.println("        <div class='endpoint'>");
        out.println("            <span class='method put'>PUT</span>");
        out.println("            <code>/countrylanguages</code> - Ülke dili güncelle");
        out.println("        </div>");
        out.println("        <div class='endpoint'>");
        out.println("            <span class='method delete'>DELETE</span>");
        out.println("            <code>/countrylanguages</code> - Ülke dili sil");
        out.println("        </div>");
        out.println("        <h2>API Dokümantasyonu</h2>");
        out.println("        <div class='endpoint'>");
        out.println("            <a href='/swagger' style='font-size: 18px; font-weight: bold;'>📚 Swagger UI ile API'yi Keşfedin</a>");
        out.println("        </div>");
        out.println("        <h2>Test Arayüzü</h2>");
        out.println("        <p>API'yi test etmek için <a href='test.html' target='_blank'>test.html</a> dosyasını tarayıcıda açın.</p>");
        out.println("        <p><strong>Not:</strong> test.html dosyasını doğrudan dosya sisteminden açmanız gerekiyor (file:// protokolü ile).</p>");
        out.println("    </div>");
        out.println("</body>");
        out.println("</html>");
    }
}

