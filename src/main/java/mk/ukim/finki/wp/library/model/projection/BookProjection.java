package mk.ukim.finki.wp.library.model.projection;

public interface BookProjection {
    String getName();
    AuthorProjection getAuthor();

    interface AuthorProjection {
        String getName();
        String getSurname();
    }
}
