CREATE VIEW book_details_view AS
SELECT
    b.id AS id,
    b.name AS book_name,
    b.category AS category,
    b.state AS state,
    (SELECT COUNT(*) FROM book_copies bc WHERE bc.book_id = b.id AND bc.is_available = true) AS available_copies,
    CONCAT(a.name, ' ', a.surname) AS author_full_name,
    c.name AS country_name
FROM books b
         JOIN authors a ON b.author_id = a.id
         JOIN countries c ON a.country_id = c.id;