package mk.ukim.finki.wp.library.scheduler;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.wp.library.repository.CategoryStatsViewRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MaterializedViewRefreshScheduler {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MaterializedViewRefreshScheduler.class);

    private final CategoryStatsViewRepository categoryStatsViewRepository;

    public MaterializedViewRefreshScheduler(CategoryStatsViewRepository categoryStatsViewRepository) {
        this.categoryStatsViewRepository = categoryStatsViewRepository;
    }

    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void refreshCategoryStatsView(){
        log.info("Refreshing CATEGORY_STATS_VIEW...");
        categoryStatsViewRepository.refresh();
        log.info("CATEGORY_STATS_VIEW successfully refreshed.");

    }
}


