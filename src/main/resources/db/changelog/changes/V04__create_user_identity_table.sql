-- liquibase formatted sql
-- changeset faisal:04-create-user-identity-table

CREATE TABLE user_identity
(
    user_identity_id      UUID PRIMARY KEY,
    user_identity_type_id INT,
    user_login_id         UUID,
    user_identity_value   VARCHAR(255),
    CONSTRAINT fk_user_identity_type FOREIGN KEY (user_identity_type_id) REFERENCES user_identity_type (user_identity_type_id),
    CONSTRAINT fk_user_identity_user_login FOREIGN KEY (user_login_id) REFERENCES user_login (user_login_id)
);