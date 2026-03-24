package mk.ukim.finki.wp.library.service;

import mk.ukim.finki.wp.library.model.domain.Book;
import mk.ukim.finki.wp.library.model.domain.BookCopy;
import mk.ukim.finki.wp.library.model.domain.Category;
import mk.ukim.finki.wp.library.model.domain.State;
import mk.ukim.finki.wp.library.model.dto.BookDto;
import mk.ukim.finki.wp.library.model.dto.BookResponseDto;
import mk.ukim.finki.wp.library.model.projection.BookProjection;
import mk.ukim.finki.wp.library.model.projection.ExtendedBookProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    List<ExtendedBookProjection> findAllProjected();
    Book findById(Long id); // Директно враќа Book
    Book save(BookDto bookDto);
    Book edit(Long id, BookDto bookDto);
    void deleteById(Long id);
    Book markAsRented(Long id);
    List<ExtendedBookProjection> listByCategory(Category category);

    BookCopy addCopy(Long bookId, String barcode, State state);
    Page<BookResponseDto> findAllWithPaginationAndFiltering(Category category,
                                                            State state,
                                                            Long authorId,
                                                            Boolean available,
                                                            Pageable pageable);

}
