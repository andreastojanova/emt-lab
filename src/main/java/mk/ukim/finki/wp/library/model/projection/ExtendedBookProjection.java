package mk.ukim.finki.wp.library.model.projection;

import mk.ukim.finki.wp.library.model.domain.Category;
import mk.ukim.finki.wp.library.model.domain.State;
import org.springframework.beans.factory.annotation.Value;

public interface ExtendedBookProjection extends BookProjection{
    @Value("#{target.author.name + ' '+ target.author.surname}")
    String getAuthorFullName();

    @Value("#{target.author.country.name}")
    String getAuthorCountry();

}
