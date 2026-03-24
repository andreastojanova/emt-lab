package mk.ukim.finki.wp.library.events;

import mk.ukim.finki.wp.library.model.domain.Book;

public record BookRentEvent(Book book,String userEmail) {
}
