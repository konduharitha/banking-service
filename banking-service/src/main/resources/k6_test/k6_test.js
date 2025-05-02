import http from 'k6/http';
import { check } from 'k6';

// run : k6 run k6_test.js
// To run and to test optimistic locking transactions

export let options = {
    vus: 2,           // 2 concurrent users
    iterations: 2,    // Total of 2 requests (1 per VU)
};

export default function () {
    const url = 'http://localhost:8080/api/accounts/debit';
    const payload = JSON.stringify({
        userId: 1,
        debitAmount: 1000,
        transactionDetails: "fee"
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    let res = http.post(url, payload, params);

    // Optional: check response
    check(res, {
        'status is 200': (r) => r.status === 200,
    });
}

