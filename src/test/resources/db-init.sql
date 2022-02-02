CREATE TABLE banks
(
    id                        int          NOT NULL AUTO_INCREMENT,
    bank_name                 varchar(255) NOT NULL,
    commission_for_individual double       NOT NULL,
    commission_for_entity     double       NOT NULL,
    PRIMARY KEY (id)
);
