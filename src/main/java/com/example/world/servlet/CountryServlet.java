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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/countries")
public class CountryServlet extends HttpServlet {
    private CountryService countryService = new CountryService();

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

            List<Country> countries;
            if (codeParam != null || nameParam != null || continentParam != null || regionParam != null || 
                surfaceAreaParam != null || indepYearParam != null || populationParam != null || 
                lifeExpectancyParam != null || gnpParam != null || gnpOldParam != null ||
                localNameParam != null || governmentFormParam != null || headOfStateParam != null || 
                capitalParam != null || code2Param != null) {
                
                Integer surfaceArea = surfaceAreaParam != null ? Integer.parseInt(surfaceAreaParam) : null;
                Integer indepYear = indepYearParam != null ? Integer.parseInt(indepYearParam) : null;
                Integer population = populationParam != null ? Integer.parseInt(populationParam) : null;
                Double lifeExpectancy = lifeExpectancyParam != null ? Double.parseDouble(lifeExpectancyParam) : null;
                Double gnp = gnpParam != null ? Double.parseDouble(gnpParam) : null;
                Double gnpOld = gnpOldParam != null ? Double.parseDouble(gnpOldParam) : null;
                Integer capital = capitalParam != null ? Integer.parseInt(capitalParam) : null;
                
                countries = countryService.searchCountries(codeParam, nameParam, continentParam, regionParam, 
                    surfaceArea, indepYear, population, lifeExpectancy, gnp, gnpOld, localNameParam, 
                    governmentFormParam, headOfStateParam, capital, code2Param);
            } else {
                countries = countryService.tumUlkeleriGetir();
            }

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
                jsonObject.put("Code", request.getParameter("Code"));
                jsonObject.put("Name", request.getParameter("Name"));
                jsonObject.put("Continent", request.getParameter("Continent"));
                jsonObject.put("Region", request.getParameter("Region"));
                jsonObject.put("SurfaceArea", request.getParameter("SurfaceArea"));
                jsonObject.put("IndepYear", request.getParameter("IndepYear"));
                jsonObject.put("Population", request.getParameter("Population"));
                jsonObject.put("LifeExpectancy", request.getParameter("LifeExpectancy"));
                jsonObject.put("GNP", request.getParameter("GNP"));
                jsonObject.put("GNPOld", request.getParameter("GNPOld"));
                jsonObject.put("LocalName", request.getParameter("LocalName"));
                jsonObject.put("GovernmentForm", request.getParameter("GovernmentForm"));
                jsonObject.put("HeadOfState", request.getParameter("HeadOfState"));
                jsonObject.put("Capital", request.getParameter("Capital"));
                jsonObject.put("Code2", request.getParameter("Code2"));
            }

            String code = jsonObject.getString("Code");
            String name = jsonObject.getString("Name");
            String continent = jsonObject.getString("Continent");
            String region = jsonObject.getString("Region");
            double surfaceArea = jsonObject.getDouble("SurfaceArea");
            Integer indepYear = jsonObject.isNull("IndepYear") ? null : jsonObject.getInt("IndepYear");
            int population = jsonObject.getInt("Population");
            Double lifeExpectancy = jsonObject.isNull("LifeExpectancy") ? null : jsonObject.getDouble("LifeExpectancy");
            Double gnp = jsonObject.isNull("GNP") ? null : jsonObject.getDouble("GNP");
            Double gnpOld = jsonObject.isNull("GNPOld") ? null : jsonObject.getDouble("GNPOld");
            String localName = jsonObject.getString("LocalName");
            String governmentForm = jsonObject.getString("GovernmentForm");
            String headOfState = jsonObject.getString("HeadOfState");
            Integer capital = jsonObject.isNull("Capital") ? null : jsonObject.getInt("Capital");
            String code2 = jsonObject.getString("Code2");

            Country country = new Country(code, name, continent, region, surfaceArea, indepYear, population, 
                lifeExpectancy, gnp, gnpOld, localName, governmentForm, headOfState, capital, code2);
            countryService.ulkeEkle(country);
            
            response.setStatus(HttpServletResponse.SC_CREATED);
            JSONObject success = new JSONObject();
            success.put("message", "Ülke başarıyla eklendi");
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
                jsonObject.put("Code", request.getParameter("Code"));
                jsonObject.put("Name", request.getParameter("Name"));
                jsonObject.put("Continent", request.getParameter("Continent"));
                jsonObject.put("Region", request.getParameter("Region"));
                jsonObject.put("SurfaceArea", request.getParameter("SurfaceArea"));
                jsonObject.put("IndepYear", request.getParameter("IndepYear"));
                jsonObject.put("Population", request.getParameter("Population"));
                jsonObject.put("LifeExpectancy", request.getParameter("LifeExpectancy"));
                jsonObject.put("GNP", request.getParameter("GNP"));
                jsonObject.put("GNPOld", request.getParameter("GNPOld"));
                jsonObject.put("LocalName", request.getParameter("LocalName"));
                jsonObject.put("GovernmentForm", request.getParameter("GovernmentForm"));
                jsonObject.put("HeadOfState", request.getParameter("HeadOfState"));
                jsonObject.put("Capital", request.getParameter("Capital"));
                jsonObject.put("Code2", request.getParameter("Code2"));
            }

            String code = jsonObject.getString("Code");
            String name = jsonObject.getString("Name");
            String continent = jsonObject.getString("Continent");
            String region = jsonObject.getString("Region");
            double surfaceArea = jsonObject.getDouble("SurfaceArea");
            Integer indepYear = jsonObject.isNull("IndepYear") ? null : jsonObject.getInt("IndepYear");
            int population = jsonObject.getInt("Population");
            Double lifeExpectancy = jsonObject.isNull("LifeExpectancy") ? null : jsonObject.getDouble("LifeExpectancy");
            Double gnp = jsonObject.isNull("GNP") ? null : jsonObject.getDouble("GNP");
            Double gnpOld = jsonObject.isNull("GNPOld") ? null : jsonObject.getDouble("GNPOld");
            String localName = jsonObject.getString("LocalName");
            String governmentForm = jsonObject.getString("GovernmentForm");
            String headOfState = jsonObject.getString("HeadOfState");
            Integer capital = jsonObject.isNull("Capital") ? null : jsonObject.getInt("Capital");
            String code2 = jsonObject.getString("Code2");

            Country country = new Country(code, name, continent, region, surfaceArea, indepYear, population, 
                lifeExpectancy, gnp, gnpOld, localName, governmentForm, headOfState, capital, code2);
            boolean isUpdated = countryService.ulkeGuncelle(country);
            
            if (isUpdated) {
                response.setStatus(HttpServletResponse.SC_OK);
                JSONObject success = new JSONObject();
                success.put("message", "Ülke başarıyla güncellendi");
                out.print(success.toString());
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                JSONObject error = new JSONObject();
                error.put("error", "Ülke bulunamadı");
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

            Integer surfaceArea = surfaceAreaParam != null ? Integer.parseInt(surfaceAreaParam) : null;
            Integer indepYear = indepYearParam != null ? Integer.parseInt(indepYearParam) : null;
            Integer population = populationParam != null ? Integer.parseInt(populationParam) : null;
            Double lifeExpectancy = lifeExpectancyParam != null ? Double.parseDouble(lifeExpectancyParam) : null;
            Double gnp = gnpParam != null ? Double.parseDouble(gnpParam) : null;
            Double gnpOld = gnpOldParam != null ? Double.parseDouble(gnpOldParam) : null;
            Integer capital = capitalParam != null ? Integer.parseInt(capitalParam) : null;

            boolean isDeleted = countryService.ulkeSil(codeParam, nameParam, continentParam, regionParam, 
                surfaceArea, indepYear, population, lifeExpectancy, gnp, gnpOld, localNameParam, 
                governmentFormParam, headOfStateParam, capital, code2Param);
            
            if (isDeleted) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                JSONObject error = new JSONObject();
                error.put("error", "Ülke bulunamadı");
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
