-- create table address
CREATE TABLE IF NOT EXISTS address
(
    id      BIGSERIAL PRIMARY KEY,
    country VARCHAR(100) NOT NULL,
    city    VARCHAR(100) NOT NULL,
    street  VARCHAR(255) NOT NULL
);

-- create table image
CREATE TABLE IF NOT EXISTS image
(
    id    UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    image BYTEA NOT NULL
);

-- create table supplier
CREATE TABLE IF NOT EXISTS supplier
(
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    address_id   BIGINT REFERENCES address (id),
    phone_number VARCHAR(20)
);

-- create table product
CREATE TABLE IF NOT EXISTS product
(
    id               BIGSERIAL PRIMARY KEY,
    name             VARCHAR(255)   NOT NULL,
    category         VARCHAR(100)   NOT NULL,
    price            DECIMAL(10, 2) NOT NULL,
    available_stock  INT            NOT NULL DEFAULT 0,
    last_update_date DATE                    DEFAULT CURRENT_DATE,
    supplier_id      BIGINT REFERENCES supplier (id),
    image_id         UUID           REFERENCES image (id) ON DELETE SET NULL
);

-- create table client
CREATE TABLE IF NOT EXISTS client
(
    id                BIGSERIAL PRIMARY KEY,
    client_name       VARCHAR(255) NOT NULL,
    client_surname    VARCHAR(255) NOT NULL,
    birthday          DATE         NOT NULL,
    gender            CHAR(1)      NOT NULL CHECK (gender IN ('M', 'F')),
    registration_date DATE         NOT NULL DEFAULT CURRENT_DATE,
    address_id        BIGINT REFERENCES address (id)
)