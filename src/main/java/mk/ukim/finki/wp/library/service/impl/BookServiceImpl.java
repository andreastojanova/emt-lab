package mk.ukim.finki.wp.library.service.impl;

import mk.ukim.finki.wp.library.model.domain.*;
import mk.ukim.finki.wp.library.model.exception.InvalidBookIdException;
import mk.ukim.finki.wp.library.model.dto.BookDto;
import mk.ukim.finki.wp.library.model.projection.BookProjection;
import mk.ukim.finki.wp.library.repository.BookCopyRepository;
import mk.ukim.finki.wp.library.repository.BookRepository;
import mk.ukim.finki.wp.library.service.AuthorService;
import mk.ukim.finki.wp.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final BookCopyRepository bookCopyRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, BookCopyRepository bookCopyRepository) {
        this.bookRepository = bookRepository;
        this.authorService=authorService;
        this.bookCopyRepository = bookCopyRepository;
    }


    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<BookProjection> findAllProjected() {
        return bookRepository.findAllProjectedBy();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);
    }

    @Override
    public Book save(BookDto bookDto) {
        Author author=authorService.findById(bookDto.authorId());
        Book book=bookDto.toBook(author);
        return bookRepository.save(book);
    }

    @Override
    public Book edit(Long id, BookDto bookDto) {
        Book book=findById(id);
        Author author=authorService.findById(bookDto.authorId());

        book.setName(bookDto.name());
        book.setAuthor(author);
        book.setCategory(bookDto.category());

        return bookRepository.save(book);

    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);

    }
    @Override
    public List<BookProjection> listByCategory(Category category) {
        return this.bookRepository.findAllByCategory(category);
    }

    @Override
    public BookCopy addCopy(Long bookId, String barcode, State state) {
        Book book=findById(bookId);
        BookCopy newCopy=new BookCopy(barcode,state,true);

        newCopy.setBook(book);
        return bookCopyRepository.save(newCopy);
    }

    @Override
    public Book markAsRented(Long id) {
        Book book=findById(id);

        BookCopy availableCopy=book.getCopies().stream()
                .filter(BookCopy::isAvailable)
                .findFirst().orElseThrow(
                        ()->new RuntimeException("No more copies available."));

        availableCopy.setAvailable(false);
        return bookRepository.save(book);
    }
}
