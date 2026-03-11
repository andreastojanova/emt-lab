package mk.ukim.finki.wp.library.model.dto;

import mk.ukim.finki.wp.library.model.domain.Author;
import mk.ukim.finki.wp.library.model.domain.Country;

public record AuthorDto(
        String name,
        String surname,
        Long countryId
) {
    public Author toAuthor(Country country) {
        return new Author(this.name,
                this.surname,
                country);
    }
}

