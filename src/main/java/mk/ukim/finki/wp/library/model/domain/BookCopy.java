package mk.ukim.finki.wp.library.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "book_copies")
public class BookCopy extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String barcode;
    @Enumerated(EnumType.STRING)
    private State state;
    @Column(name = "is_available")
    private boolean isAvailable = true;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    @JsonIgnore
    private Book book;

    public BookCopy(String barcode, State state, boolean isAvailable) {
        this.barcode = barcode;
        this.state = state;
        this.isAvailable = isAvailable;
    }

    public BookCopy() {
    }

    public String getBarcode() {
        return barcode;
    }

    public State getState() {
        return state;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Book getBook() {
        return book;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
