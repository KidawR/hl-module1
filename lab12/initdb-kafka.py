import argparse
import json
import random
from faker import Faker
from kafka import KafkaProducer
from datetime import datetime

fake = Faker()

# Настройки подключения к Kafka
producer = KafkaProducer(
    bootstrap_servers=["127.0.0.2:9094"],
    value_serializer=lambda v: json.dumps(v).encode("utf-8")
)

TOPIC_NAME = "var16"  # Тема Kafka для данных

# Функции генерации данных для Artist, Viewer и Ticket
def generate_artist_payload():
    genres = ["METAL", "K_POP", "ROCK"]
    return {
        "nameGroup": fake.company(),
        "genre": random.choice(genres),
        "timePerformances": random.randint(0, 315360000)  # время выступлений в секундах
    }

def generate_viewer_payload():
    return {
        "name": fake.name(),
        "email": fake.email()
    }

def generate_ticket_payload(artist_id, viewer_id):
    sector = ["A", "B", "C", "D"]
    return {
        "artistId": artist_id,
        "viewerId": viewer_id,
        "date": fake.date_time_this_decade().isoformat(),  # преобразуем в строку
        "sector": random.choice(sector)
    }

# Обертка для сообщения, отправляемого в Kafka
def wrap_message(entity, operation, payload):
    return {
        "entity": entity,
        "operation": operation,
        "payload": payload
    }

def main():
    parser = argparse.ArgumentParser(description="Kafka data generator for artists, viewers, and tickets")
    parser.add_argument("--entity", required=True, choices=["ARTIST", "VIEWER", "TICKET"])
    parser.add_argument("--operation", default="POST", choices=["POST", "DELETE"])
    parser.add_argument("--count", type=int, default=10, help="Количество создаваемых объектов")
    parser.add_argument("--id", type=int, help="ID для DELETE операции")
    parser.add_argument("--artist_id", type=int, help="ID артиста для генерации билета")
    parser.add_argument("--viewer_id", type=int, help="ID зрителя для генерации билета")

    args = parser.parse_args()

    if args.operation == "POST":
        if args.entity == "ARTIST":
            for _ in range(args.count):
                payload = generate_artist_payload()
                message = wrap_message("ARTIST", "POST", payload)
                producer.send(TOPIC_NAME, message)

        elif args.entity == "VIEWER":
            for _ in range(args.count):
                payload = generate_viewer_payload()
                message = wrap_message("VIEWER", "POST", payload)
                producer.send(TOPIC_NAME, message)

        elif args.entity == "TICKET":
            if args.artist_id is None or args.viewer_id is None:
                print("Для генерации билетов необходимо указать --artist_id и --viewer_id.")
                return

            for _ in range(args.count):
                payload = generate_ticket_payload(args.artist_id, args.viewer_id)
                message = wrap_message("TICKET", "POST", payload)
                producer.send(TOPIC_NAME, message)

        print(f"Отправлено {args.count} {args.entity} POST сообщений в топик {TOPIC_NAME}.")

    elif args.operation == "DELETE":
        if args.id is None:
            print("Ошибка: --id обязателен для операции DELETE.")
            return

        message = wrap_message(args.entity, "DELETE", {"id": args.id})
        producer.send(TOPIC_NAME, message)
        print(f"Отправлено DELETE сообщение для {args.entity} с id={args.id} в топик {TOPIC_NAME}.")

    producer.flush()

if __name__ == "__main__":
    main()


#artists  50
#viewers  50
#tickets  250
#python initdb-kafka.py --entity ARTIST --count 50
# python initdb-kafka.py --entity TICKET --count 2 --artist_id 3 --viewer_id 944