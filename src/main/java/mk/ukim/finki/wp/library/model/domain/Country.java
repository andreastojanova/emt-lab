package mk.ukim.finki.wp.library.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="countries")

@Getter
@Setter
public class Country extends BaseEntity {


    private String name;
    private String continent;

    public Country(String name, String continent) {
        super();
        this.name = name;
        this.continent = continent;
    }
    public Country(){}
}
