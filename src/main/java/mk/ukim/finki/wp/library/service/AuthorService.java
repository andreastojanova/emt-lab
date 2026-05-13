package mk.ukim.finki.wp.library.service;

import mk.ukim.finki.wp.library.model.domain.Author;
import mk.ukim.finki.wp.library.model.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
    Author findById(Long id);
    Author save(AuthorDto authorDto);
    void deleteById(Long id);
    Author edit(Long id, AuthorDto authorDto) ;
}
