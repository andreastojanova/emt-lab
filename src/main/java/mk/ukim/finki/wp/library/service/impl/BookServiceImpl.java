package mk.ukim.finki.wp.library.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.wp.library.events.BookRentEvent;
import mk.ukim.finki.wp.library.model.domain.*;
import mk.ukim.finki.wp.library.model.dto.BookResponseDto;
import mk.ukim.finki.wp.library.model.exception.InvalidBookIdException;
import mk.ukim.finki.wp.library.model.dto.BookDto;
import mk.ukim.finki.wp.library.model.projection.BookProjection;
import mk.ukim.finki.wp.library.model.projection.ExtendedBookProjection;
import mk.ukim.finki.wp.library.repository.BookCopyRepository;
import mk.ukim.finki.wp.library.repository.BookRepository;
import mk.ukim.finki.wp.library.service.AuthorService;
import mk.ukim.finki.wp.library.service.BookService;
import mk.ukim.finki.wp.library.specification.BookSpecification;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final BookCopyRepository bookCopyRepository;
    private final ApplicationEventPublisher eventPublisher;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, BookCopyRepository bookCopyRepository, ApplicationEventPublisher eventPublisher) {
        this.bookRepository = bookRepository;
        this.authorService=authorService;
        this.bookCopyRepository = bookCopyRepository;
        this.eventPublisher = eventPublisher;
    }


    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<ExtendedBookProjection> findAllProjected() {
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
    public List<ExtendedBookProjection> listByCategory(Category category) {
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
    public Page<BookResponseDto> findAllWithPaginationAndFiltering(Category category, State state, Long authorId, Boolean available, Pageable pageable) {
        Specification<Book> filter=Specification
                .where(BookSpecification.hasCategory(category))
                .and(BookSpecification.hasState(state))
                .and(BookSpecification.hasAuthor(authorId))
                .and(BookSpecification.hasAvailableCopies(available));

        Page<Book> bookPage = this.bookRepository.findAll(filter, pageable);

        return bookPage.map(BookResponseDto::from);
    }

    @Override
    public List<Book> findLatestTen() {
        return bookRepository.findTop10ByOrderByDatePublishedDesc();
    }

    @Override
    @Transactional
    public Book markAsRented(Long id) {
        Book book=findById(id);

        BookCopy availableCopy=book.getCopies().stream()
                .filter(BookCopy::isAvailable)
                .findFirst().orElseThrow(
                        ()->new RuntimeException("No more copies available."));

        availableCopy.setAvailable(false);
        this.eventPublisher.publishEvent(new BookRentEvent(book, "student@finki.ukim.mk"));
        return bookRepository.save(book);
    }
}
