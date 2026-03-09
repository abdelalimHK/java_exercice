CREATE TABLE garages (
    id        BIGSERIAL PRIMARY KEY,
    name      VARCHAR(100) NOT NULL,
    address   VARCHAR(255) NOT NULL,
    telephone VARCHAR(20)  NOT NULL,
    email     VARCHAR(150) NOT NULL
);

CREATE TABLE garage_opening_times (
    id           BIGSERIAL PRIMARY KEY,
    garage_id    BIGINT      NOT NULL,
    day_of_week  VARCHAR(20) NOT NULL,
    start_time   TIME        NOT NULL,
    end_time     TIME        NOT NULL,
    CONSTRAINT fk_garage FOREIGN KEY (garage_id) REFERENCES garages(id) ON DELETE CASCADE
);