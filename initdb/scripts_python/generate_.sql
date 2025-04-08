
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
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('Metallica', 'METAL', 90);
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('BTS', 'METAL', 116);
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('Nirvana', 'METAL', 89);
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('Coldplay', 'METAL', 172);
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('Linkin Park', 'METAL', 143);
INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('Adele', 'METAL', 62);
INSERT INTO t_viewer (name, email) VALUES ('Alice Johnson', 'alice@example.com');
INSERT INTO t_viewer (name, email) VALUES ('Bob Smith', 'bob@example.com');
INSERT INTO t_viewer (name, email) VALUES ('Charlie Brown', 'charlie@example.com');
INSERT INTO t_viewer (name, email) VALUES ('Diana Prince', 'diana@example.com');
INSERT INTO t_viewer (name, email) VALUES ('Ethan Hunt', 'ethan@example.com');
INSERT INTO t_viewer (name, email) VALUES ('Fiona Gallagher', 'fiona@example.com');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (6, 5, '2025-04-22 15:52:12.538790', 'B');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (5, 5, '2025-04-26 15:52:12.538805', 'C');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (2, 1, '2025-04-15 15:52:12.538809', 'D');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (2, 2, '2025-04-14 15:52:12.538812', 'C');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (2, 3, '2025-04-21 15:52:12.538815', 'C');
INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES (2, 6, '2025-04-15 15:52:12.538818', 'D');
COMMIT;
