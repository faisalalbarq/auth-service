-- liquibase formatted sql
-- changeset faisal:03-create-user-identity-type-table

CREATE TABLE user_identity_type
(
    user_identity_type_id   SERIAL PRIMARY KEY,
    user_identity_type_name VARCHAR(100)
);