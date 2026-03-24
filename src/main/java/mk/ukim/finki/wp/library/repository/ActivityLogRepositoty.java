package mk.ukim.finki.wp.library.repository;

import mk.ukim.finki.wp.library.model.domain.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityLogRepositoty extends JpaRepository<ActivityLog,Long> {
}
