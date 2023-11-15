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
    PRIMARY KEY (id)
);
INSERT INTO vehicles (brand, color, model,  plate, year) VALUES 
('Volkswagen', 'Branca', 'Gol', 'ABC1234', 2020),
('Hyundai', 'Preto', 'HB20', 'CDE1234', 2021),
('Volkswagen', 'Cinza', 'Polo', 'EFG1234', 2022);