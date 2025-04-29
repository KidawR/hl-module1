-- Создание таблицы t_artist
CREATE TABLE IF NOT EXISTS t_artist (
    id SERIAL PRIMARY KEY,
    name_group VARCHAR(100) NOT NULL,
    genre VARCHAR(50) NOT NULL,
    time_performances INT NOT NULL
    );

-- Создание таблицы t_viewer
CREATE TABLE IF NOT EXISTS t_viewer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
    );

-- Создание таблицы t_ticket
CREATE TABLE IF NOT EXISTS t_ticket (
    id SERIAL PRIMARY KEY,
    artist_id INT REFERENCES t_artist(id) ON DELETE CASCADE,
    viewer_id INT REFERENCES t_viewer(id) ON DELETE CASCADE,
    date VARCHAR(50) NOT NULL,
    sector VARCHAR(10) NOT NULL
    );

-- Вставка тестовых данных в t_artist
INSERT INTO t_artist (name_group, genre, time_performances) VALUES
    ('Metallica', 'METAL', 120),
    ('BTS', 'K_POP', 90),
    ('Nirvana', 'ROCK', 100);

-- Вставка тестовых данных в t_viewer
INSERT INTO t_viewer (name, email) VALUES
    ('Alice Johnson', 'alice@example.com'),
    ('Bob Smith', 'bob@example.com'),
    ('Charlie Brown', 'charlie@example.com');

-- Вставка тестовых данных в t_ticket
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES
    (1, 1, '2024-03-15 19:00:00', 'A'),
    (2, 2, '2024-03-15 20:00:00', 'B'),
    (3, 3, '2024-03-16 18:30:00', 'C');
