CREATE DATABASE supermercado;
USE supermercado;

CREATE TABLE transacciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_caja INT NOT NULL,
    monto DOUBLE NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
