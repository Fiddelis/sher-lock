CREATE TABLE IF NOT EXISTS client (
  	id SERIAL NOT NULL PRIMARY KEY,
  	address VARCHAR(45) NULL,
  	name VARCHAR(45) NULL,
  	phone_number VARCHAR(45) NULL,
  	mail VARCHAR(45) NULL
);

CREATE TABLE IF NOT EXISTS locker (
	id SERIAL NOT NULL PRIMARY KEY,
  	address VARCHAR(100) NULL,
  	latitude DOUBLE PRECISION NULL,
  	longitude DOUBLE PRECISION NULL
);

CREATE TABLE IF NOT EXISTS drawer (
 	id SERIAL NOT NULL PRIMARY KEY,
  	locker_id INT NOT NULL,
  	dimension VARCHAR(45) NULL,
  	available BOOLEAN NULL,
  	FOREIGN KEY (locker_id) REFERENCES locker (id)
);

CREATE TABLE IF NOT EXISTS product (
  	id SERIAL NOT NULL PRIMARY KEY,
  	client_id INT NOT NULL,
	drawer_id INT NOT NULL,
	locker_id INT NOT NULL,
	name VARCHAR(100) NULL,
	quantity FLOAT NULL,
	dimension VARCHAR(45) NULL,
	estimated_date VARCHAR(45) NULL,
	address VARCHAR(100) NULL,
	pass_code VARCHAR(300) NULL,
	FOREIGN KEY (client_id) REFERENCES client (id),
	FOREIGN KEY (drawer_id) REFERENCES drawer (id),
	FOREIGN KEY (locker_id) REFERENCES locker (id)
);

INSERT INTO locker (address, latitude, longitude) VALUES
('Instituto Nacional de Telecomunicações - Inatel', -22.257218, -45.696542);

INSERT INTO drawer (locker_id, dimension, available) VALUES
(1, '09x30x30 cm', true),
(1, '16x40x30 cm', true);