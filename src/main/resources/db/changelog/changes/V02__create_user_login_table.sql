-- liquibase formatted sql
-- changeset faisal:02-create-user-login-table

CREATE TABLE user_login
(
    user_login_id UUID PRIMARY KEY,
    party_id      UUID,
    password      VARCHAR(255),
    birth_date    DATE,
    CONSTRAINT fk_user_login_party FOREIGN KEY (party_id) REFERENCES party (party_id)
);