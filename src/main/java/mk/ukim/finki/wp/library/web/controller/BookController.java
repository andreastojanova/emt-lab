package mk.ukim.finki.wp.library.web.controller;

import mk.ukim.finki.wp.library.model.domain.Book;
import mk.ukim.finki.wp.library.model.domain.Category;
import mk.ukim.finki.wp.library.model.dto.BookDto;
import mk.ukim.finki.wp.library.model.projection.BookProjection;
import mk.ukim.finki.wp.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll() {
        return this.bookService.findAll();
    }

    @GetMapping("/projected")
    public List<BookProjection> findAllProjected() {
        return this.bookService.findAllProjected();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.bookService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(this.bookService.save(bookDto));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return ResponseEntity.ok(this.bookService.edit(id, bookDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/filter")
    public List<BookProjection> filterByCategory(@RequestParam Category category) {
        return this.bookService.listByCategory(category);
    }

    @PutMapping("/rent/{id}")
    public ResponseEntity<Book> markAsRented(@PathVariable Long id) {
        return ResponseEntity.ok(this.bookService.markAsRented(id));
    }
}
