import http from 'k6/http';
import { check, sleep } from 'k6';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";
import { textSummary } from "https://jslib.k6.io/k6-summary/0.0.1/index.js";

export const options = {
    stages: [
        { duration: '45s', target: 30 },
        { duration: '1m', target: 30 },
        { duration: '30s', target: 0 },
    ],
    thresholds: {
        http_req_duration: ['avg<400', 'p(95)<500'],
        http_req_failed: ['rate<0.01'],
    },
};

export default function () {
    const url = 'https://tbcbank.ge/ka/tbc-card?openAction=4dRPAp3GfVKLlZkCkDYOl7';
    const res = http.get(url);

    check(res, {
        'status is 200': (r) => r.status === 200,
        'response time < 600ms': (r) => r.timings.duration < 600,
    });

    sleep(1);
}

export function handleSummary(data) {
    return {
        "performance-test-result.html": htmlReport(data),
        stdout: textSummary(data, { indent: " ", enableColors: true }),
    };
}
