--liquibase formatted sql

--changeset fghost:1
CREATE TABLE IF NOT EXISTS users (
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    code character varying(36) not null,
    name character varying(100) NOT NULL,
    last_name character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    password character varying(100) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY(id),
    CONSTRAINT users_uq_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS users_preferences (
    user_id integer NOT NULL,
    preference_key character varying(100) NOT NULL,
    preference_value character varying(100) NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users (id)
 );

 CREATE TABLE IF NOT EXISTS user_photo (
     user_id integer NOT NULL,
     file_name character varying(100) NOT NULL,
     url character varying(200) NOT NULL,
     size INTEGER DEFAULT 0 NOT NULL,
     content_type character varying(100) NOT NULL,
     CONSTRAINT user_photo_pk_user PRIMARY KEY(user_id),
     CONSTRAINT user_photo_fk_user FOREIGN KEY (user_id) REFERENCES users (id)
 );

ALTER TABLE IF EXISTS users
    OWNER to "user-carview-api-java";

CREATE INDEX IF NOT EXISTS ix_users_id
    ON users USING btree
    (id ASC NULLS LAST)
    TABLESPACE pg_default;