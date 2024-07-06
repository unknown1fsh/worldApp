package com.example.world.repository;

import com.example.world.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CityRepository {
    private final String url = "jdbc:mysql://localhost:3306/world";
    private final String user = "root"; // Veritabanı kullanıcı adı
    private final String password = "12345"; // Veritabanı şifresi

    public List<City> tumSehirleriGetir() {
        List<City> sehirler = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM city")) {

            while (resultSet.next()) {
                City sehir = new City(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("CountryCode"),
                        resultSet.getString("District"),
                        resultSet.getInt("Population")
                );
                sehirler.add(sehir);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sehirler;
    }

    public void sehirEkle(City sehir) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO city (ID, Name, CountryCode, District, Population) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setInt(1, sehir.getId());
            preparedStatement.setString(2, sehir.getName());
            preparedStatement.setString(3, sehir.getCountryCode());
            preparedStatement.setString(4, sehir.getDistrict());
            preparedStatement.setInt(5, sehir.getPopulation());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<City> sehirGetir(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM city WHERE ID = ?")) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new City(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("CountryCode"),
                        resultSet.getString("District"),
                        resultSet.getInt("Population")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean sehirGuncelle(City sehir) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE city SET Name = ?, CountryCode = ?, District = ?, Population = ? WHERE ID = ?")) {

            preparedStatement.setString(1, sehir.getName());
            preparedStatement.setString(2, sehir.getCountryCode());
            preparedStatement.setString(3, sehir.getDistrict());
            preparedStatement.setInt(4, sehir.getPopulation());
            preparedStatement.setInt(5, sehir.getId());
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean sehirSil(Integer id, String name, String countryCode, String district, Integer population) {
        StringBuilder query = new StringBuilder("DELETE FROM city WHERE 1=1");

        if (id != null) {
            query.append(" AND ID = ").append(id);
        }
        if (name != null) {
            query.append(" AND Name = '").append(name).append("'");
        }
        if (countryCode != null) {
            query.append(" AND CountryCode = '").append(countryCode).append("'");
        }
        if (district != null) {
            query.append(" AND District = '").append(district).append("'");
        }
        if (population != null) {
            query.append(" AND Population = ").append(population);
        }

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            int affectedRows = statement.executeUpdate(query.toString());
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<City> searchCities(Integer id, String name, String countryCode, String district, Integer population) {
        List<City> sehirler = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM city WHERE 1=1");

        if (id != null) {
            query.append(" AND ID = ").append(id);
        }
        if (name != null) {
            query.append(" AND Name = '").append(name).append("'");
        }
        if (countryCode != null) {
            query.append(" AND CountryCode = '").append(countryCode).append("'");
        }
        if (district != null) {
            query.append(" AND District = '").append(district).append("'");
        }
        if (population != null) {
            query.append(" AND Population = ").append(population);
        }

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query.toString())) {

            while (resultSet.next()) {
                City sehir = new City(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("CountryCode"),
                        resultSet.getString("District"),
                        resultSet.getInt("Population")
                );
                sehirler.add(sehir);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sehirler;
    }
}
