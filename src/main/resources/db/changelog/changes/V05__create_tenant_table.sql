-- liquibase formatted sql
-- changeset faisal:05-create-tenant-table

CREATE TABLE tenant
(
    tenant_id    UUID PRIMARY KEY,
    party_id     UUID,
    tenant_name  VARCHAR(255),
    phone_number VARCHAR(50),
    email        VARCHAR(255),
    CONSTRAINT fk_tenant_party FOREIGN KEY (party_id) REFERENCES party (party_id)
);