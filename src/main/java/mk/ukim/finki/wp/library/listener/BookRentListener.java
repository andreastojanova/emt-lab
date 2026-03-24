package mk.ukim.finki.wp.library.listener;

import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.wp.library.events.BookRentEvent;
import mk.ukim.finki.wp.library.model.domain.ActivityLog;
import mk.ukim.finki.wp.library.model.domain.BookCopy;
import mk.ukim.finki.wp.library.repository.ActivityLogRepositoty;
import mk.ukim.finki.wp.library.scheduler.MaterializedViewRefreshScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class BookRentListener {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BookRentListener.class);
    private final ActivityLogRepositoty activityLogRepositoty;

    public BookRentListener(ActivityLogRepositoty activityLogRepositoty) {
        this.activityLogRepositoty = activityLogRepositoty;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void rentBookEvent(BookRentEvent event) {
        log.info("[ASYNC - thread: {}] Sending confirmation email to '{}'.", Thread.currentThread().getName(),
                event.userEmail());

        long availableCopies=event.book().getCopies().stream().filter(BookCopy::isAvailable).count();
        if (availableCopies==0){
            log.info("No more available copies for book: {}!", event.book().getName());
        }

        ActivityLog activity=new ActivityLog(event.book().getName(),"BOOK_RENTED");
        activityLogRepositoty.save(activity);

        log.info("Activity saved in database for book: {}", event.book().getName());

        simulateSendEmail(event);
    }

    private void simulateSendEmail(BookRentEvent event) {
        try {
            Thread.sleep(2000);
            log.info("Email sent to '{}'.", event.userEmail());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


}
