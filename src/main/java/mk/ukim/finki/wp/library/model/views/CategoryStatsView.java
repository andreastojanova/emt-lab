package mk.ukim.finki.wp.library.model.views;

import jakarta.persistence.*;
import mk.ukim.finki.wp.library.model.domain.Category;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "category_stats_view")
public class CategoryStatsView {
    @Id
    @Enumerated(EnumType.STRING)
    Category category;
    @Column(name = "total_books")
    private int totalBooks;
    @Column(name = "total_available_copies")
    private int totalAvailableCopies;
    @Column(name = "bad_condition_books")
    private int badConditionBooks;

    public Category getCategory() {
        return category;
    }

    public int getTotalBooks() {
        return totalBooks;
    }

    public int getTotalAvailableCopies() {
        return totalAvailableCopies;
    }

    public int getBadConditionBooks() {
        return badConditionBooks;
    }
}
