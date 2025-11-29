package com.example.world.service;

import com.example.world.model.Country;
import com.example.world.repository.CountryRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CountryService {
    private CountryRepository countryRepository = new CountryRepository();

    public List<Country> tumUlkeleriGetir() {
        return countryRepository.tumUlkeleriGetir();
    }

    public void ulkeEkle(Country country) throws SQLException {
        countryRepository.ulkeEkle(country);
    }

    public Optional<Country> ulkeGetir(String code) {
        return countryRepository.ulkeGetir(code);
    }

    public boolean ulkeGuncelle(Country country) {
        return countryRepository.ulkeGuncelle(country);
    }

    public boolean ulkeSil(String code, String name, String continent, String region, Integer surfaceArea, Integer indepYear, Integer population, Double lifeExpectancy, Double gnp, Double gnpOld, String localName, String governmentForm, String headOfState, Integer capital, String code2) {
        return countryRepository.ulkeSil(code, name, continent, region, surfaceArea, indepYear, population, lifeExpectancy, gnp, gnpOld, localName, governmentForm, headOfState, capital, code2);
    }

    public List<Country> searchCountries(String code, String name, String continent, String region, Integer surfaceArea, Integer indepYear, Integer population, Double lifeExpectancy, Double gnp, Double gnpOld, String localName, String governmentForm, String headOfState, Integer capital, String code2) {
        return countryRepository.searchCountries(code, name, continent, region, surfaceArea, indepYear, population, lifeExpectancy, gnp, gnpOld, localName, governmentForm, headOfState, capital, code2);
    }
}
