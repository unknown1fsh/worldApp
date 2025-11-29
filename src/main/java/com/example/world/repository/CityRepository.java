package com.example.world.repository;

import com.example.world.config.DatabaseConnection;
import com.example.world.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CityRepository {

    public List<City> tumSehirleriGetir() {
        List<City> sehirler = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
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
            System.err.println("Şehirler getirilirken hata: " + e.getMessage());
            e.printStackTrace();
        }
        return sehirler;
    }

    public void sehirEkle(City sehir) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO city (ID, Name, CountryCode, District, Population) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setInt(1, sehir.getId());
            preparedStatement.setString(2, sehir.getName());
            preparedStatement.setString(3, sehir.getCountryCode());
            preparedStatement.setString(4, sehir.getDistrict());
            preparedStatement.setInt(5, sehir.getPopulation());
            preparedStatement.executeUpdate();
        }
    }

    public Optional<City> sehirGetir(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
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
            System.err.println("Şehir getirilirken hata: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean sehirGuncelle(City sehir) {
        try (Connection connection = DatabaseConnection.getConnection();
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
            System.err.println("Şehir güncellenirken hata: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean sehirSil(Integer id, String name, String countryCode, String district, Integer population) {
        List<String> conditions = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        
        if (id != null) {
            conditions.add("ID = ?");
            params.add(id);
        }
        if (name != null) {
            conditions.add("Name = ?");
            params.add(name);
        }
        if (countryCode != null) {
            conditions.add("CountryCode = ?");
            params.add(countryCode);
        }
        if (district != null) {
            conditions.add("District = ?");
            params.add(district);
        }
        if (population != null) {
            conditions.add("Population = ?");
            params.add(population);
        }
        
        if (conditions.isEmpty()) {
            return false;
        }
        
        String query = "DELETE FROM city WHERE " + String.join(" AND ", conditions);
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                if (param instanceof Integer) {
                    preparedStatement.setInt(i + 1, (Integer) param);
                } else if (param instanceof String) {
                    preparedStatement.setString(i + 1, (String) param);
                }
            }
            
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Şehir silinirken hata: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public List<City> searchCities(Integer id, String name, String countryCode, String district, Integer population) {
        List<City> sehirler = new ArrayList<>();
        List<String> conditions = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        
        if (id != null) {
            conditions.add("ID = ?");
            params.add(id);
        }
        if (name != null) {
            conditions.add("Name = ?");
            params.add(name);
        }
        if (countryCode != null) {
            conditions.add("CountryCode = ?");
            params.add(countryCode);
        }
        if (district != null) {
            conditions.add("District = ?");
            params.add(district);
        }
        if (population != null) {
            conditions.add("Population = ?");
            params.add(population);
        }
        
        String query = "SELECT * FROM city";
        if (!conditions.isEmpty()) {
            query += " WHERE " + String.join(" AND ", conditions);
        }
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                if (param instanceof Integer) {
                    preparedStatement.setInt(i + 1, (Integer) param);
                } else if (param instanceof String) {
                    preparedStatement.setString(i + 1, (String) param);
                }
            }
            
            ResultSet resultSet = preparedStatement.executeQuery();
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
            System.err.println("Şehirler aranırken hata: " + e.getMessage());
            e.printStackTrace();
        }
        return sehirler;
    }
}
