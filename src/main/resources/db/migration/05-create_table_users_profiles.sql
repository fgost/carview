--liquibase formatted sql

--changeset fghost:5
CREATE TABLE IF NOT EXISTS users_profiles(
    user_id integer NOT NULL,
    profile_id integer NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (profile_id) REFERENCES profiles (id)
 );

ALTER TABLE IF EXISTS users_roles
    OWNER to "user-carview-api-java";

CREATE INDEX IF NOT EXISTS ix_users_roles_id
    ON users USING btree
    (id ASC NULLS LAST)
    TABLESPACE pg_default;