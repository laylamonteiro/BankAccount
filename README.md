## TuumBankAccount

Implementation of a small core banking solution:
  • Creates accounts to keep track of current accounts, balances, and transaction history
  • Capable of publishing messages into RabbitMQ for other consumers
  

Start the project
---
On folder src/main/java/com/laylamonteiro/bankaccount/config/container

  Start containers:
  - docker-compose up -d

Remove the project
---
On folder src/main/java/com/laylamonteiro/bankaccount/config/container

  Stop containers and remove volumes:
  - docker-compose down -v

Tests
---
On folder src/main/java/com/laylamonteiro/bankaccount/config/container

  Generate test coverage reports:
    - ./gradlew clean build
    - ./gradlew jacocoTestReport

  Test results:
  - build/reports/jacoco/test/html/index.html
  - On folder src/main/java/com/laylamonteiro/bankaccount/config/container

API Contract
---
