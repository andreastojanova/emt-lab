package mk.ukim.finki.wp.library.specification;

import mk.ukim.finki.wp.library.model.domain.Book;
import mk.ukim.finki.wp.library.model.domain.Category;
import mk.ukim.finki.wp.library.model.domain.State;
import org.springframework.data.jpa.domain.Specification;

import java.util.Calendar;

public class BookSpecification {

    public static Specification<Book> hasCategory(Category category){
        return (root, query, criteriaBuilder) ->
                category == null?null:criteriaBuilder.equal(root.get("category"),category);
    }

    public static Specification<Book> hasState(State state){
        return (root, query, criteriaBuilder) ->
                state == null?null:criteriaBuilder.equal(root.get("state"),state);
    }

    public static Specification<Book> hasAuthor(Long authorId){
        return (root, query, criteriaBuilder) ->
                authorId == null?null:criteriaBuilder.equal(root.get("author").get("id"),authorId);
    }

    public static Specification<Book> hasAvailableCopies(Boolean available){
        return (root, query, criteriaBuilder) ->
        {
            if (available == null) {
                return null;
            }

            if (available){
                return criteriaBuilder.isTrue(root.join("copies").get("isAvailable"));
            }else {
                return criteriaBuilder.isFalse(root.join("copies").get("isAvailable"));
            }
        };
    }



}
