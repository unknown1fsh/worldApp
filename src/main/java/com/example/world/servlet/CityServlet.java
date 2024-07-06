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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet("/cities")
public class CityServlet extends HttpServlet {
    private CityService cityService = new CityService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String idParam = request.getParameter("ID");
        String nameParam = request.getParameter("Name");
        String countryCodeParam = request.getParameter("CountryCode");
        String districtParam = request.getParameter("District");
        String populationParam = request.getParameter("Population");

        if (idParam != null || nameParam != null || countryCodeParam != null || districtParam != null || populationParam != null) {
            try {
                Integer id = idParam != null ? Integer.parseInt(idParam) : null;
                Integer population = populationParam != null ? Integer.parseInt(populationParam) : null;

                List<City> cities = cityService.searchCities(id, nameParam, countryCodeParam, districtParam, population);
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

                out.println(jsonArray.toString());
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println("{\"error\":\"Invalid number format\"}");
            }
        } else {
            List<City> cities = cityService.tumSehirleriGetir();
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

            out.println(jsonArray.toString());
        }

        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("ID"));
        String name = request.getParameter("Name");
        String countryCode = request.getParameter("CountryCode");
        String district = request.getParameter("District");
        int population = Integer.parseInt(request.getParameter("Population"));

        City city = new City(id, name, countryCode, district, population);
        cityService.sehirEkle(city);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("ID"));
        String name = request.getParameter("Name");
        String countryCode = request.getParameter("CountryCode");
        String district = request.getParameter("District");
        int population = Integer.parseInt(request.getParameter("Population"));

        City city = new City(id, name, countryCode, district, population);
        boolean isUpdated = cityService.sehirGuncelle(city);
        if (isUpdated) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{\"error\":\"City not found\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("ID");
        String nameParam = request.getParameter("Name");
        String countryCodeParam = request.getParameter("CountryCode");
        String districtParam = request.getParameter("District");
        String populationParam = request.getParameter("Population");

        try {
            Integer id = idParam != null ? Integer.parseInt(idParam) : null;
            Integer population = populationParam != null ? Integer.parseInt(populationParam) : null;

            boolean isDeleted = cityService.sehirSil(id, nameParam, countryCodeParam, districtParam, population);
            if (isDeleted) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\":\"City not found\"}");
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Invalid number format\"}");
        }
    }
}
