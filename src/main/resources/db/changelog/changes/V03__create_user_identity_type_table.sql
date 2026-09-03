-- liquibase formatted sql
-- changeset faisal:03-create-user-identity-type-table

CREATE TABLE user_identity_type
(
    user_identity_type_id   SERIAL PRIMARY KEY,
    user_identity_type_name VARCHAR(100)
);

INSERT INTO user_identity_type (user_identity_type_id, user_identity_type_name)
VALUES (1, 'Email'),
       (2, 'Phone Number');