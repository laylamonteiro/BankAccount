ALTER USER postgres WITH PASSWORD 'postgres';

CREATE TYPE currency AS ENUM ('EUR', 'SEK', 'GBP', 'USD');

CREATE TABLE accounts (
	accountId SERIAL,
	customerId BIGINT NOT NULL,
	country TEXT NOT NULL,
	PRIMARY KEY (accountId)
);


CREATE TABLE balances (
    accountId BIGINT NOT NULL,
    availableAmount DECIMAL NOT NULL,
    currency currency NOT NULL,
    CONSTRAINT fk_account
        FOREIGN KEY(accountId)
            REFERENCES accounts(accountId)
);

CREATE TABLE transactions (
	transactionId SERIAL,
	accountId BIGINT NOT NULL,
	amount DECIMAL NOT NULL,
	currency currency NOT NULL,
	direction VARCHAR(3) NOT NULL,
	description TEXT,
	PRIMARY KEY (transactionId),
    CONSTRAINT fk_account
        FOREIGN KEY(accountId)
            REFERENCES accounts(accountId)
);

INSERT INTO accounts(customerId, country) VALUES (123, 'Brazil');
INSERT INTO balances(accountId, availableAmount, currency) VALUES (1, 0, 'USD');
INSERT INTO transactions(accountId, amount, currency, direction, description)
VALUES (1, 2000, 'USD', 'IN', 'Salary');

GRANT ALL PRIVILEGES ON DATABASE bankaccountdb TO postgres;

SELECT * FROM accounts;
SELECT * FROM balances;
SELECT * FROM transactions;