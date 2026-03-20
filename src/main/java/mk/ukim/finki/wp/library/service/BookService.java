package mk.ukim.finki.wp.library.service;

import mk.ukim.finki.wp.library.model.domain.Book;
import mk.ukim.finki.wp.library.model.domain.BookCopy;
import mk.ukim.finki.wp.library.model.domain.Category;
import mk.ukim.finki.wp.library.model.domain.State;
import mk.ukim.finki.wp.library.model.dto.BookDto;
import mk.ukim.finki.wp.library.model.projection.BookProjection;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    List<BookProjection> findAllProjected();
    Book findById(Long id); // Директно враќа Book
    Book save(BookDto bookDto);
    Book edit(Long id, BookDto bookDto);
    void deleteById(Long id);
    Book markAsRented(Long id);
    List<BookProjection> listByCategory(Category category);

    BookCopy addCopy(Long bookId, String barcode, State state);

}
