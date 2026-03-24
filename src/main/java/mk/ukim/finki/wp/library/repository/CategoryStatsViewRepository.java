package mk.ukim.finki.wp.library.repository;

import mk.ukim.finki.wp.library.model.views.CategoryStatsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryStatsViewRepository extends JpaRepository<CategoryStatsView,Long> {
    @Modifying
    @Query(value = "REFRESH MATERIALIZED VIEW category_stats_view", nativeQuery = true)
    void refresh();

}
