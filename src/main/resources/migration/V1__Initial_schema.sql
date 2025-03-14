
-- Вставка тестовых данных в t_Artist
INSERT INTO t_Artist (name_group, genre, time_performances) VALUES
    ('Metallica', 'METAL', 120),
    ('BTS', 'K_POP', 90),
    ('Nirvana', 'ROCK', 100);



-- Вставка тестовых данных в t_Viewer
INSERT INTO t_Viewer (name, email) VALUES
    ('Alice Johnson', 'alice@example.com'),
    ('Bob Smith', 'bob@example.com'),
    ('Charlie Brown', 'charlie@example.com');


-- Вставка тестовых данных в t_Ticket
INSERT INTO t_Ticket (artist_id, viewer_id, date, sector) VALUES
    (1, 1, '2024-03-15 19:00:00', 'A'),
    (2, 2, '2024-03-15 20:00:00', 'B'),
    (3, 3, '2024-03-16 18:30:00', 'C');
);