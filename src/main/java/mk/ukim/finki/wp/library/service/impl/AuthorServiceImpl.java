package mk.ukim.finki.wp.library.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.wp.library.model.domain.Author;
import mk.ukim.finki.wp.library.model.domain.Country;
import mk.ukim.finki.wp.library.model.dto.AuthorDto;
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
    public Author save(AuthorDto authorDto) {
        Country country=countryService.findById(authorDto.countryId());
        Author author=authorDto.toAuthor(country);
        return authorRepository.save(author);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Author edit(Long id, AuthorDto authorDto) {
       Country country=countryService.findById(authorDto.countryId());
       Author author=findById(id);
       author.setName(authorDto.name());
       author.setSurname(authorDto.surname());
       author.setCountry(country);
       return authorRepository.save(author);
    }
}
