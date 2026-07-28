--liquibase formatted sql

--changeset faisal:1
CREATE TABLE party (
   party_id UUID PRIMARY KEY
);

--rollback DROP TABLE party;