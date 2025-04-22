import random
from datetime import datetime, timedelta

def generate_sql(filename, num_artists=250, num_viewers=250, num_tickets=1000):
    genres = ["METAL", "K_POP", "ROCK"]
    artist_names = ["Metallica", "BTS", "Nirvana", "Coldplay", "Linkin Park", "Adele"]
    viewer_names = [
        "Alice Johnson", "Bob Smith", "Charlie Brown",
        "Diana Prince", "Ethan Hunt", "Fiona Gallagher"
    ]
    viewer_emails = [
        "alice@example.com", "bob@example.com",
        "charlie@example.com", "diana@example.com",
        "ethan@example.com", "fiona@example.com"
    ]

    with open(filename, 'w', encoding='utf-8') as f:
        f.write("""
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
        """)

        f.write("\nBEGIN TRANSACTION;\n")

        # Вставка данных в t_artist
        for i in range(num_artists):
            name_group = artist_names[i]
            genre = random.choice(genres)
            time_performances = random.randint(60, 180)  # Время выступления от 60 до 180 минут
            f.write(
                f"INSERT INTO t_artist (name_group, genre, time_performances) VALUES ('{name_group}', '{genre}', {time_performances});\n"
            )

        # Вставка данных в t_viewer
        for i in range(num_viewers):
            name = viewer_names[i]
            email = viewer_emails[i]
            f.write(
                f"INSERT INTO t_viewer (name, email) VALUES ('{name}', '{email}');\n"
            )

        # Вставка данных в t_ticket
        for i in range(num_tickets):
            artist_id = random.randint(1, num_artists)
            viewer_id = random.randint(1, num_viewers)
            date = datetime.now() + timedelta(days=random.randint(1, 30))  # Случайная дата в будущем
            sector = random.choice(['A', 'B', 'C', 'D'])
            f.write(
                f"INSERT INTO t_ticket (artist_id, viewer_id, date, sector) VALUES ({artist_id}, {viewer_id}, '{date}', '{sector}');\n"
            )

        f.write("COMMIT;\n")


generate_sql("./scripts_python/generate_.sql")
