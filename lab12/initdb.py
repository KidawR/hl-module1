import argparse
import requests
import random
from faker import Faker

# Инициализация Faker
fake = Faker()

def clear_data(endpoint):
    """Удаляет все данные из указанного эндпоинта."""
    response = requests.delete(f'http://localhost:8080/{endpoint}/clear')
    if response.status_code == 200:
        print(f"Данные в {endpoint} успешно очищены.")
    else:
        print(f"Ошибка при очистке данных в {endpoint}: {response.status_code}")

def create_artist():
    """Создает тестового артиста."""
    genres = ["METAL", "K_POP", "ROCK"]
    return {
        "nameGroup": fake.company(),
        "genre": random.choice(genres),
        "timePerformances": int(random.randint(0, 315360000))  # Преобразуем в строку
    }

def create_viewer():
    """Создает тестового зрителя."""
    return {
        "name": fake.name(),
        "email": fake.email()
    }

def create_ticket(artist_id, viewer_id):
    """Создает тестовый билет."""
    sector = ["A", "B", "C", "D"]
    return {
        "artistId": artist_id,
        "viewerId": viewer_id,
        "date": fake.date_time_this_decade().isoformat(),  # Преобразуем в строку
        "sector": random.choice(sector)
    }

def populate_data(endpoint, count, clear):
    """Заполняет указанный эндпоинт тестовыми данными."""
    if count <= 0:
        return  # Если count меньше или равен 0, ничего не делаем

    for _ in range(count):
        if endpoint == "artists":
            artist_data = create_artist()
            response = requests.post(f'http://localhost:8080/{endpoint}', json=artist_data)
            if response.status_code == 200:
                print(f"Артист создан: {artist_data['nameGroup']} с ID {response.json()['id']}")
            else:
                print(f"Ошибка при создании артиста: {response.status_code}, {response.text}")

        elif endpoint == "viewers":
            viewer_data = create_viewer()
            response = requests.post(f'http://localhost:8080/{endpoint}', json=viewer_data)
            if response.status_code == 200:
                print(f"Зритель создан: {viewer_data['name']} с ID {response.json()['id']}")
            else:
                print(f"Ошибка при создании зрителя: {response.status_code}, {response.text}")

        elif endpoint == "tickets":
            # Получаем ID артистов и зрителей для создания билета
            artists_response = requests.get('http://localhost:8080/artists')
            viewers_response = requests.get('http://localhost:8080/viewers')
            if artists_response.status_code == 200 and viewers_response.status_code == 200:
                artists = artists_response.json()
                viewers = viewers_response.json()

                # Проверяем наличие артистов и зрителей
                if not artists:
                    populate_data("artists", count, clear)  # Создаем хотя бы одного артиста
                    artists_response = requests.get('http://localhost:8080/artists')  # Обновляем список артистов
                    artists = artists_response.json()  # Обновляем переменную artists

                if not viewers:
                    populate_data("viewers", count, clear)  # Создаем хотя бы одного зрителя
                    viewers_response = requests.get('http://localhost:8080/viewers')  # Обновляем список зрителей
                    viewers = viewers_response.json()  # Обновляем переменную viewers

                if artists and viewers:
                    # Выбираем случайного артиста и зрителя
                    if clear:
                        artist_id = artists[_]['id']
                        viewer_id = viewers[_]['id']
                    else:
                        artist_id = random.choice(artists)['id']
                        viewer_id = random.choice(viewers)['id']
                    ticket_data = create_ticket(artist_id, viewer_id)
                    response = requests.post(f'http://localhost:8080/{endpoint}', json=ticket_data)
                    if response.status_code == 200:
                        print(f"Билет создан для артиста ID {artist_id} и зрителя ID {viewer_id}")
                    else:
                        print(f"Ошибка при создании билета: {response.status_code}, {response.text}")


def main():
    # Настройка аргументов командной строки
    parser = argparse.ArgumentParser(description="Заполнение REST-сервиса тестовыми данными.")
    parser.add_argument('--count', type=int, default=500, help='Количество создаваемых объектов (по умолчанию 500).')
    parser.add_argument('--endpoint', type=str, required=True, help='API-эндпоинт (например, artists, viewers или tickets).')
    parser.add_argument('--clear', action='store_true', help='Очистить данные перед заполнением.')
    args = parser.parse_args()

    if args.clear:
        clear_data("tickets")
        clear_data("viewers")
        clear_data("artists")


    populate_data(args.endpoint, args.count, args.clear)

if __name__ == "__main__":
    main()

#artists
#viewers
#tickets

#python initdb.py --count 2 --endpoint tickets --clear

# lab 15 K105ab923cd8dd4d16eaa836a4767d986aabfa30f0bcb09f6f0952d67d2da7408e5::server:4ee4b063bff35facea195253feb268f6
# 10.60.3.30/24

# токен eyJhbGciOiJSUzI1NiIsImtpZCI6IjFKZnJWZWFVWmRjYnJqRHBzSmZhX09SNXBvOEszaVB3YnhnMms2cVFrZ00ifQ.eyJhdWQiOlsiaHR0cHM6Ly9rdWJlcm5ldGVzLmRlZmF1bHQuc3ZjLmNsdXN0ZXIubG9jYWwiLCJrM3MiXSwiZXhwIjoxNzQ1OTI4MDUyLCJpYXQiOjE3NDU5MjQ0NTIsImlzcyI6Imh0dHBzOi8va3ViZXJuZXRlcy5kZWZhdWx0LnN2Yy5jbHVzdGVyLmxvY2FsIiwianRpIjoiMjUzMGRiZTYtNzgyOC00MmVhLWFhNGUtNGY3NDdmODNmNGEzIiwia3ViZXJuZXRlcy5pbyI6eyJuYW1lc3BhY2UiOiJrdWJlcm5ldGVzLWRhc2hib2FyZCIsInNlcnZpY2VhY2NvdW50Ijp7Im5hbWUiOiJhZG1pbi11c2VyIiwidWlkIjoiOGM2MzhmM2ItMjU4NS00YjZlLTk0MTgtY2RkZWRlNThmMGQyIn19LCJuYmYiOjE3NDU5MjQ0NTIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDprdWJlcm5ldGVzLWRhc2hib2FyZDphZG1pbi11c2VyIn0.oGmuU0tUbbCUaPA2sW8HpBjzZ-K3oY9MF0lBVHQo46QF98g0jxEqLgWiWbVxBe8DxaixYHfS6yyOownM9U5owbrPz_9MLQQmBJVmw_7jXHDIQi2VOZSg3ajxhhXf06h1hgBGw1bNxmWCZU2Q8L3TsEvJiV61Lf9oeiQ76Bgftiocq5jSaJcyQ4By3NP_oFU3BVhjZWu6i6QuxGEHDs2qgPZTvKvV8oPm0W0pY-4bJt0Qe1BTzuNf112PjSrOB322D9duaNyow3PrEVgbgCeHbQjOPCY9C_-Q0O4nq4bjVIkiEcXrGu7vnU_rfcoSmtVsnE8qTLCkprHXzcD5Du_4wA
