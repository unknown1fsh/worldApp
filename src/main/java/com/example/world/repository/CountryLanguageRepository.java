package com.example.world.repository;

import com.example.world.model.CountryLanguage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CountryLanguageRepository {
    private final String url = "jdbc:mysql://localhost:3306/world";
    private final String user = "root"; // Veritabanı kullanıcı adı
    private final String password = "12345"; // Veritabanı şifresi

    public List<CountryLanguage> tumCountryLanguageGetir() {
        List<CountryLanguage> countryLanguages = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
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
            e.printStackTrace();
        }
        return countryLanguages;
    }

    public void ulkeDiliEkle(CountryLanguage countryLanguage) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO countrylanguage (CountryCode, Language, IsOfficial, Percentage) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, countryLanguage.getCountryCode());
            preparedStatement.setString(2, countryLanguage.getLanguage());
            preparedStatement.setString(3, countryLanguage.getIsOfficial());
            preparedStatement.setDouble(4, countryLanguage.getPercentage());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<CountryLanguage> ulkeDiliGetir(String countryCode, String language) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
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
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean ulkeDiliGuncelle(CountryLanguage countryLanguage) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE countrylanguage SET IsOfficial = ?, Percentage = ? WHERE CountryCode = ? AND Language = ?")) {

            preparedStatement.setString(1, countryLanguage.getIsOfficial());
            preparedStatement.setDouble(2, countryLanguage.getPercentage());
            preparedStatement.setString(3, countryLanguage.getCountryCode());
            preparedStatement.setString(4, countryLanguage.getLanguage());
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean ulkeDiliSil(String countryCode, String language, String isOfficial, Double percentage) {
        StringBuilder query = new StringBuilder("DELETE FROM countrylanguage WHERE 1=1");

        if (countryCode != null) {
            query.append(" AND CountryCode = '").append(countryCode).append("'");
        }
        if (language != null) {
            query.append(" AND Language = '").append(language).append("'");
        }
        if (isOfficial != null) {
            query.append(" AND IsOfficial = '").append(isOfficial).append("'");
        }
        if (percentage != null) {
            query.append(" AND Percentage = ").append(percentage);
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

    public List<CountryLanguage> searchCountryLanguages(String countryCode, String language, String isOfficial, Double percentage) {
        List<CountryLanguage> countryLanguages = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM countrylanguage WHERE 1=1");

        if (countryCode != null) {
            query.append(" AND CountryCode = '").append(countryCode).append("'");
        }
        if (language != null) {
            query.append(" AND Language = '").append(language).append("'");
        }
        if (isOfficial != null) {
            query.append(" AND IsOfficial = '").append(isOfficial).append("'");
        }
        if (percentage != null) {
            query.append(" AND Percentage = ").append(percentage);
        }

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query.toString())) {

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
            e.printStackTrace();
        }
        return countryLanguages;
    }
}
