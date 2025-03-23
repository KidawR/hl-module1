
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
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('Metallica', 'METAL', 177);
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('BTS', 'K_POP', 119);
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('Nirvana', 'METAL', 134);
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('Coldplay', 'ROCK', 121);
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('Linkin Park', 'ROCK', 75);
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('Adele', 'K_POP', 98);
INSERT INTO t_viewer (name, email) VALUES ('Alice Johnson', 'alice@example.com');
INSERT INTO t_viewer (name, email) VALUES ('Bob Smith', 'bob@example.com');
INSERT INTO t_viewer (name, email) VALUES ('Charlie Brown', 'charlie@example.com');
INSERT INTO t_viewer (name, email) VALUES ('Diana Prince', 'diana@example.com');
INSERT INTO t_viewer (name, email) VALUES ('Ethan Hunt', 'ethan@example.com');
INSERT INTO t_viewer (name, email) VALUES ('Fiona Gallagher', 'fiona@example.com');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (2, 5, '2025-04-18 13:31:29.527282', 'A');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (2, 5, '2025-04-16 13:31:29.527292', 'A');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (2, 3, '2025-04-10 13:31:29.527296', 'C');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (2, 6, '2025-04-18 13:31:29.527299', 'A');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (4, 4, '2025-04-11 13:31:29.527302', 'C');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (1, 1, '2025-04-02 13:31:29.527305', 'C');
COMMIT;
