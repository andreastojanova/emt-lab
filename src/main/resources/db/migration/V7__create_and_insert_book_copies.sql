CREATE TABLE book_copies (
                             id BIGSERIAL PRIMARY KEY,
                             barcode VARCHAR(255) UNIQUE NOT NULL,
                             state VARCHAR(255),
                             is_available BOOLEAN DEFAULT TRUE,
                             book_id BIGINT REFERENCES books(id) ON DELETE CASCADE,
                             created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                             updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

INSERT INTO book_copies (barcode, state, is_available, book_id)
SELECT
    'BC-' || b.id || '-' || s.num,  -- Генерира баркод пр: BC-1-1, BC-1-2...
    b.state,                        -- Ја презема состојбата од книгата
    TRUE,                           -- Сите нови копии се слободни за старт
    b.id                            -- Поврзување со книгата
FROM books b
         CROSS JOIN LATERAL generate_series(1, b.available_copies) AS s(num)
WHERE b.available_copies > 0;

ALTER TABLE books DROP COLUMN available_copies;