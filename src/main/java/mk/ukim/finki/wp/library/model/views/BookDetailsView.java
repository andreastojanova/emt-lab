package mk.ukim.finki.wp.library.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "book_details_view")
@Getter
public class BookDetailsView {
    @Id
    private Long id;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "category")
    private String category;
    @Column(name = "state")
    private String state;
    @Column(name = "available_copies")
    private Integer availableCopies;
    @Column(name = "author_full_name")
    private String authorFullName;
    @Column(name = "country_name")
    private String countryName;


    public Long getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public String getCategory() {
        return category;
    }

    public String getState() {
        return state;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public String getAuthorFullName() {
        return authorFullName;
    }

    public String getCountryName() {
        return countryName;
    }
}

