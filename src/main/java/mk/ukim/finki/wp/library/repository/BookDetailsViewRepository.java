package mk.ukim.finki.wp.library.repository;

import mk.ukim.finki.wp.library.model.views.BookDetailsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDetailsViewRepository extends JpaRepository<BookDetailsView,Long> {
}
