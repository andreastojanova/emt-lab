package mk.ukim.finki.wp.library.repository;

import mk.ukim.finki.wp.library.model.domain.Book;
import mk.ukim.finki.wp.library.model.domain.Category;
import mk.ukim.finki.wp.library.model.projection.BookProjection;
import mk.ukim.finki.wp.library.model.projection.ExtendedBookProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>, JpaSpecificationExecutor<Book> {
//    @EntityGraph(attributePaths = {"author","author.country","copies"})
    @EntityGraph(value = "book-entity-graph",type = EntityGraph.EntityGraphType.FETCH)
    List<ExtendedBookProjection> findAllProjectedBy();
    @EntityGraph(value = "book-entity-graph",type = EntityGraph.EntityGraphType.FETCH)

    List<ExtendedBookProjection> findAllByCategory(Category category);

    List<Book> findTop10ByOrderByDatePublishedDesc();
}
