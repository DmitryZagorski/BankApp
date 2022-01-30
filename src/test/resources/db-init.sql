CREATE TABLE bank_accounts
(
    id              int NOT NULL AUTO_INCREMENT,
    currency_id     int NOT NULL,
    amount_of_money double DEFAULT NULL,
    bank_id         int NOT NULL,
    client_id       int NOT NULL,
    PRIMARY KEY (id),
    KEY bank_id (bank_id),
    KEY client_id (client_id),
    FOREIGN KEY (bank_id) REFERENCES banks (id),
    FOREIGN KEY (client_id) REFERENCES clients (id)
);

CREATE TABLE bank_clients
(
    id        int NOT NULL AUTO_INCREMENT,
    bank_id   int NOT NULL,
    client_id int NOT NULL,
    PRIMARY KEY (id),
    KEY bank_id (bank_id),
    KEY client_id (client_id),
    FOREIGN KEY (bank_id) REFERENCES banks (id),
    FOREIGN KEY (client_id) REFERENCES clients (id)
);

CREATE TABLE banks
(
    id                        int          NOT NULL AUTO_INCREMENT,
    bank_name                 varchar(255) NOT NULL,
    commission_for_individual double       NOT NULL,
    commission_for_entity     double       NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE client_status
(
    id          int          NOT NULL AUTO_INCREMENT,
    status_name varchar(155) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE clients
(
    id        int          NOT NULL AUTO_INCREMENT,
    name      varchar(255) NOT NULL,
    surname   varchar(255) NOT NULL,
    status_id int          NOT NULL,
    PRIMARY KEY (id),
    KEY status_id (status_id),
    FOREIGN KEY (status_id) REFERENCES client_status (id)
);

CREATE TABLE currency
(
    id            int          NOT NULL AUTO_INCREMENT,
    currency_name varchar(255) NOT NULL,
    rate          double       NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE transactions
(
    id                        int    NOT NULL AUTO_INCREMENT,
    client_id                 int    NOT NULL,
    sender_bank_account_id    int    NOT NULL,
    recipient_bank_account_id int    NOT NULL,
    currency_id               int    NOT NULL,
    amount_of_money           double NOT NULL,
    creation_date             date   NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (client_id) REFERENCES clients (id),
    FOREIGN KEY (sender_bank_account_id) REFERENCES bank_accounts (id),
    FOREIGN KEY (recipient_bank_account_id) REFERENCES bank_accounts (id),
    FOREIGN KEY (currency_id) REFERENCES currency (id)
);
