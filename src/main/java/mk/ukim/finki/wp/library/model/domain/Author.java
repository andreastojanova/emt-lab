package mk.ukim.finki.wp.library.model.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="authors")
@NoArgsConstructor
@Getter
@Setter
public class Author extends BaseEntity {

    private String name;
    private String surname;
    @ManyToOne
    @JoinColumn(name="country_id")
    private Country country;

    public Author(String name, String surname, Country country) {
        super();
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
    public Author(){}



}
