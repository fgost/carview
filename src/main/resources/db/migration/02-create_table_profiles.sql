 --liquibase formatted sql

--changeset fghost:2
CREATE TABLE IF NOT EXISTS profiles (
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    code character varying(36) NOT NULL,
    name character varying(100) NOT NULL,
    CONSTRAINT pk_profiles PRIMARY KEY(id),
    CONSTRAINT profiles_uq_name UNIQUE (name)
 );

ALTER TABLE IF EXISTS profiles
    OWNER to "user-carview-api-java";

CREATE INDEX IF NOT EXISTS ix_profiles_id
    ON users USING btree
    (id ASC NULLS LAST)
    TABLESPACE pg_default;
