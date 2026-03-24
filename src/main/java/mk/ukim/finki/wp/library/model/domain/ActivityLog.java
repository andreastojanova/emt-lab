package mk.ukim.finki.wp.library.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookName;
    private LocalDateTime eventTime;
    private String eventType;

    public ActivityLog(String bookName,String eventType) {
        this.bookName = bookName;
        this.eventTime = LocalDateTime.now();
        this.eventType = eventType;
    }

    public ActivityLog() {
    }

    public Long getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public String getEventType() {
        return eventType;
    }
}
