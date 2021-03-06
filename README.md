# Bank Account

### Implementation of a small core banking solution
  
  • Creates accounts to keep track of current accounts, balances, and transaction history
  
  • Capable of publishing messages into RabbitMQ for other consumers
      
&nbsp;

Technologies
---
    
• Java 14

• SpringBoot

• MyBatis

• Gradle

• Postgres

• RabbitMQ

• JUnit

• Lombok

    
&nbsp;

Build & Run
---

Run project on your IDE.

On sources root folder, open your terminal:

  - Start containers:
  
  ```console 
  foo@bar:~$ cd src/main/resources/container
  
  foo@bar:~$ docker-compose up -d
  ```
  
  If needed, install Lombok -> https://projectlombok.org/setup/overview
  
<br> 
  
  - Stop containers and remove volumes:
  
  ```console
  foo@bar:~$ cd src/main/resources/container
  
  foo@bar:~$ docker-compose down -v
  ```
  
&nbsp;

Connect to Postgres/RabbitMQ
---

  - Postgres
  
> Port: 5432
> 
> User: postgres
> 
> Password: postgres
  
<br> 
    
  - RabbitMQ
  
> http://localhost:15672/#/exchanges
> 
> User: guest
> 
> Password: guest
>
> destination: processed_messages
  
&nbsp;

Tests
---

On sources root folder, open your terminal:

  - Generate test coverage reports:
  
  Ubuntu:
  ```console
  foo@bar:~$ ./gradlew clean build
  
  foo@bar:~$ ./gradlew jacocoTestReport
  ```
  
  Windows:
  ```console
  foo@bar:~$ gradlew clean build
  
  foo@bar:~$ gradlew jacocoTestReport
  ```
  
  Test results wil be located on folder <b><i>build/reports/jacoco/test/html/index.html</b></i>

&nbsp;

API Contract
---


#### [POST] - Create account

  ```
  /api/v1/account
  ```

<details>
  <summary>Body</summary>
  
<br> 
  
  > <b>customerId</b> <i>number</i>
  > 
  > <b>country</b> <i>string</i>
  > 
  > <b>currencies</b> <i>array[string]</i>
  > <sub><sup>* Accepted values: EUR | SEK | GBP | USD</sup></sub>
  
  ```json
    {
      "customerId": 123,
      "country": "Brazil",
      "currencies": ["EUR", "SEK", "GBP", "USD"]
    }
  ```
</details>

<details>
  <summary>Response</summary>
    
<br> 
    
  ```json
{
    "accountId": 1,
    "customerId": 123,
    "balances": [
        {
            "accountId": 1,
            "availableAmount": 0,
            "currency": "EUR"
        },
        {
            "accountId": 1,
            "availableAmount": 0,
            "currency": "SEK"
        },
        {
            "accountId": 1,
            "availableAmount": 0,
            "currency": "GBP"
        },
        {
            "accountId": 1,
            "availableAmount": 0,
            "currency": "USD"
        }
    ]
}
  ```
</details>

&nbsp;

#### [GET] - Find account by ID

  ```
  /api/v1/account/{accountId}
  ```

<details>
  <summary>Path</summary>
  
<br> 
  
  > <b>accountId</b> <i>number</i>
</details>

<details>
  <summary>Response</summary>
    
<br> 
  
  ```json
{
    "accountId": 1,
    "customerId": 123,
    "balances": [
        {
            "accountId": 1,
            "availableAmount": 0,
            "currency": "EUR"
        },
        {
            "accountId": 1,
            "availableAmount": 0,
            "currency": "SEK"
        },
        {
            "accountId": 1,
            "availableAmount": 0,
            "currency": "GBP"
        },
        {
            "accountId": 1,
            "availableAmount": 0,
            "currency": "USD"
        }
    ]
}
  ```
</details>

&nbsp;

#### [POST] - Create transaction

  ```
  /api/v1/transaction
  ```

<details>
  <summary>Body</summary>  
  
<br> 
  
  > <b>accountId</b> <i>number</i>
  > 
  > <b>amount</b> <i>string</i>
  > 
  > <b>currency</b> <i>string</i>
  > <sub><sup>* Accepted values: EUR | SEK | GBP | USD</sup></sub>
  >   
  > <b>direction</b> <i>string</i>
  > <sub><sup>* Accepted values: IN | OUT</sup></sub>
  >   
  > <b>description</b> <i>string</i>
  
  ```json
    {
      "accountId": 1,
      "amount": "100.00",
      "currency": "EUR",
      "direction": "IN",
      "description": "TEST"
    }
  ```
</details>

<details>
  <summary>Response</summary>
    
<br> 
  
  ```json
{
    "accountId": 1,
    "customerId": 123,
    "balances": [
        {
            "accountId": 1,
            "availableAmount": 100.00,
            "currency": "EUR"
        },
        {
            "accountId": 1,
            "availableAmount": 0,
            "currency": "SEK"
        },
        {
            "accountId": 1,
            "availableAmount": 0,
            "currency": "GBP"
        },
        {
            "accountId": 1,
            "availableAmount": 0,
            "currency": "USD"
        }
    ]
}
  ```
</details>

&nbsp;

#### [GET] - Find account transactions

  ```
  /api/v1/transaction/{accountId}/transactions
  ```

<details>
  <summary>Path</summary>
  
<br> 
  
  > <b>accountId</b> <i>number</i>

</details>

<details>
  <summary>Response</summary>
    
<br> 
  
  ```json
[
    {
        "accountId": 1,
        "transactionId": 1,
        "amount": 100.00,
        "currency": "EUR",
        "direction": "IN",
        "description": "TEST"
    },
    {
        "accountId": 1,
        "transactionId": 2,
        "amount": 10.00,
        "currency": "EUR",
        "direction": "OUT",
        "description": "TEST"
    },
    {
        "accountId": 1,
        "transactionId": 3,
        "amount": 100.00,
        "currency": "SEK",
        "direction": "IN",
        "description": "TEST"
    },
    {
        "accountId": 1,
        "transactionId": 4,
        "amount": 100.00,
        "currency": "GBP",
        "direction": "IN",
        "description": "TEST"
    },
    {
        "accountId": 1,
        "transactionId": 5,
        "amount": 100.00,
        "currency": "USD",
        "direction": "IN",
        "description": "TEST"
    }
]
  
```
  
</details>

&nbsp;

Considerations about the project
---
- Numbers were used for IDs, so they would be auto-generated and incremented
- Lombok was used to avoid boiler-plate code
- Classes generated by Lombok were removed from test coverage
- Database is preloaded with a few records for testing
- When a message is successfully sent to RabbitMQ, the message will be printed on console
- Unit tests were created using mocks

&nbsp;

RPS - Requests per second
---

> <i> RPS = (Total RAM / Task memory usage) * (1 / Task duration) </i>
> 
> RPS = (8 GB / 2 GB) * (1 / 0.6sec)
> 
> RPS = <b>6.66666</b> <i>requests per second</i>

&nbsp;

Scaling horizontally
---

- How many users will be sending requests?
- When is the busiest season for the application?
- Is the application hosted on the cloud?
- What's the cost of scaling horizontally x vertically?
- Is the application using internal cache?
