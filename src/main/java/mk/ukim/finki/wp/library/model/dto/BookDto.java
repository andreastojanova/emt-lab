package mk.ukim.finki.wp.library.model.dto;

import mk.ukim.finki.wp.library.model.domain.Author;
import mk.ukim.finki.wp.library.model.domain.Book;
import mk.ukim.finki.wp.library.model.domain.BookCopy;
import mk.ukim.finki.wp.library.model.domain.Category;

public record BookDto(String name,
                      Category category,
                      Long authorId) {
    public Book toBook(Author author){
        return new Book(this.name,this.category,author);
    }
}
