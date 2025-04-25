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
    # Получаем ID артистов и зрителей для создания билета
    artists_response = requests.get('http://localhost:8080/artists')
    viewers_response = requests.get('http://localhost:8080/viewers')
    artists = artists_response.json()
    viewers = viewers_response.json()
    # Проверяем наличие артистов и зрителей
    if not artists and endpoint == "tickets":
        populate_data("artists", count, clear)  # Создаем хотя бы одного артиста
        artists_response = requests.get('http://localhost:8080/artists')  # Обновляем список артистов
        artists = artists_response.json()  # Обновляем переменную artists

    if not viewers and endpoint == "tickets":
        populate_data("viewers", count, clear)  # Создаем хотя бы одного зрителя
        viewers_response = requests.get('http://localhost:8080/viewers')  # Обновляем список зрителей
        viewers = viewers_response.json()  # Обновляем переменную viewers

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
             if artists_response.status_code == 200 and viewers_response.status_code == 200:


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

#artists  50
#viewers  50
#tickets  250

#python initdb.py --count 2 --endpoint tickets --clear
