package mk.ukim.finki.wp.library.model.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name="books")
@NamedEntityGraph(
        name = "book-entity-graph",
        attributeNodes = {
                @NamedAttributeNode(value="author"),
                @NamedAttributeNode(value = "author",subgraph = "author-subgraph"),
                @NamedAttributeNode(value="copies")
        },
        subgraphs = {
                @NamedSubgraph(
                        name="author-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("country")
                        }
                )
        }
)
public class Book extends BaseEntity {


    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;
    @Enumerated(EnumType.STRING)
    private State state;

    @OneToMany(mappedBy ="book")
    private List<BookCopy> copies;

    public Book(String name, Category category, Author author) {
        this.name = name;
        this.category = category;
        this.author = author;


    }

    public Book(){}

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Author getAuthor() {
        return author;
    }

    public State getState() {
        return state;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<BookCopy> getCopies() {
        return copies;
    }
}
