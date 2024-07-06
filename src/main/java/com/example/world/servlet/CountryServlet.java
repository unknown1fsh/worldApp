package com.example.world.servlet;

import com.example.world.model.Country;
import com.example.world.service.CountryService;
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

@WebServlet("/countries")
public class CountryServlet extends HttpServlet {
    private CountryService countryService = new CountryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String codeParam = request.getParameter("Code");
        String nameParam = request.getParameter("Name");
        String continentParam = request.getParameter("Continent");
        String regionParam = request.getParameter("Region");
        String surfaceAreaParam = request.getParameter("SurfaceArea");
        String indepYearParam = request.getParameter("IndepYear");
        String populationParam = request.getParameter("Population");
        String lifeExpectancyParam = request.getParameter("LifeExpectancy");
        String gnpParam = request.getParameter("GNP");
        String gnpOldParam = request.getParameter("GNPOld");
        String localNameParam = request.getParameter("LocalName");
        String governmentFormParam = request.getParameter("GovernmentForm");
        String headOfStateParam = request.getParameter("HeadOfState");
        String capitalParam = request.getParameter("Capital");
        String code2Param = request.getParameter("Code2");

        if (codeParam != null || nameParam != null || continentParam != null || regionParam != null || surfaceAreaParam != null ||
                indepYearParam != null || populationParam != null || lifeExpectancyParam != null || gnpParam != null || gnpOldParam != null ||
                localNameParam != null || governmentFormParam != null || headOfStateParam != null || capitalParam != null || code2Param != null) {
            try {
                Integer surfaceArea = surfaceAreaParam != null ? Integer.parseInt(surfaceAreaParam) : null;
                Integer indepYear = indepYearParam != null ? Integer.parseInt(indepYearParam) : null;
                Integer population = populationParam != null ? Integer.parseInt(populationParam) : null;
                Double lifeExpectancy = lifeExpectancyParam != null ? Double.parseDouble(lifeExpectancyParam) : null;
                Double gnp = gnpParam != null ? Double.parseDouble(gnpParam) : null;
                Double gnpOld = gnpOldParam != null ? Double.parseDouble(gnpOldParam) : null;
                Integer capital = capitalParam != null ? Integer.parseInt(capitalParam) : null;

                List<Country> countries = countryService.searchCountries(codeParam, nameParam, continentParam, regionParam, surfaceArea, indepYear, population, lifeExpectancy, gnp, gnpOld, localNameParam, governmentFormParam, headOfStateParam, capital, code2Param);
                JSONArray jsonArray = new JSONArray();

                for (Country country : countries) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("Code", country.getCode());
                    jsonObject.put("Name", country.getName());
                    jsonObject.put("Continent", country.getContinent());
                    jsonObject.put("Region", country.getRegion());
                    jsonObject.put("SurfaceArea", country.getSurfaceArea());
                    jsonObject.put("IndepYear", country.getIndepYear());
                    jsonObject.put("Population", country.getPopulation());
                    jsonObject.put("LifeExpectancy", country.getLifeExpectancy());
                    jsonObject.put("GNP", country.getGnp());
                    jsonObject.put("GNPOld", country.getGnpOld());
                    jsonObject.put("LocalName", country.getLocalName());
                    jsonObject.put("GovernmentForm", country.getGovernmentForm());
                    jsonObject.put("HeadOfState", country.getHeadOfState());
                    jsonObject.put("Capital", country.getCapital());
                    jsonObject.put("Code2", country.getCode2());
                    jsonArray.put(jsonObject);
                }

                out.println(jsonArray.toString());
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println("{\"error\":\"Invalid number format\"}");
            }
        } else {
            List<Country> countries = countryService.tumUlkeleriGetir();
            JSONArray jsonArray = new JSONArray();

            for (Country country : countries) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("Code", country.getCode());
                jsonObject.put("Name", country.getName());
                jsonObject.put("Continent", country.getContinent());
                jsonObject.put("Region", country.getRegion());
                jsonObject.put("SurfaceArea", country.getSurfaceArea());
                jsonObject.put("IndepYear", country.getIndepYear());
                jsonObject.put("Population", country.getPopulation());
                jsonObject.put("LifeExpectancy", country.getLifeExpectancy());
                jsonObject.put("GNP", country.getGnp());
                jsonObject.put("GNPOld", country.getGnpOld());
                jsonObject.put("LocalName", country.getLocalName());
                jsonObject.put("GovernmentForm", country.getGovernmentForm());
                jsonObject.put("HeadOfState", country.getHeadOfState());
                jsonObject.put("Capital", country.getCapital());
                jsonObject.put("Code2", country.getCode2());
                jsonArray.put(jsonObject);
            }

            out.println(jsonArray.toString());
        }

        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("Code");
        String name = request.getParameter("Name");
        String continent = request.getParameter("Continent");
        String region = request.getParameter("Region");
        int surfaceArea = Integer.parseInt(request.getParameter("SurfaceArea"));
        int indepYear = Integer.parseInt(request.getParameter("IndepYear"));
        int population = Integer.parseInt(request.getParameter("Population"));
        double lifeExpectancy = Double.parseDouble(request.getParameter("LifeExpectancy"));
        double gnp = Double.parseDouble(request.getParameter("GNP"));
        double gnpOld = Double.parseDouble(request.getParameter("GNPOld"));
        String localName = request.getParameter("LocalName");
        String governmentForm = request.getParameter("GovernmentForm");
        String headOfState = request.getParameter("HeadOfState");
        int capital = Integer.parseInt(request.getParameter("Capital"));
        String code2 = request.getParameter("Code2");

        Country country = new Country(code, name, continent, region, surfaceArea, indepYear, population, lifeExpectancy, gnp, gnpOld, localName, governmentForm, headOfState, capital, code2);
        countryService.ulkeEkle(country);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("Code");
        String name = request.getParameter("Name");
        String continent = request.getParameter("Continent");
        String region = request.getParameter("Region");
        int surfaceArea = Integer.parseInt(request.getParameter("SurfaceArea"));
        int indepYear = Integer.parseInt(request.getParameter("IndepYear"));
        int population = Integer.parseInt(request.getParameter("Population"));
        double lifeExpectancy = Double.parseDouble(request.getParameter("LifeExpectancy"));
        double gnp = Double.parseDouble(request.getParameter("GNP"));
        double gnpOld = Double.parseDouble(request.getParameter("GNPOld"));
        String localName = request.getParameter("LocalName");
        String governmentForm = request.getParameter("GovernmentForm");
        String headOfState = request.getParameter("HeadOfState");
        int capital = Integer.parseInt(request.getParameter("Capital"));
        String code2 = request.getParameter("Code2");

        Country country = new Country(code, name, continent, region, surfaceArea, indepYear, population, lifeExpectancy, gnp, gnpOld, localName, governmentForm, headOfState, capital, code2);
        boolean isUpdated = countryService.ulkeGuncelle(country);
        if (isUpdated) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{\"error\":\"Country not found\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codeParam = request.getParameter("Code");
        String nameParam = request.getParameter("Name");
        String continentParam = request.getParameter("Continent");
        String regionParam = request.getParameter("Region");
        String surfaceAreaParam = request.getParameter("SurfaceArea");
        String indepYearParam = request.getParameter("IndepYear");
        String populationParam = request.getParameter("Population");
        String lifeExpectancyParam = request.getParameter("LifeExpectancy");
        String gnpParam = request.getParameter("GNP");
        String gnpOldParam = request.getParameter("GNPOld");
        String localNameParam = request.getParameter("LocalName");
        String governmentFormParam = request.getParameter("GovernmentForm");
        String headOfStateParam = request.getParameter("HeadOfState");
        String capitalParam = request.getParameter("Capital");
        String code2Param = request.getParameter("Code2");

        try {
            Integer surfaceArea = surfaceAreaParam != null ? Integer.parseInt(surfaceAreaParam) : null;
            Integer indepYear = indepYearParam != null ? Integer.parseInt(indepYearParam) : null;
            Integer population = populationParam != null ? Integer.parseInt(populationParam) : null;
            Double lifeExpectancy = lifeExpectancyParam != null ? Double.parseDouble(lifeExpectancyParam) : null;
            Double gnp = gnpParam != null ? Double.parseDouble(gnpParam) : null;
            Double gnpOld = gnpOldParam != null ? Double.parseDouble(gnpOldParam) : null;
            Integer capital = capitalParam != null ? Integer.parseInt(capitalParam) : null;

            boolean isDeleted = countryService.ulkeSil(codeParam, nameParam, continentParam, regionParam, surfaceArea, indepYear, population, lifeExpectancy, gnp, gnpOld, localNameParam, governmentFormParam, headOfStateParam, capital, code2Param);
            if (isDeleted) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\":\"Country not found\"}");
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Invalid number format\"}");
        }
    }
}
