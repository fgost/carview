--liquibase formatted sql

--changeset fghost:8
CREATE TABLE IF NOT EXISTS profiles_access_groups (
    profile_id integer NOT NULL,
    access_group_id integer NOT NULL,
    FOREIGN KEY (profile_id) REFERENCES profiles (id),
    FOREIGN KEY (access_group_id) REFERENCES access_groups (id)
 );

ALTER TABLE IF EXISTS profiles_access_groups
    OWNER to "user-carview-api-java";

CREATE INDEX IF NOT EXISTS ix_profiles_access_groups_id
    ON profiles_access_groups USING btree
    (profile_id ASC NULLS LAST)
    TABLESPACE pg_default;