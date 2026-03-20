package mk.ukim.finki.wp.library.repository;

import mk.ukim.finki.wp.library.model.domain.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy,Long> {
}
