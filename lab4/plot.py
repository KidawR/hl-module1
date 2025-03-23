import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import numpy as np

# Устанавливаем стиль Seaborn
sns.set(style="whitegrid", palette="pastel")

def plot_response_vs_load_from_csv(csv_file):
    df = pd.read_csv(csv_file)

    # Получаем данные для VUS и времени отклика
    df_vus = df[df["metric_name"] == "vus"][["timestamp", "metric_value"]]
    df_vus.rename(columns={"metric_value": "vus"}, inplace=True)

    df_http_req_duration = df[df["metric_name"] == "http_req_duration"][["timestamp", "metric_value"]]
    df_http_req_duration.rename(columns={"metric_value": "http_req_duration"}, inplace=True)

    # Преобразуем timestamp
    df_vus["timestamp"] = pd.to_datetime(df_vus["timestamp"], unit="s")
    df_http_req_duration["timestamp"] = pd.to_datetime(df_http_req_duration["timestamp"], unit="s")

    # Объединяем данные
    df_merged = pd.merge_asof(df_http_req_duration.sort_values("timestamp"),
                              df_vus.sort_values("timestamp"),
                              on="timestamp", direction="nearest")

    plt.figure(figsize=(12, 6))

    # Изменяем стиль точек и добавляем прозрачность
    plt.scatter(df_merged["vus"], df_merged["http_req_duration"],
                color="royalblue", alpha=0.7, s=50, edgecolors="darkblue", linewidth=1, label="Время отклика")

    # Настраиваем сетку
    plt.grid(True, linestyle="--", linewidth=0.5, alpha=0.7)

    # Настройка меток
    plt.xticks(np.arange(0, df_merged["vus"].max() + 1, step=2))
    plt.yticks(np.arange(0, df_merged["http_req_duration"].max() + 1, step=5))

    # Добавляем подписи и заголовок
    plt.xlabel("Нагрузка (VUS)", fontsize=14)
    plt.ylabel("Время отклика (мс)", fontsize=14)
    plt.title("Зависимость времени отклика от нагрузки", fontsize=16)
    plt.legend(fontsize=12)

    # Сохраняем график
    plt.savefig("график.png", dpi=300, bbox_inches='tight')
    plt.close()  # Закрываем график после сохранения

plot_response_vs_load_from_csv("file.csv")
