CREATE TABLE IF NOT EXISTS client (
  	id SERIAL NOT NULL PRIMARY KEY,
  	address VARCHAR(45) NULL,
  	name VARCHAR(45) NULL,
  	phone_number VARCHAR(45) NULL,
  	mail VARCHAR(45) NULL
);

CREATE TABLE IF NOT EXISTS locker (
	id SERIAL NOT NULL PRIMARY KEY,
  	address VARCHAR(45) NULL,
  	latitude DOUBLE PRECISION NULL,
  	longitude DOUBLE PRECISION NULL,
	drawer_ip VARCHAR(100) NULL,
	camera_ip VARCHAR(100) NULL
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
	weight FLOAT NULL,
	dimension VARCHAR(45) NULL,
	estimated_date VARCHAR(45) NULL,
	address VARCHAR(45) NULL,
	pass_code VARCHAR(300) NULL,
	FOREIGN KEY (client_id) REFERENCES client (id),
	FOREIGN KEY (drawer_id) REFERENCES drawer (id),
	FOREIGN KEY (locker_id) REFERENCES locker (id)
);


INSERT INTO client (address, name, phone_number, mail) VALUES
('Rua A, 123', 'João Silva', '555-1234', 'joao@example.com'),
('Avenida B, 456', 'Maria Souza', '555-5678', 'maria@example.com');

INSERT INTO locker (address, latitude, longitude, drawer_ip, camera_ip) VALUES
('Prédio 1, Térreo', -23.5505199, -46.6333094, '192.168.0.1', '192.168.0.2'),
('Prédio 2, Subsolo', -23.5474484, -46.6351586, '192.168.0.3', '192.168.0.4');

INSERT INTO drawer (locker_id, dimension, available) VALUES
(1, '30x30x30 cm', true),
(1, '40x40x40 cm', true),
(1, '50x50x50 cm', true);

INSERT INTO product (client_id, drawer_id, locker_id, name, quantity, weight, dimension, estimated_date, address, pass_code) VALUES
(1, 1, 1, 'Livro', 1, 0.5, '20x20x10 cm', '2024-06-10', 'Rua A, 123', 'd7e30f51-b7be-4f2a-b140-15b22a50cd62'),
(1, 2, 1, 'Notebook', 1, 2.5, '40x30x5 cm', '2024-06-15', 'Rua A, 123', 'd7e30f51-b7be-4f2a-b140-15b22a50cd62'),
(2, 3, 2, 'Câmera', 1, 1.0, '15x10x10 cm', '2024-06-20', 'Avenida B, 456', 'd7e30f51-b7be-4f2a-b140-15b22a50cd62');