
        BEGIN TRANSACTION;

        CREATE TABLE t_artist (
            id BIGSERIAL PRIMARY KEY,
            name_group VARCHAR(255) NOT NULL,
            genre VARCHAR(50) NOT NULL,
            time_performances INT NOT NULL
        );

        CREATE TABLE t_viewer (
            id BIGSERIAL PRIMARY KEY,
            name VARCHAR(255) NOT NULL,
            email VARCHAR(255) UNIQUE NOT NULL
        );

        CREATE TABLE t_ticket (
            id BIGSERIAL PRIMARY KEY,
            date VARCHAR(50) NOT NULL,
            sector VARCHAR(10) NOT NULL,
            viewer_id BIGINT NOT NULL,
            artist_id BIGINT NOT NULL,
            CONSTRAINT fk_ticket_viewer FOREIGN KEY (viewer_id) REFERENCES t_viewer(id) ON DELETE CASCADE,
            CONSTRAINT fk_ticket_artist FOREIGN KEY (artist_id) REFERENCES t_artist(id) ON DELETE CASCADE
        );


        COMMIT;
        
BEGIN TRANSACTION;
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('Metallica', 'ROCK', 135);
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('BTS', 'METAL', 88);
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('Nirvana', 'METAL', 72);
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('Coldplay', 'ROCK', 68);
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('Linkin Park', 'K_POP', 90);
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('Adele', 'METAL', 83);
INSERT INTO t_viewer (name, email) VALUES ('Alice Johnson', 'alice@example.com');
INSERT INTO t_viewer (name, email) VALUES ('Bob Smith', 'bob@example.com');
INSERT INTO t_viewer (name, email) VALUES ('Charlie Brown', 'charlie@example.com');
INSERT INTO t_viewer (name, email) VALUES ('Diana Prince', 'diana@example.com');
INSERT INTO t_viewer (name, email) VALUES ('Ethan Hunt', 'ethan@example.com');
INSERT INTO t_viewer (name, email) VALUES ('Fiona Gallagher', 'fiona@example.com');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (2, 1, '2025-03-26 18:15:29.859240', 'B');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (3, 1, '2025-04-09 18:15:29.859443', 'D');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (5, 1, '2025-03-30 18:15:29.859490', 'D');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (4, 5, '2025-04-09 18:15:29.859519', 'B');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (3, 5, '2025-03-24 18:15:29.859532', 'A');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (6, 5, '2025-03-23 18:15:29.859574', 'D');
COMMIT;
