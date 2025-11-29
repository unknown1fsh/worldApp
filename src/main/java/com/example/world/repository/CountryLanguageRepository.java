package com.example.world.repository;

import com.example.world.config.DatabaseConnection;
import com.example.world.model.CountryLanguage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CountryLanguageRepository {

    public List<CountryLanguage> tumCountryLanguageGetir() {
        List<CountryLanguage> countryLanguages = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM countrylanguage")) {

            while (resultSet.next()) {
                CountryLanguage countryLanguage = new CountryLanguage(
                        resultSet.getString("CountryCode"),
                        resultSet.getString("Language"),
                        resultSet.getString("IsOfficial"),
                        resultSet.getDouble("Percentage")
                );
                countryLanguages.add(countryLanguage);
            }
        } catch (SQLException e) {
            System.err.println("Ülke dilleri getirilirken hata: " + e.getMessage());
            e.printStackTrace();
        }
        return countryLanguages;
    }

    public void ulkeDiliEkle(CountryLanguage countryLanguage) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO countrylanguage (CountryCode, Language, IsOfficial, Percentage) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, countryLanguage.getCountryCode());
            preparedStatement.setString(2, countryLanguage.getLanguage());
            preparedStatement.setString(3, countryLanguage.getIsOfficial());
            preparedStatement.setDouble(4, countryLanguage.getPercentage());
            preparedStatement.executeUpdate();
        }
    }

    public Optional<CountryLanguage> ulkeDiliGetir(String countryCode, String language) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM countrylanguage WHERE CountryCode = ? AND Language = ?")) {

            preparedStatement.setString(1, countryCode);
            preparedStatement.setString(2, language);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new CountryLanguage(
                        resultSet.getString("CountryCode"),
                        resultSet.getString("Language"),
                        resultSet.getString("IsOfficial"),
                        resultSet.getDouble("Percentage")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Ülke dili getirilirken hata: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean ulkeDiliGuncelle(CountryLanguage countryLanguage) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE countrylanguage SET IsOfficial = ?, Percentage = ? WHERE CountryCode = ? AND Language = ?")) {

            preparedStatement.setString(1, countryLanguage.getIsOfficial());
            preparedStatement.setDouble(2, countryLanguage.getPercentage());
            preparedStatement.setString(3, countryLanguage.getCountryCode());
            preparedStatement.setString(4, countryLanguage.getLanguage());
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Ülke dili güncellenirken hata: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean ulkeDiliSil(String countryCode, String language, String isOfficial, Double percentage) {
        List<String> conditions = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        
        if (countryCode != null) {
            conditions.add("CountryCode = ?");
            params.add(countryCode);
        }
        if (language != null) {
            conditions.add("Language = ?");
            params.add(language);
        }
        if (isOfficial != null) {
            conditions.add("IsOfficial = ?");
            params.add(isOfficial);
        }
        if (percentage != null) {
            conditions.add("Percentage = ?");
            params.add(percentage);
        }
        
        if (conditions.isEmpty()) {
            return false;
        }
        
        String query = "DELETE FROM countrylanguage WHERE " + String.join(" AND ", conditions);
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                if (param instanceof Double) {
                    preparedStatement.setDouble(i + 1, (Double) param);
                } else if (param instanceof String) {
                    preparedStatement.setString(i + 1, (String) param);
                }
            }
            
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Ülke dili silinirken hata: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public List<CountryLanguage> searchCountryLanguages(String countryCode, String language, String isOfficial, Double percentage) {
        List<CountryLanguage> countryLanguages = new ArrayList<>();
        List<String> conditions = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        
        if (countryCode != null) {
            conditions.add("CountryCode = ?");
            params.add(countryCode);
        }
        if (language != null) {
            conditions.add("Language = ?");
            params.add(language);
        }
        if (isOfficial != null) {
            conditions.add("IsOfficial = ?");
            params.add(isOfficial);
        }
        if (percentage != null) {
            conditions.add("Percentage = ?");
            params.add(percentage);
        }
        
        String query = "SELECT * FROM countrylanguage";
        if (!conditions.isEmpty()) {
            query += " WHERE " + String.join(" AND ", conditions);
        }
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                if (param instanceof Double) {
                    preparedStatement.setDouble(i + 1, (Double) param);
                } else if (param instanceof String) {
                    preparedStatement.setString(i + 1, (String) param);
                }
            }
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CountryLanguage countryLanguage = new CountryLanguage(
                        resultSet.getString("CountryCode"),
                        resultSet.getString("Language"),
                        resultSet.getString("IsOfficial"),
                        resultSet.getDouble("Percentage")
                );
                countryLanguages.add(countryLanguage);
            }
        } catch (SQLException e) {
            System.err.println("Ülke dilleri aranırken hata: " + e.getMessage());
            e.printStackTrace();
        }
        return countryLanguages;
    }
}
