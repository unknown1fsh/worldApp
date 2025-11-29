package com.example.world.servlet;

import com.example.world.model.CountryLanguage;
import com.example.world.service.CountryLanguageService;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/countrylanguages")
public class CountryLanguageServlet extends HttpServlet {
    private CountryLanguageService countryLanguageService = new CountryLanguageService();

    private void setCorsHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }

    private String readRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString();
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String countryCodeParam = request.getParameter("CountryCode");
            String languageParam = request.getParameter("Language");
            String isOfficialParam = request.getParameter("IsOfficial");
            String percentageParam = request.getParameter("Percentage");

            List<CountryLanguage> countryLanguages;
            if (countryCodeParam != null || languageParam != null || isOfficialParam != null || percentageParam != null) {
                Double percentage = percentageParam != null ? Double.parseDouble(percentageParam) : null;
                countryLanguages = countryLanguageService.searchCountryLanguages(countryCodeParam, languageParam, isOfficialParam, percentage);
            } else {
                countryLanguages = countryLanguageService.tumCountryLanguageGetir();
            }

            JSONArray jsonArray = new JSONArray();
            for (CountryLanguage countryLanguage : countryLanguages) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("CountryCode", countryLanguage.getCountryCode());
                jsonObject.put("Language", countryLanguage.getLanguage());
                jsonObject.put("IsOfficial", countryLanguage.getIsOfficial());
                jsonObject.put("Percentage", countryLanguage.getPercentage());
                jsonArray.put(jsonObject);
            }

            out.print(jsonArray.toString());
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JSONObject error = new JSONObject();
            error.put("error", "Geçersiz sayı formatı");
            out.print(error.toString());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject error = new JSONObject();
            error.put("error", "Sunucu hatası: " + e.getMessage());
            out.print(error.toString());
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            JSONObject jsonObject;
            String body = readRequestBody(request);
            
            if (body != null && !body.isEmpty()) {
                jsonObject = new JSONObject(body);
            } else {
                // Form parametrelerinden oku
                jsonObject = new JSONObject();
                jsonObject.put("CountryCode", request.getParameter("CountryCode"));
                jsonObject.put("Language", request.getParameter("Language"));
                jsonObject.put("IsOfficial", request.getParameter("IsOfficial"));
                jsonObject.put("Percentage", request.getParameter("Percentage"));
            }

            String countryCode = jsonObject.getString("CountryCode");
            String language = jsonObject.getString("Language");
            String isOfficial = jsonObject.getString("IsOfficial");
            double percentage = jsonObject.getDouble("Percentage");

            CountryLanguage countryLanguage = new CountryLanguage(countryCode, language, isOfficial, percentage);
            countryLanguageService.ulkeDiliEkle(countryLanguage);
            
            response.setStatus(HttpServletResponse.SC_CREATED);
            JSONObject success = new JSONObject();
            success.put("message", "Ülke dili başarıyla eklendi");
            out.print(success.toString());
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JSONObject error = new JSONObject();
            error.put("error", "Veritabanı hatası: " + e.getMessage());
            out.print(error.toString());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JSONObject error = new JSONObject();
            error.put("error", "Geçersiz istek: " + e.getMessage());
            out.print(error.toString());
        } finally {
            out.close();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            JSONObject jsonObject;
            String body = readRequestBody(request);
            
            if (body != null && !body.isEmpty()) {
                jsonObject = new JSONObject(body);
            } else {
                // Form parametrelerinden oku
                jsonObject = new JSONObject();
                jsonObject.put("CountryCode", request.getParameter("CountryCode"));
                jsonObject.put("Language", request.getParameter("Language"));
                jsonObject.put("IsOfficial", request.getParameter("IsOfficial"));
                jsonObject.put("Percentage", request.getParameter("Percentage"));
            }

            String countryCode = jsonObject.getString("CountryCode");
            String language = jsonObject.getString("Language");
            String isOfficial = jsonObject.getString("IsOfficial");
            double percentage = jsonObject.getDouble("Percentage");

            CountryLanguage countryLanguage = new CountryLanguage(countryCode, language, isOfficial, percentage);
            boolean isUpdated = countryLanguageService.ulkeDiliGuncelle(countryLanguage);
            
            if (isUpdated) {
                response.setStatus(HttpServletResponse.SC_OK);
                JSONObject success = new JSONObject();
                success.put("message", "Ülke dili başarıyla güncellendi");
                out.print(success.toString());
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                JSONObject error = new JSONObject();
                error.put("error", "Ülke dili bulunamadı");
                out.print(error.toString());
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JSONObject error = new JSONObject();
            error.put("error", "Geçersiz istek: " + e.getMessage());
            out.print(error.toString());
        } finally {
            out.close();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String countryCodeParam = request.getParameter("CountryCode");
            String languageParam = request.getParameter("Language");
            String isOfficialParam = request.getParameter("IsOfficial");
            String percentageParam = request.getParameter("Percentage");

            Double percentage = percentageParam != null ? Double.parseDouble(percentageParam) : null;

            boolean isDeleted = countryLanguageService.ulkeDiliSil(countryCodeParam, languageParam, isOfficialParam, percentage);
            
            if (isDeleted) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                JSONObject error = new JSONObject();
                error.put("error", "Ülke dili bulunamadı");
                out.print(error.toString());
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JSONObject error = new JSONObject();
            error.put("error", "Geçersiz sayı formatı");
            out.print(error.toString());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject error = new JSONObject();
            error.put("error", "Sunucu hatası: " + e.getMessage());
            out.print(error.toString());
        } finally {
            out.close();
        }
    }
}
