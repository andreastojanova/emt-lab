package mk.ukim.finki.wp.library.service;

import mk.ukim.finki.wp.library.model.domain.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll();
    Country findById(Long id);
    Country save(String name, String continent);
    void deleteById(Long id);
}
