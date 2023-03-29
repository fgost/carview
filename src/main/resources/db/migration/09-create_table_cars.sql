--liquibase formatted sql

--changeset fghost:1
CREATE TABLE IF NOT EXISTS cars (
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    code character varying(36) not null,
    car_model character varying(100) NOT NULL,
    auto_maker character varying(100) NOT NULL,
    year character varying(100) NOT NULL,
    color character varying(100) NOT NULL,
    type smallint NOT NULL,
    CONSTRAINT pk_cars PRIMARY KEY(id)
);

COMMENT ON TABLE cars IS 'This table provide basic informations about the cars.';

COMMENT ON COLUMN cars.id IS 'Column responsible about informations about car`s ID';
COMMENT ON COLUMN cars.code IS 'Column responsible about informations about car`s code, or external ID';
COMMENT ON COLUMN cars.car_model IS 'Column responsible about model informations of car';
COMMENT ON COLUMN cars.auto_maker IS 'Column responsible about informations of maker of car';
COMMENT ON COLUMN cars.year IS 'Column responsible about informations of year of car';
COMMENT ON COLUMN cars.color IS 'Column responsible about informations of color of car';
COMMENT ON COLUMN cars.type IS 'Column responsible about set the type of car';

COMMENT ON CONSTRAINT pk_cars ON cars IS 'Constraint responsible to guarantee the unike information on primary key of car registry';

ALTER TABLE IF EXISTS cars
    OWNER to "user-carview-api-java";

CREATE INDEX IF NOT EXISTS ix_cars_id
    ON cars USING btree
    (id ASC NULLS LAST)
    TABLESPACE pg_default;