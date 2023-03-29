--liquibase formatted sql

--changeset fghost:6
CREATE TABLE IF NOT EXISTS access_groups (
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    code character varying(36) not null,
    name character varying(100) NOT NULL,
    CONSTRAINT access_groups_pk PRIMARY KEY(id),
    CONSTRAINT access_groups_uq_name UNIQUE (name)
 );

ALTER TABLE IF EXISTS access_groups
    OWNER to "user-carview-api-java";

CREATE INDEX IF NOT EXISTS ix_access_groups_id
    ON access_groups USING btree
    (id ASC NULLS LAST)
    TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS access_groups_permissions (
    access_group_id integer NOT NULL,
    permission_key character varying(100) NOT NULL,
    permission_value character varying(100) NOT NULL,
    CONSTRAINT fk_profile FOREIGN KEY(access_group_id) REFERENCES access_groups (id)
);