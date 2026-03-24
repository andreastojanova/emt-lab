package mk.ukim.finki.wp.library.web.controller;

import mk.ukim.finki.wp.library.model.domain.Book;
import mk.ukim.finki.wp.library.model.domain.BookCopy;
import mk.ukim.finki.wp.library.model.domain.Category;
import mk.ukim.finki.wp.library.model.domain.State;
import mk.ukim.finki.wp.library.model.dto.BookDto;
import mk.ukim.finki.wp.library.model.dto.BookResponseDto;
import mk.ukim.finki.wp.library.model.projection.BookProjection;
import mk.ukim.finki.wp.library.model.projection.ExtendedBookProjection;
import mk.ukim.finki.wp.library.model.views.BookDetailsView;
import mk.ukim.finki.wp.library.model.views.CategoryStatsView;
import mk.ukim.finki.wp.library.repository.BookDetailsViewRepository;
import mk.ukim.finki.wp.library.repository.CategoryStatsViewRepository;
import mk.ukim.finki.wp.library.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final BookDetailsViewRepository bookDetailsViewRepository;
    private final CategoryStatsViewRepository categoryStatsViewRepository;

    public BookController(BookService bookService, BookDetailsViewRepository bookDetailsViewRepository, CategoryStatsViewRepository categoryStatsViewRepository) {
        this.bookService = bookService;
        this.bookDetailsViewRepository = bookDetailsViewRepository;
        this.categoryStatsViewRepository = categoryStatsViewRepository;
    }

    @GetMapping
    public List<Book> findAll() {
        return this.bookService.findAll();
    }

    @GetMapping("/projected")
    public List<ExtendedBookProjection> findAllProjected() {
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
    public List<ExtendedBookProjection> filterByCategory(@RequestParam Category category) {
        return this.bookService.listByCategory(category);
    }

    @PutMapping("/rent/{id}")
    public ResponseEntity<Book> markAsRented(@PathVariable Long id) {
        return ResponseEntity.ok(this.bookService.markAsRented(id));
    }

    @PostMapping("/{id}/add-copy")
    public ResponseEntity<BookCopy> addCopy(@PathVariable Long id,
                                            @RequestParam String barcode,
                                            @RequestParam State state) {
        return ResponseEntity.ok(this.bookService.addCopy(id, barcode, state));

    }

    @GetMapping("/pagination")
    public Page<BookResponseDto> getBooksPaginated(@RequestParam(required = false) Category category,
                                                   @RequestParam(required = false) State state,
                                                   @RequestParam(required = false) Long authorId,
                                                   @RequestParam(required = false) Boolean available,
                                                   Pageable pageable) {
        return this.bookService.findAllWithPaginationAndFiltering(category, state, authorId, available, pageable);
    }

    @GetMapping("/views")
    public List<BookDetailsView> getAllFromView() {
        return bookDetailsViewRepository.findAll();
    }

    @GetMapping("/statistics")
    public List<CategoryStatsView> getStatistics() {
        return categoryStatsViewRepository.findAll();
    }
}
