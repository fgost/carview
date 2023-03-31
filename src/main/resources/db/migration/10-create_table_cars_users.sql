--liquibase formatted sql

--changeset fghost:7
CREATE TABLE IF NOT EXISTS users_cars (
    user_id integer NOT NULL,
    cars_id integer NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (cars_id) REFERENCES cars (id)
 );

ALTER TABLE IF EXISTS users_cars
    OWNER to "user-carview-api-java";

CREATE INDEX IF NOT EXISTS ix_users_cars_id
    ON users_cars USING btree
    (user_id ASC NULLS LAST)
    TABLESPACE pg_default;