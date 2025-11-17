-- Consigna 3 tpi Programación II
-- Archivo create_db

DROP DATABASE IF EXISTS tpi_bd;
CREATE DATABASE tpi_bd CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE tpi_bd;

-- Tabla fichaBibliografica (se crea primero porque libro depende de ella)
CREATE TABLE ficha_bibliografica (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    eliminado BOOLEAN NOT NULL DEFAULT FALSE,
    isbn VARCHAR(17) UNIQUE,
    clasificacion_dewey VARCHAR(20),
    estanteria VARCHAR(20),
    idioma VARCHAR(30)
);

-- Tabla libro
CREATE TABLE libro (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   eliminado BOOLEAN NOT NULL DEFAULT FALSE,
   titulo VARCHAR(150) NOT NULL,
   autor VARCHAR(120) NOT NULL,
   editorial VARCHAR(100),
   anio_edicion INT,
   id_ficha BIGINT UNIQUE, -- relación 1:1
   CONSTRAINT fk_libro_ficha FOREIGN KEY (id_ficha)
       REFERENCES fichaBibliografica(id)
       ON DELETE SET NULL
       ON UPDATE CASCADE
);