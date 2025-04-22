import http from 'k6/http';
import { check } from 'k6';

export const options = {
    vus: 150, // количество одновременных пользователей
    duration: '60s', // общее время теста
};

export default function () {
    const chance = Math.random();

    if (chance < 0.05) { // 5% запросов - создание клиента
        const payload = JSON.stringify({
            name: 'Client ${__VU}_${__ITER}',
            email: '+7${Math.floor(9000000000 + Math.random() * 100000000)}'
        });

        const headers = {
            'accept': '*/*',
            'Content-Type': 'application/json'
        };

        const res = http.post('http://192.168.1.232:8080/viewers', payload, { headers });

        check(res, {
            'client created successfully': (r) => r.status === 200 || r.status === 201,
        });

    } else { // 95% запросов - выполнение доп задания
        const res = http.get('http://192.168.1.232:8080/tickets/stats');

        check(res, {
            'clients retrieved successfully': (r) => r.status === 200,
        });
    }
}