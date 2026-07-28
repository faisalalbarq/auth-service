--liquibase formatted sql

--changeset faisal:2
CREATE TABLE employees (
   employee_id UUID PRIMARY KEY,
   first_name VARCHAR(255),
   last_name VARCHAR(255),
   email VARCHAR(255)
);

--rollback DROP TABLE employees;