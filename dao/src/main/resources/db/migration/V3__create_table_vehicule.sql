CREATE TABLE vehicles (
    id                 BIGSERIAL    PRIMARY KEY,
    garage_id          BIGINT       NOT NULL,
    brand              VARCHAR(50)  NOT NULL,
    model              VARCHAR(50)  NOT NULL,
    annee_fabrication  INT          NOT NULL,
    fuel_type          VARCHAR(20)  NOT NULL,
    CONSTRAINT fk_vehicle_garage FOREIGN KEY (garage_id) REFERENCES garages(id) ON DELETE CASCADE
);