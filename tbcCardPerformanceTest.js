import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    stages: [
        { duration: '45s', target: 30 },
        { duration: '1m', target: 30 },
        { duration: '30s', target: 0 },
    ],
    thresholds: {
        http_req_duration: ['avg<1500', 'p(95)<2000'],
        http_req_failed: ['rate<0.05'],
    },
};

export default function () {
    const url = 'https://tbcbank.ge/ka/tbc-card?openAction=4dRPAp3GfVKLlZkCkDYOl7';
    const res = http.get(url);

    check(res, {
        'status is 200': (r) => r.status === 200,
    });

    sleep(1);
}
