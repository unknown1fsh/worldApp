package com.example.world.repository;

import com.example.world.config.DatabaseConnection;
import com.example.world.model.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CountryRepository {

    public List<Country> tumUlkeleriGetir() {
        List<Country> ulkeler = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM country")) {

            while (resultSet.next()) {
                ulkeler.add(Country.fromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.err.println("Ülkeler getirilirken hata: " + e.getMessage());
            e.printStackTrace();
        }
        return ulkeler;
    }

    public void ulkeEkle(Country ulke) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO country (Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, ulke.getCode());
            preparedStatement.setString(2, ulke.getName());
            preparedStatement.setString(3, ulke.getContinent());
            preparedStatement.setString(4, ulke.getRegion());
            preparedStatement.setDouble(5, ulke.getSurfaceArea());
            if (ulke.getIndepYear() != null) {
                preparedStatement.setInt(6, ulke.getIndepYear());
            } else {
                preparedStatement.setNull(6, Types.INTEGER);
            }
            preparedStatement.setInt(7, ulke.getPopulation());
            if (ulke.getLifeExpectancy() != null) {
                preparedStatement.setDouble(8, ulke.getLifeExpectancy());
            } else {
                preparedStatement.setNull(8, Types.DOUBLE);
            }
            if (ulke.getGnp() != null) {
                preparedStatement.setDouble(9, ulke.getGnp());
            } else {
                preparedStatement.setNull(9, Types.DOUBLE);
            }
            if (ulke.getGnpOld() != null) {
                preparedStatement.setDouble(10, ulke.getGnpOld());
            } else {
                preparedStatement.setNull(10, Types.DOUBLE);
            }
            preparedStatement.setString(11, ulke.getLocalName());
            preparedStatement.setString(12, ulke.getGovernmentForm());
            preparedStatement.setString(13, ulke.getHeadOfState());
            if (ulke.getCapital() != null) {
                preparedStatement.setInt(14, ulke.getCapital());
            } else {
                preparedStatement.setNull(14, Types.INTEGER);
            }
            preparedStatement.setString(15, ulke.getCode2());
            preparedStatement.executeUpdate();
        }
    }

    public Optional<Country> ulkeGetir(String code) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM country WHERE Code = ?")) {

            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(Country.fromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.err.println("Ülke getirilirken hata: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean ulkeGuncelle(Country ulke) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE country SET Name = ?, Continent = ?, Region = ?, SurfaceArea = ?, IndepYear = ?, Population = ?, LifeExpectancy = ?, GNP = ?, GNPOld = ?, LocalName = ?, GovernmentForm = ?, HeadOfState = ?, Capital = ?, Code2 = ? WHERE Code = ?")) {

            preparedStatement.setString(1, ulke.getName());
            preparedStatement.setString(2, ulke.getContinent());
            preparedStatement.setString(3, ulke.getRegion());
            preparedStatement.setDouble(4, ulke.getSurfaceArea());
            if (ulke.getIndepYear() != null) {
                preparedStatement.setInt(5, ulke.getIndepYear());
            } else {
                preparedStatement.setNull(5, Types.INTEGER);
            }
            preparedStatement.setInt(6, ulke.getPopulation());
            if (ulke.getLifeExpectancy() != null) {
                preparedStatement.setDouble(7, ulke.getLifeExpectancy());
            } else {
                preparedStatement.setNull(7, Types.DOUBLE);
            }
            if (ulke.getGnp() != null) {
                preparedStatement.setDouble(8, ulke.getGnp());
            } else {
                preparedStatement.setNull(8, Types.DOUBLE);
            }
            if (ulke.getGnpOld() != null) {
                preparedStatement.setDouble(9, ulke.getGnpOld());
            } else {
                preparedStatement.setNull(9, Types.DOUBLE);
            }
            preparedStatement.setString(10, ulke.getLocalName());
            preparedStatement.setString(11, ulke.getGovernmentForm());
            preparedStatement.setString(12, ulke.getHeadOfState());
            if (ulke.getCapital() != null) {
                preparedStatement.setInt(13, ulke.getCapital());
            } else {
                preparedStatement.setNull(13, Types.INTEGER);
            }
            preparedStatement.setString(14, ulke.getCode2());
            preparedStatement.setString(15, ulke.getCode());
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Ülke güncellenirken hata: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean ulkeSil(String code, String name, String continent, String region, Integer surfaceArea, Integer indepYear, Integer population, Double lifeExpectancy, Double gnp, Double gnpOld, String localName, String governmentForm, String headOfState, Integer capital, String code2) {
        List<String> conditions = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        
        if (code != null) {
            conditions.add("Code = ?");
            params.add(code);
        }
        if (name != null) {
            conditions.add("Name = ?");
            params.add(name);
        }
        if (continent != null) {
            conditions.add("Continent = ?");
            params.add(continent);
        }
        if (region != null) {
            conditions.add("Region = ?");
            params.add(region);
        }
        if (surfaceArea != null) {
            conditions.add("SurfaceArea = ?");
            params.add(surfaceArea);
        }
        if (indepYear != null) {
            conditions.add("IndepYear = ?");
            params.add(indepYear);
        }
        if (population != null) {
            conditions.add("Population = ?");
            params.add(population);
        }
        if (lifeExpectancy != null) {
            conditions.add("LifeExpectancy = ?");
            params.add(lifeExpectancy);
        }
        if (gnp != null) {
            conditions.add("GNP = ?");
            params.add(gnp);
        }
        if (gnpOld != null) {
            conditions.add("GNPOld = ?");
            params.add(gnpOld);
        }
        if (localName != null) {
            conditions.add("LocalName = ?");
            params.add(localName);
        }
        if (governmentForm != null) {
            conditions.add("GovernmentForm = ?");
            params.add(governmentForm);
        }
        if (headOfState != null) {
            conditions.add("HeadOfState = ?");
            params.add(headOfState);
        }
        if (capital != null) {
            conditions.add("Capital = ?");
            params.add(capital);
        }
        if (code2 != null) {
            conditions.add("Code2 = ?");
            params.add(code2);
        }
        
        if (conditions.isEmpty()) {
            return false;
        }
        
        String query = "DELETE FROM country WHERE " + String.join(" AND ", conditions);
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                if (param instanceof Integer) {
                    preparedStatement.setInt(i + 1, (Integer) param);
                } else if (param instanceof Double) {
                    preparedStatement.setDouble(i + 1, (Double) param);
                } else if (param instanceof String) {
                    preparedStatement.setString(i + 1, (String) param);
                }
            }
            
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Ülke silinirken hata: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public List<Country> searchCountries(String code, String name, String continent, String region, Integer surfaceArea, Integer indepYear, Integer population, Double lifeExpectancy, Double gnp, Double gnpOld, String localName, String governmentForm, String headOfState, Integer capital, String code2) {
        List<Country> ulkeler = new ArrayList<>();
        List<String> conditions = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        
        if (code != null) {
            conditions.add("Code = ?");
            params.add(code);
        }
        if (name != null) {
            conditions.add("Name = ?");
            params.add(name);
        }
        if (continent != null) {
            conditions.add("Continent = ?");
            params.add(continent);
        }
        if (region != null) {
            conditions.add("Region = ?");
            params.add(region);
        }
        if (surfaceArea != null) {
            conditions.add("SurfaceArea = ?");
            params.add(surfaceArea);
        }
        if (indepYear != null) {
            conditions.add("IndepYear = ?");
            params.add(indepYear);
        }
        if (population != null) {
            conditions.add("Population = ?");
            params.add(population);
        }
        if (lifeExpectancy != null) {
            conditions.add("LifeExpectancy = ?");
            params.add(lifeExpectancy);
        }
        if (gnp != null) {
            conditions.add("GNP = ?");
            params.add(gnp);
        }
        if (gnpOld != null) {
            conditions.add("GNPOld = ?");
            params.add(gnpOld);
        }
        if (localName != null) {
            conditions.add("LocalName = ?");
            params.add(localName);
        }
        if (governmentForm != null) {
            conditions.add("GovernmentForm = ?");
            params.add(governmentForm);
        }
        if (headOfState != null) {
            conditions.add("HeadOfState = ?");
            params.add(headOfState);
        }
        if (capital != null) {
            conditions.add("Capital = ?");
            params.add(capital);
        }
        if (code2 != null) {
            conditions.add("Code2 = ?");
            params.add(code2);
        }
        
        String query = "SELECT * FROM country";
        if (!conditions.isEmpty()) {
            query += " WHERE " + String.join(" AND ", conditions);
        }
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                if (param instanceof Integer) {
                    preparedStatement.setInt(i + 1, (Integer) param);
                } else if (param instanceof Double) {
                    preparedStatement.setDouble(i + 1, (Double) param);
                } else if (param instanceof String) {
                    preparedStatement.setString(i + 1, (String) param);
                }
            }
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ulkeler.add(Country.fromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.err.println("Ülkeler aranırken hata: " + e.getMessage());
            e.printStackTrace();
        }
        return ulkeler;
    }
}
