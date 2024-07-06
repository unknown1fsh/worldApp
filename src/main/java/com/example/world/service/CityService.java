package com.example.world.service;

import com.example.world.model.City;
import com.example.world.repository.CityRepository;

import java.util.List;
import java.util.Optional;

public class CityService {
    private CityRepository cityRepository = new CityRepository();

    public List<City> tumSehirleriGetir() {
        return cityRepository.tumSehirleriGetir();
    }

    public void sehirEkle(City sehir) {
        cityRepository.sehirEkle(sehir);
    }

    public Optional<City> sehirGetir(int id) {
        return cityRepository.sehirGetir(id);
    }

    public boolean sehirGuncelle(City sehir) {
        return cityRepository.sehirGuncelle(sehir);
    }

    public boolean sehirSil(Integer id, String name, String countryCode, String district, Integer population) {
        return cityRepository.sehirSil(id, name, countryCode, district, population);
    }

    public List<City> searchCities(Integer id, String name, String countryCode, String district, Integer population) {
        return cityRepository.searchCities(id, name, countryCode, district, population);
    }
}
