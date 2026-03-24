package mk.ukim.finki.wp.library.web.controller;


import mk.ukim.finki.wp.library.model.domain.ActivityLog;
import mk.ukim.finki.wp.library.repository.ActivityLogRepositoty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
    private final ActivityLogRepositoty activityLogRepositoty;

    public ActivityController(ActivityLogRepositoty activityLogRepositoty) {
        this.activityLogRepositoty = activityLogRepositoty;
    }

    @GetMapping
    public Page<ActivityLog> findAll(Pageable pageable){
        return activityLogRepositoty.findAll(pageable);
    }
}
