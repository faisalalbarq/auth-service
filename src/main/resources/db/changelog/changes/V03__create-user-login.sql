--liquibase formatted sql

--changeset faisal:3
CREATE TABLE user_login (
   user_login_id UUID PRIMARY KEY,
   party_id UUID,
   password VARCHAR(255),
   CONSTRAINT fk_user_login_party FOREIGN KEY (party_id) REFERENCES party(party_id)
);

--rollback DROP TABLE user_login;