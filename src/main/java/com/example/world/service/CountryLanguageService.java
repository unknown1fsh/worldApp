package com.example.world.service;

import com.example.world.model.CountryLanguage;
import com.example.world.repository.CountryLanguageRepository;

import java.util.List;
import java.util.Optional;

public class CountryLanguageService {
    private CountryLanguageRepository countryLanguageRepository = new CountryLanguageRepository();

    public List<CountryLanguage> tumCountryLanguageGetir() {
        return countryLanguageRepository.tumCountryLanguageGetir();
    }

    public void ulkeDiliEkle(CountryLanguage countryLanguage) {
        countryLanguageRepository.ulkeDiliEkle(countryLanguage);
    }

    public Optional<CountryLanguage> ulkeDiliGetir(String countryCode, String language) {
        return countryLanguageRepository.ulkeDiliGetir(countryCode, language);
    }

    public boolean ulkeDiliGuncelle(CountryLanguage countryLanguage) {
        return countryLanguageRepository.ulkeDiliGuncelle(countryLanguage);
    }

    public boolean ulkeDiliSil(String countryCode, String language, String isOfficial, Double percentage) {
        return countryLanguageRepository.ulkeDiliSil(countryCode, language, isOfficial, percentage);
    }

    public List<CountryLanguage> searchCountryLanguages(String countryCode, String language, String isOfficial, Double percentage) {
        return countryLanguageRepository.searchCountryLanguages(countryCode, language, isOfficial, percentage);
    }
}
