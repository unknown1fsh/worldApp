package com.example.world.servlet;

import com.example.world.model.City;
import com.example.world.service.CityService;
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

@WebServlet("/cities")
public class CityServlet extends HttpServlet {
    private CityService cityService = new CityService();

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
            String idParam = request.getParameter("ID");
            String nameParam = request.getParameter("Name");
            String countryCodeParam = request.getParameter("CountryCode");
            String districtParam = request.getParameter("District");
            String populationParam = request.getParameter("Population");

            List<City> cities;
            if (idParam != null || nameParam != null || countryCodeParam != null || districtParam != null || populationParam != null) {
                Integer id = idParam != null ? Integer.parseInt(idParam) : null;
                Integer population = populationParam != null ? Integer.parseInt(populationParam) : null;
                cities = cityService.searchCities(id, nameParam, countryCodeParam, districtParam, population);
            } else {
                cities = cityService.tumSehirleriGetir();
            }

            JSONArray jsonArray = new JSONArray();
            for (City city : cities) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("ID", city.getId());
                jsonObject.put("Name", city.getName());
                jsonObject.put("CountryCode", city.getCountryCode());
                jsonObject.put("District", city.getDistrict());
                jsonObject.put("Population", city.getPopulation());
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
                jsonObject.put("ID", request.getParameter("ID"));
                jsonObject.put("Name", request.getParameter("Name"));
                jsonObject.put("CountryCode", request.getParameter("CountryCode"));
                jsonObject.put("District", request.getParameter("District"));
                jsonObject.put("Population", request.getParameter("Population"));
            }

            int id = jsonObject.getInt("ID");
            String name = jsonObject.getString("Name");
            String countryCode = jsonObject.getString("CountryCode");
            String district = jsonObject.getString("District");
            int population = jsonObject.getInt("Population");

            City city = new City(id, name, countryCode, district, population);
            cityService.sehirEkle(city);
            
            response.setStatus(HttpServletResponse.SC_CREATED);
            JSONObject success = new JSONObject();
            success.put("message", "Şehir başarıyla eklendi");
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
                jsonObject.put("ID", request.getParameter("ID"));
                jsonObject.put("Name", request.getParameter("Name"));
                jsonObject.put("CountryCode", request.getParameter("CountryCode"));
                jsonObject.put("District", request.getParameter("District"));
                jsonObject.put("Population", request.getParameter("Population"));
            }

            int id = jsonObject.getInt("ID");
            String name = jsonObject.getString("Name");
            String countryCode = jsonObject.getString("CountryCode");
            String district = jsonObject.getString("District");
            int population = jsonObject.getInt("Population");

            City city = new City(id, name, countryCode, district, population);
            boolean isUpdated = cityService.sehirGuncelle(city);
            
            if (isUpdated) {
                response.setStatus(HttpServletResponse.SC_OK);
                JSONObject success = new JSONObject();
                success.put("message", "Şehir başarıyla güncellendi");
                out.print(success.toString());
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                JSONObject error = new JSONObject();
                error.put("error", "Şehir bulunamadı");
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
            String idParam = request.getParameter("ID");
            String nameParam = request.getParameter("Name");
            String countryCodeParam = request.getParameter("CountryCode");
            String districtParam = request.getParameter("District");
            String populationParam = request.getParameter("Population");

            Integer id = idParam != null ? Integer.parseInt(idParam) : null;
            Integer population = populationParam != null ? Integer.parseInt(populationParam) : null;

            boolean isDeleted = cityService.sehirSil(id, nameParam, countryCodeParam, districtParam, population);
            
            if (isDeleted) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                JSONObject error = new JSONObject();
                error.put("error", "Şehir bulunamadı");
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
