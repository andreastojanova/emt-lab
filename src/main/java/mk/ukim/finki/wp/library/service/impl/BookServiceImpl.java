package mk.ukim.finki.wp.library.service.impl;

import mk.ukim.finki.wp.library.model.domain.Category;
import mk.ukim.finki.wp.library.model.exception.InvalidBookIdException;
import mk.ukim.finki.wp.library.model.domain.Author;
import mk.ukim.finki.wp.library.model.domain.Book;
import mk.ukim.finki.wp.library.model.dto.BookDto;
import mk.ukim.finki.wp.library.model.projection.BookProjection;
import mk.ukim.finki.wp.library.repository.BookRepository;
import mk.ukim.finki.wp.library.service.AuthorService;
import mk.ukim.finki.wp.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository,AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService=authorService;
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
        book.setAvailableCopies(bookDto.availableCopies());

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
    public Book markAsRented(Long id) {
        Book book=findById(id);

        if (book.getAvailableCopies()>0){
            book.setAvailableCopies(book.getAvailableCopies()-1);
            return bookRepository.save(book);
        }else {
            throw new RuntimeException("No more copies available.");
        }
    }
}
