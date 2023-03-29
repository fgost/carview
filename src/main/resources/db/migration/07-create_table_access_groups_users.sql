--liquibase formatted sql

--changeset fghost:7
CREATE TABLE IF NOT EXISTS access_groups_users (
    user_id integer NOT NULL,
    access_group_id integer NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (access_group_id) REFERENCES access_groups (id)
 );

ALTER TABLE IF EXISTS access_groups_users
    OWNER to "user-carview-api-java";

CREATE INDEX IF NOT EXISTS ix_access_groups_users_id
    ON access_groups_users USING btree
    (user_id ASC NULLS LAST)
    TABLESPACE pg_default;