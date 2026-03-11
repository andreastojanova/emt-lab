package mk.ukim.finki.wp.library.service.impl;

import mk.ukim.finki.wp.library.model.domain.Author;
import mk.ukim.finki.wp.library.model.domain.Country;
import mk.ukim.finki.wp.library.model.exception.InvalidAuthorIdException;
import mk.ukim.finki.wp.library.repository.AuthorRepository;
import mk.ukim.finki.wp.library.service.AuthorService;
import mk.ukim.finki.wp.library.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }


    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(InvalidAuthorIdException::new);
    }

    @Override
    public Author save(String name, String surname, Long countryId) {
        Country country=countryService.findById(countryId);
        Author author=new Author(name,surname,country);
        return authorRepository.save(author);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
