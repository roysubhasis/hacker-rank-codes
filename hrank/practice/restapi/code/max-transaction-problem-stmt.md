# Hackerrank : REST API : Find Max transactions

Use HTTP GET method to retrieve information from a database related to maximum transaction.

**Query:**   

https://jsonmock.hackerrank.com/api/transactions?name=< name >&city=< city > to find all transaction records of the user and city combination.
The query result is paginated. The access additional pages, append &page=< num > to the URL where num is the page number.

Sample JSON Response:

```
{
    "page": 1,
    "per_page": 10,
    "total": 300,
    "total_pages": 30,
    "data": [
        {
            "id": 1,
            "userId": 1,
            "userName": "John Oliver",
            "timestamp": 1549536882071,
            "txnType": "debit",
            "amount": "$1,670.57",
            "location": {
                "id": 7,
                "address": "770, Deepends, Stockton Street",
                "city": "Ripley",
                "zipCode": 44139
            },
            "ip": "212.215.115.165"
        }
    ]
}
```

Fields in the JSON are given below,
* page: Current page of the result (number)
* per_page: Max number of result per page
* total: Total number of result
* total_pages: Total number of pages with results.
* data: An array with multiple objects, where a single object that contains user records.


Find Max transaction of a user in the corresponding city.
