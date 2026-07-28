--liquibase formatted sql

--changeset faisal:4
CREATE TABLE user_identity (
   user_identity_id UUID PRIMARY KEY,
   user_login_id UUID,
   user_identity_type_id INT,
   user_identity_value VARCHAR(255),
   CONSTRAINT fk_user_identity_login FOREIGN KEY (user_login_id) REFERENCES user_login(user_login_id)
);

--rollback DROP TABLE user_identity;