CREATE TABLE accessories (
    id          BIGSERIAL       PRIMARY KEY,
    vehicle_id  BIGINT          NOT NULL,
    nom         VARCHAR(100)    NOT NULL,
    description TEXT            NOT NULL,
    prix        NUMERIC(10, 2)  NOT NULL,
    type        VARCHAR(50)     NOT NULL,
    CONSTRAINT fk_accessory_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicles(id) ON DELETE CASCADE
);