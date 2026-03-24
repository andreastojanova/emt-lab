package mk.ukim.finki.wp.library.model.projection;

import mk.ukim.finki.wp.library.model.domain.Category;
import mk.ukim.finki.wp.library.model.domain.State;
import org.springframework.beans.factory.annotation.Value;

public interface BookProjection {
    Long getId();
    String getName();
    Category getCategory();
    State getState();

    @Value("#{target.copies.?[isAvailable()].size()}")
    Integer getAvailableCopies();

}
