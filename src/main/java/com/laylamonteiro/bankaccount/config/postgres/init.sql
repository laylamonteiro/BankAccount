-- SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'bankaccountdb';

-- CREATE USER postgres;
--
-- CREATE DATABASE bankaccountdb;
ALTER USER postgres WITH PASSWORD 'postgres';

CREATE TYPE currency AS ENUM ('EUR', 'SEK', 'GBP', 'USD');

CREATE TABLE accounts (
	accountId BIGINT NOT NULL,
	customerId BIGINT NOT NULL,
	country TEXT NOT NULL,
	balances BIGINT NOT NULL,
	transactions BIGINT,
	PRIMARY KEY (accountId)
);


CREATE TABLE balances (
    accountId BIGINT NOT NULL,
    availableAmount DECIMAL NOT NULL,
    currency currency NOT NULL
);

INSERT INTO Balances(accountId, availableAmount, currency) VALUES (123, 0, 'USD');
INSERT INTO Accounts(customerId, accountId, country, balances) VALUES (1, 123, 'Brazil', 1);

CREATE TABLE transactions (
	transactionId BIGINT NOT NULL,
	accountId BIGINT NOT NULL,
	amount DECIMAL NOT NULL,
	currency VARCHAR(3) NOT NULL,
	direction VARCHAR(3) NOT NULL,
	description TEXT,
	PRIMARY KEY (transactionId)
);

GRANT ALL PRIVILEGES ON DATABASE bankaccountdb TO postgres;

SELECT * FROM accounts;