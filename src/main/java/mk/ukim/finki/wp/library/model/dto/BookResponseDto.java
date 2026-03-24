package mk.ukim.finki.wp.library.model.dto;

import mk.ukim.finki.wp.library.model.domain.Book;
import mk.ukim.finki.wp.library.model.domain.BookCopy;
import mk.ukim.finki.wp.library.model.domain.Category;
import mk.ukim.finki.wp.library.model.domain.State;

public record BookResponseDto (Long id,
                               String name,
                               Category category,
                               String authorName,
                               State state,
                               boolean hasAvailableCopies){
    public static BookResponseDto from (Book book){

        boolean available=false;
        if (book.getCopies()!=null){
            available=book.getCopies().stream().anyMatch(BookCopy::isAvailable);
        }

        String fullAuthorName=book.getAuthor().getName() + " " +book.getAuthor().getSurname();

        return new BookResponseDto(book.getId(),
                book.getName(),
                book.getCategory(),
                fullAuthorName,
                book.getState(),
                available);
    }
}
