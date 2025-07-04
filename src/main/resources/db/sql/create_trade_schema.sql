CREATE TABLE IF NOT EXISTS trades (
    name varchar(50) NOT NULL UNIQUE PRIMARY KEY,
    counterparty varchar(50) NOT NULL,
    amount decimal NOT NULL,
    currency varchar(3) NOT NULL
);
