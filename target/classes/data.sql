DROP DATABASE IF EXISTS managementDB;
CREATE DATABASE managementDB;
USE managementDB;
CREATE TABLE vehicles (
    id INT NOT NULL AUTO_INCREMENT,
    brand VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL,
	model VARCHAR(255) NOT NULL,
    plate VARCHAR(7) UNIQUE NOT NULL,
    year INT NOT NULL,
    is_reserved BOOLEAN NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE reservations (
    id INT NOT NULL AUTO_INCREMENT,
    vehicle_id INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id)
);

INSERT INTO vehicles (brand, color, model,  plate, year, is_reserved) VALUES 
('Volkswagen', 'Branca', 'Gol', 'ABC1234', 2020, false),
('Hyundai', 'Preto', 'HB20', 'CDE1234', 2021, false),
('Hyundai', 'Preto', 'HB20', 'CDE1235', 2021, false),
('Hyundai', 'Preto', 'HB20', 'CDE1254', 2021, false);

