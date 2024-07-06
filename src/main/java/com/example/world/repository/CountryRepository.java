package com.example.world.repository;

import com.example.world.model.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CountryRepository {
    private final String url = "jdbc:mysql://localhost:3306/world";
    private final String user = "root"; // Veritabanı kullanıcı adı
    private final String password = "12345"; // Veritabanı şifresi

    public List<Country> tumUlkeleriGetir() {
        List<Country> ulkeler = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM country")) {

            while (resultSet.next()) {
                Country ulke = new Country(
                        resultSet.getString("Code"),
                        resultSet.getString("Name"),
                        resultSet.getString("Continent"),
                        resultSet.getString("Region"),
                        resultSet.getInt("SurfaceArea"),
                        resultSet.getInt("IndepYear"),
                        resultSet.getInt("Population"),
                        resultSet.getDouble("LifeExpectancy"),
                        resultSet.getDouble("GNP"),
                        resultSet.getDouble("GNPOld"),
                        resultSet.getString("LocalName"),
                        resultSet.getString("GovernmentForm"),
                        resultSet.getString("HeadOfState"),
                        resultSet.getInt("Capital"),
                        resultSet.getString("Code2")
                );
                ulkeler.add(ulke);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ulkeler;
    }

    public void ulkeEkle(Country ulke) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO country (Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, ulke.getCode());
            preparedStatement.setString(2, ulke.getName());
            preparedStatement.setString(3, ulke.getContinent());
            preparedStatement.setString(4, ulke.getRegion());
            preparedStatement.setDouble(5, ulke.getSurfaceArea());
            preparedStatement.setInt(6, ulke.getIndepYear());
            preparedStatement.setInt(7, ulke.getPopulation());
            preparedStatement.setDouble(8, ulke.getLifeExpectancy());
            preparedStatement.setDouble(9, ulke.getGnp());
            preparedStatement.setDouble(10, ulke.getGnpOld());
            preparedStatement.setString(11, ulke.getLocalName());
            preparedStatement.setString(12, ulke.getGovernmentForm());
            preparedStatement.setString(13, ulke.getHeadOfState());
            preparedStatement.setInt(14, ulke.getCapital());
            preparedStatement.setString(15, ulke.getCode2());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Country> ulkeGetir(String code) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM country WHERE Code = ?")) {

            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Country(
                        resultSet.getString("Code"),
                        resultSet.getString("Name"),
                        resultSet.getString("Continent"),
                        resultSet.getString("Region"),
                        resultSet.getInt("SurfaceArea"),
                        resultSet.getInt("IndepYear"),
                        resultSet.getInt("Population"),
                        resultSet.getDouble("LifeExpectancy"),
                        resultSet.getDouble("GNP"),
                        resultSet.getDouble("GNPOld"),
                        resultSet.getString("LocalName"),
                        resultSet.getString("GovernmentForm"),
                        resultSet.getString("HeadOfState"),
                        resultSet.getInt("Capital"),
                        resultSet.getString("Code2")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean ulkeGuncelle(Country ulke) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE country SET Name = ?, Continent = ?, Region = ?, SurfaceArea = ?, IndepYear = ?, Population = ?, LifeExpectancy = ?, GNP = ?, GNPOld = ?, LocalName = ?, GovernmentForm = ?, HeadOfState = ?, Capital = ?, Code2 = ? WHERE Code = ?")) {

            preparedStatement.setString(1, ulke.getName());
            preparedStatement.setString(2, ulke.getContinent());
            preparedStatement.setString(3, ulke.getRegion());
            preparedStatement.setDouble(4, ulke.getSurfaceArea());
            preparedStatement.setInt(5, ulke.getIndepYear());
            preparedStatement.setInt(6, ulke.getPopulation());
            preparedStatement.setDouble(7, ulke.getLifeExpectancy());
            preparedStatement.setDouble(8, ulke.getGnp());
            preparedStatement.setDouble(9, ulke.getGnpOld());
            preparedStatement.setString(10, ulke.getLocalName());
            preparedStatement.setString(11, ulke.getGovernmentForm());
            preparedStatement.setString(12, ulke.getHeadOfState());
            preparedStatement.setInt(13, ulke.getCapital());
            preparedStatement.setString(14, ulke.getCode2());
            preparedStatement.setString(15, ulke.getCode());
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean ulkeSil(String code, String name, String continent, String region, Integer surfaceArea, Integer indepYear, Integer population, Double lifeExpectancy, Double gnp, Double gnpOld, String localName, String governmentForm, String headOfState, Integer capital, String code2) {
        StringBuilder query = new StringBuilder("DELETE FROM country WHERE 1=1");

        if (code != null) {
            query.append(" AND Code = '").append(code).append("'");
        }
        if (name != null) {
            query.append(" AND Name = '").append(name).append("'");
        }
        if (continent != null) {
            query.append(" AND Continent = '").append(continent).append("'");
        }
        if (region != null) {
            query.append(" AND Region = '").append(region).append("'");
        }
        if (surfaceArea != null) {
            query.append(" AND SurfaceArea = ").append(surfaceArea);
        }
        if (indepYear != null) {
            query.append(" AND IndepYear = ").append(indepYear);
        }
        if (population != null) {
            query.append(" AND Population = ").append(population);
        }
        if (lifeExpectancy != null) {
            query.append(" AND LifeExpectancy = ").append(lifeExpectancy);
        }
        if (gnp != null) {
            query.append(" AND GNP = ").append(gnp);
        }
        if (gnpOld != null) {
            query.append(" AND GNPOld = ").append(gnpOld);
        }
        if (localName != null) {
            query.append(" AND LocalName = '").append(localName).append("'");
        }
        if (governmentForm != null) {
            query.append(" AND GovernmentForm = '").append(governmentForm).append("'");
        }
        if (headOfState != null) {
            query.append(" AND HeadOfState = '").append(headOfState).append("'");
        }
        if (capital != null) {
            query.append(" AND Capital = ").append(capital);
        }
        if (code2 != null) {
            query.append(" AND Code2 = '").append(code2).append("'");
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

    public List<Country> searchCountries(String code, String name, String continent, String region, Integer surfaceArea, Integer indepYear, Integer population, Double lifeExpectancy, Double gnp, Double gnpOld, String localName, String governmentForm, String headOfState, Integer capital, String code2) {
        List<Country> ulkeler = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM country WHERE 1=1");

        if (code != null) {
            query.append(" AND Code = '").append(code).append("'");
        }
        if (name != null) {
            query.append(" AND Name = '").append(name).append("'");
        }
        if (continent != null) {
            query.append(" AND Continent = '").append(continent).append("'");
        }
        if (region != null) {
            query.append(" AND Region = '").append(region).append("'");
        }
        if (surfaceArea != null) {
            query.append(" AND SurfaceArea = ").append(surfaceArea);
        }
        if (indepYear != null) {
            query.append(" AND IndepYear = ").append(indepYear);
        }
        if (population != null) {
            query.append(" AND Population = ").append(population);
        }
        if (lifeExpectancy != null) {
            query.append(" AND LifeExpectancy = ").append(lifeExpectancy);
        }
        if (gnp != null) {
            query.append(" AND GNP = ").append(gnp);
        }
        if (gnpOld != null) {
            query.append(" AND GNPOld = ").append(gnpOld);
        }
        if (localName != null) {
            query.append(" AND LocalName = '").append(localName).append("'");
        }
        if (governmentForm != null) {
            query.append(" AND GovernmentForm = '").append(governmentForm).append("'");
        }
        if (headOfState != null) {
            query.append(" AND HeadOfState = '").append(headOfState).append("'");
        }
        if (capital != null) {
            query.append(" AND Capital = ").append(capital);
        }
        if (code2 != null) {
            query.append(" AND Code2 = '").append(code2).append("'");
        }

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query.toString())) {

            while (resultSet.next()) {
                Country ulke = new Country(
                        resultSet.getString("Code"),
                        resultSet.getString("Name"),
                        resultSet.getString("Continent"),
                        resultSet.getString("Region"),
                        resultSet.getInt("SurfaceArea"),
                        resultSet.getInt("IndepYear"),
                        resultSet.getInt("Population"),
                        resultSet.getDouble("LifeExpectancy"),
                        resultSet.getDouble("GNP"),
                        resultSet.getDouble("GNPOld"),
                        resultSet.getString("LocalName"),
                        resultSet.getString("GovernmentForm"),
                        resultSet.getString("HeadOfState"),
                        resultSet.getInt("Capital"),
                        resultSet.getString("Code2")
                );
                ulkeler.add(ulke);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ulkeler;
    }
}
