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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet("/countrylanguages")
public class CountryLanguageServlet extends HttpServlet {
    private CountryLanguageService countryLanguageService = new CountryLanguageService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String countryCodeParam = request.getParameter("CountryCode");
        String languageParam = request.getParameter("Language");
        String isOfficialParam = request.getParameter("IsOfficial");
        String percentageParam = request.getParameter("Percentage");

        if (countryCodeParam != null || languageParam != null || isOfficialParam != null || percentageParam != null) {
            try {
                Double percentage = percentageParam != null ? Double.parseDouble(percentageParam) : null;

                List<CountryLanguage> countryLanguages = countryLanguageService.searchCountryLanguages(countryCodeParam, languageParam, isOfficialParam, percentage);
                JSONArray jsonArray = new JSONArray();

                for (CountryLanguage countryLanguage : countryLanguages) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("CountryCode", countryLanguage.getCountryCode());
                    jsonObject.put("Language", countryLanguage.getLanguage());
                    jsonObject.put("IsOfficial", countryLanguage.getIsOfficial());
                    jsonObject.put("Percentage", countryLanguage.getPercentage());
                    jsonArray.put(jsonObject);
                }

                out.println(jsonArray.toString());
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println("{\"error\":\"Invalid number format\"}");
            }
        } else {
            List<CountryLanguage> countryLanguages = countryLanguageService.tumCountryLanguageGetir();
            JSONArray jsonArray = new JSONArray();

            for (CountryLanguage countryLanguage : countryLanguages) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("CountryCode", countryLanguage.getCountryCode());
                jsonObject.put("Language", countryLanguage.getLanguage());
                jsonObject.put("IsOfficial", countryLanguage.getIsOfficial());
                jsonObject.put("Percentage", countryLanguage.getPercentage());
                jsonArray.put(jsonObject);
            }

            out.println(jsonArray.toString());
        }

        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String countryCode = request.getParameter("CountryCode");
        String language = request.getParameter("Language");
        String isOfficial = request.getParameter("IsOfficial");
        double percentage = Double.parseDouble(request.getParameter("Percentage"));

        CountryLanguage countryLanguage = new CountryLanguage(countryCode, language, isOfficial, percentage);
        countryLanguageService.ulkeDiliEkle(countryLanguage);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String countryCode = request.getParameter("CountryCode");
        String language = request.getParameter("Language");
        String isOfficial = request.getParameter("IsOfficial");
        double percentage = Double.parseDouble(request.getParameter("Percentage"));

        CountryLanguage countryLanguage = new CountryLanguage(countryCode, language, isOfficial, percentage);
        boolean isUpdated = countryLanguageService.ulkeDiliGuncelle(countryLanguage);
        if (isUpdated) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{\"error\":\"CountryLanguage not found\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String countryCodeParam = request.getParameter("CountryCode");
        String languageParam = request.getParameter("Language");
        String isOfficialParam = request.getParameter("IsOfficial");
        String percentageParam = request.getParameter("Percentage");

        try {
            Double percentage = percentageParam != null ? Double.parseDouble(percentageParam) : null;

            boolean isDeleted = countryLanguageService.ulkeDiliSil(countryCodeParam, languageParam, isOfficialParam, percentage);
            if (isDeleted) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\":\"CountryLanguage not found\"}");
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Invalid number format\"}");
        }
    }
}
