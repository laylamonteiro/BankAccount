CREATE TABLE accounts (
	accountId SERIAL,
	customerId BIGINT NOT NULL,
	country TEXT NOT NULL,
	PRIMARY KEY (accountId)
);


CREATE TABLE balances (
    accountId BIGINT NOT NULL,
    availableAmount DECIMAL NOT NULL,
    currency VARCHAR(3) NOT NULL,
    CONSTRAINT fk_account
        FOREIGN KEY(accountId)
            REFERENCES accounts(accountId)
);

CREATE TABLE transactions (
	transactionId SERIAL,
	accountId BIGINT NOT NULL,
	amount DECIMAL NOT NULL,
	currency VARCHAR(3) NOT NULL,
	direction VARCHAR(3) NOT NULL,
	description TEXT NOT NULL,
	PRIMARY KEY (transactionId),
    CONSTRAINT fk_account
        FOREIGN KEY(accountId)
            REFERENCES accounts(accountId)
);

INSERT INTO accounts(customerId, country) VALUES (123, 'Brazil');
INSERT INTO accounts(customerId, country) VALUES (456, 'Estonia');
INSERT INTO accounts(customerId, country) VALUES (321, 'Lithuania');
INSERT INTO accounts(customerId, country) VALUES (654, 'Sweden');
INSERT INTO accounts(customerId, country) VALUES (987, 'Slovakia');

INSERT INTO balances(accountId, availableAmount, currency) VALUES (1, 100, 'USD');
INSERT INTO balances(accountId, availableAmount, currency) VALUES (1, 100, 'EUR');
INSERT INTO balances(accountId, availableAmount, currency) VALUES (2, 100, 'GBP');
INSERT INTO balances(accountId, availableAmount, currency) VALUES (2, 100, 'EUR');
INSERT INTO balances(accountId, availableAmount, currency) VALUES (3, 100, 'GBP');
INSERT INTO balances(accountId, availableAmount, currency) VALUES (3, 100, 'USD');
INSERT INTO balances(accountId, availableAmount, currency) VALUES (4, 100, 'SEK');
INSERT INTO balances(accountId, availableAmount, currency) VALUES (4, 100, 'USD');
INSERT INTO balances(accountId, availableAmount, currency) VALUES (5, 100, 'GBP');
INSERT INTO balances(accountId, availableAmount, currency) VALUES (5, 100, 'EUR');

INSERT INTO transactions(accountId, amount, currency, direction, description)
VALUES (1, 3000, 'USD', 'IN', 'Salary');
INSERT INTO transactions(accountId, amount, currency, direction, description)
VALUES (1, 1000, 'EUR', 'IN', 'Investments');
INSERT INTO transactions(accountId, amount, currency, direction, description)
VALUES (1, 150, 'USD', 'OUT', 'Restaurants');
INSERT INTO transactions(accountId, amount, currency, direction, description)
VALUES (1, 300, 'EUR', 'OUT', 'Insurance');
INSERT INTO transactions(accountId, amount, currency, direction, description)
VALUES (2, 1000, 'GBP', 'IN', 'Salary');
INSERT INTO transactions(accountId, amount, currency, direction, description)
VALUES (2, 3000, 'EUR', 'IN', 'Investments');
INSERT INTO transactions(accountId, amount, currency, direction, description)
VALUES (2, 300, 'GBP', 'OUT', 'Electricity');
INSERT INTO transactions(accountId, amount, currency, direction, description)
VALUES (2, 150, 'EUR', 'OUT', 'Restaurants');
INSERT INTO transactions(accountId, amount, currency, direction, description)
VALUES (3, 2000, 'GBP', 'IN', 'Salary');
INSERT INTO transactions(accountId, amount, currency, direction, description)
VALUES (3, 1000, 'USD', 'IN', 'Investments');
INSERT INTO transactions(accountId, amount, currency, direction, description)
VALUES (3, 450, 'GBP', 'OUT', 'Restaurants');
INSERT INTO transactions(accountId, amount, currency, direction, description)
VALUES (3, 500, 'USD', 'OUT', 'Gas');
INSERT INTO transactions(accountId, amount, currency, direction, description)
VALUES (4, 3000, 'SEK', 'IN', 'Salary');
INSERT INTO transactions(accountId, amount, currency, direction, description)
VALUES (4, 2000, 'USD', 'IN', 'Investments');
INSERT INTO transactions(accountId, amount, currency, direction, description)
VALUES (4, 350, 'SEK', 'OUT', 'Clothing');
INSERT INTO transactions(accountId, amount, currency, direction, description)
VALUES (4, 250, 'USD', 'OUT', 'Insurance');
INSERT INTO transactions(accountId, amount, currency, direction, description)
VALUES (5, 4000, 'GBP', 'IN', 'Salary');
INSERT INTO transactions(accountId, amount, currency, direction, description)
VALUES (5, 2000, 'EUR', 'IN', 'Investments');
INSERT INTO transactions(accountId, amount, currency, direction, description)
VALUES (5, 100, 'GBP', 'OUT', 'Electricity');
INSERT INTO transactions(accountId, amount, currency, direction, description)
VALUES (5, 600, 'EUR', 'OUT', 'Transportation');

SELECT * FROM accounts;
SELECT * FROM balances;
SELECT * FROM transactions;