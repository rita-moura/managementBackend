CREATE DATABASE managementDB;
USE managementDB;
CREATE TABLE vehicles (
    id INT NOT NULL AUTO_INCREMENT,
    brand VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL,
	model VARCHAR(255) NOT NULL,
    plate VARCHAR(7) UNIQUE NOT NULL,
    year YEAR NOT NULL,
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
('Volkswagen', 'Branco', 'Gol', 'ABC1234', 2020, false),
('Hyundai', 'Preto', 'HB20', 'CDE1234', 2021, false),
('Volkswagen', 'Prata', 'Fox', 'KOL8907', 2023, false),
('Renault', 'Prata', 'Reanault Kwid', 'NGH1235', 2022, false),
('Citroën', 'Branco', 'C3', 'DFG5768', 2021, false),
('Fiat', 'Preto', 'Argo', 'VGH3647', 2022, false),
('Sandero', 'Preto', 'Stepway', 'BHJ2345', 2019, false),
('Chevrolet', 'Preto', 'Onix', 'CFG4567', 2020, false);
