CREATE MATERIALIZED VIEW category_stats_view AS
SELECT
    b.category AS category,
    COUNT(DISTINCT b.id) AS total_books,
    SUM(CASE WHEN bc.is_available = true THEN 1 ELSE 0 END) AS total_available_copies,
    COUNT(DISTINCT CASE WHEN b.state != 'GOOD' THEN b.id END) AS bad_condition_books
FROM books b
         LEFT JOIN book_copies bc ON b.id = bc.book_id
GROUP BY b.category;