---- Criando a tabela dos clientes ----
CREATE TABLE IF NOT EXISTS customers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150),
    email VARCHAR(30) UNIQUE NOT NULL,
    address VARCHAR(150),
    phone VARCHAR(11)
);
-- Povoando a tabela dos clientes
INSERT INTO customers (name, email, address, phone) VALUES ("Jackson", "jack@gmail.com", "Rua Argelino Melo, Centro, N180", "31988909090");
INSERT INTO customers (name, email, address, phone) VALUES ("Jonas", "jon@gmail.com", "Rua dos oradores, Centro, N44", "71988909090");
INSERT INTO customers (name, email, address, phone) VALUES ("Amanda", "ama@yahoo.com", "Alameida yaya, Centro, N10", "56988909090");
INSERT INTO customers (name, email, address, phone) VALUES ("Bruna", "bruninha@soft.com", "Avenida Dutra, Centro, N80", "88988909090");
INSERT INTO customers (name, email, address, phone) VALUES ("Breno", "brenoSS@soft.com", "Rua Eng. Filho, Centro, N11", "63988909090");
INSERT INTO customers (name, email, address, phone) VALUES ("Luirton", "luirton464@gmail.com", "Alameida Cabral, Centro, N233", "44988909090");
INSERT INTO customers (name, email, address, phone) VALUES ("William", "willSantos@outlook.com", "Rua dos palmares, Centro, N2016", "21988909090");
INSERT INTO customers (name, email, address, phone) VALUES ("Vanessa", "vanepc14@bus.com", "Avenida Presidente, Centro, N305", "11988909090");

---- Criando a tabela dos produtos ----
CREATE TABLE IF NOT EXISTS product_entityjpa (
    product_id BIGSERIAL NOT NULL PRIMARY KEY,
    product_name VARCHAR(255),
    product_price REAL
);

-- Povoando a tabela dos produtos
INSERT INTO product_entityjpa (product_name, product_price) VALUES ("Ovos duz.", 12);
INSERT INTO product_entityjpa (product_name, product_price) VALUES ("Arroz Quero+", 7.44);
INSERT INTO product_entityjpa (product_name, product_price) VALUES ("Feijão Quero+", 9);
INSERT INTO product_entityjpa (product_name, product_price) VALUES ("Manteiga Fazenda", 11.49);
INSERT INTO product_entityjpa (product_name, product_price) VALUES ("Desinfetante 700ml", 8.69);

/*
psql
\c shoppingcart;


*/