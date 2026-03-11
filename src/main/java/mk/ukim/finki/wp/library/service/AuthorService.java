package mk.ukim.finki.wp.library.service;

import mk.ukim.finki.wp.library.model.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
    Author findById(Long id);
    Author save(String name, String surname, Long countryId);
    void deleteById(Long id);
}
