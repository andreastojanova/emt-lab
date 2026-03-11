package mk.ukim.finki.wp.library.service.impl;

import mk.ukim.finki.wp.library.model.domain.Country;
import mk.ukim.finki.wp.library.model.exception.InvalidCountryIdException;
import mk.ukim.finki.wp.library.repository.CountryRepository;
import mk.ukim.finki.wp.library.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id).orElseThrow(InvalidCountryIdException::new);
    }

    @Override
    public Country save(String name, String continent) {
        Country country=new Country(name,continent);
        return countryRepository.save(country);
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
