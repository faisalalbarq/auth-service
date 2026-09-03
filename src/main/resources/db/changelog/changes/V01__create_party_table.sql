-- liquibase formatted sql
-- changeset faisal:01-create-party-table

CREATE TABLE party
(
    party_id   UUID PRIMARY KEY,
    party_name VARCHAR(255)
);