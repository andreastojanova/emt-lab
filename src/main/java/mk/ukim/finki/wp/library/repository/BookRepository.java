package mk.ukim.finki.wp.library.repository;

import mk.ukim.finki.wp.library.model.domain.Book;
import mk.ukim.finki.wp.library.model.domain.Category;
import mk.ukim.finki.wp.library.model.projection.BookProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<BookProjection> findAllProjectedBy();
    List<BookProjection> findAllByCategory(Category category);
}
