
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
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('Metallica', 'ROCK', 164);
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('BTS', 'K_POP', 79);
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('Nirvana', 'METAL', 130);
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('Coldplay', 'K_POP', 142);
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('Linkin Park', 'ROCK', 106);
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('Adele', 'K_POP', 171);
INSERT INTO t_viewer (name, email) VALUES ('Alice Johnson', 'alice@example.com');
INSERT INTO t_viewer (name, email) VALUES ('Bob Smith', 'bob@example.com');
INSERT INTO t_viewer (name, email) VALUES ('Charlie Brown', 'charlie@example.com');
INSERT INTO t_viewer (name, email) VALUES ('Diana Prince', 'diana@example.com');
INSERT INTO t_viewer (name, email) VALUES ('Ethan Hunt', 'ethan@example.com');
INSERT INTO t_viewer (name, email) VALUES ('Fiona Gallagher', 'fiona@example.com');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (5, 2, '2025-04-11 16:14:09.439410', 'C');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (2, 4, '2025-04-09 16:14:09.439422', 'C');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (4, 2, '2025-05-08 16:14:09.439426', 'D');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (2, 6, '2025-05-03 16:14:09.439429', 'B');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (3, 4, '2025-04-17 16:14:09.439432', 'D');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (5, 6, '2025-04-10 16:14:09.439435', 'C');
COMMIT;
