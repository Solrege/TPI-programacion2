-- Consigna 3 tpi Programación II
-- Archivo create_db

DROP DATABASE IF EXISTS tpi_bd;
CREATE DATABASE tpi_bd CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE tpi_bd;

-- Tabla libro 
CREATE TABLE libro (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  eliminado BOOLEAN NOT NULL DEFAULT FALSE,
  titulo VARCHAR(150) NOT NULL,
  autor VARCHAR(120) NOT NULL,
  editorial VARCHAR(100),
  anioEdicion INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY uq_libro_titulo_autor (titulo, autor) -- opcional
);

-- Tabla fichabibliografica 
CREATE TABLE ficha_bibliografica (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  eliminado BOOLEAN NOT NULL DEFAULT FALSE,
  isbn VARCHAR(17),
  clasificacion_dewey VARCHAR(20),
  estanteria VARCHAR(20),
  idioma VARCHAR(30),
  a_id BIGINT NOT NULL,
  CONSTRAINT uq_ficha_aid UNIQUE (a_id),
  CONSTRAINT uq_ficha_isbn UNIQUE (isbn),
  CONSTRAINT fk_ficha_libro FOREIGN KEY (a_id)
    REFERENCES libro(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

--  ÍNDICES 

SHOW tables;

INSERT INTO libro (titulo, autor, editorial, anioEdicion) VALUES
('Cien Años de Soledad','Gabriel García Márquez','Sudamericana',1967),
('El Aleph','Jorge Luis Borges','Sur',1949),
('Java: Cómo Programar','Deitel','Prentice Hall',2018);

INSERT INTO ficha_bibliografica (isbn, clasificacion_dewey, estanteria, idioma, a_id) VALUES
('978-84-376-0494-7','863.64','A1','es', 1),
('978-950-02-0001-8','861.64','A2','es', 2),
('978-0134670942','005.13','B1','en', 3);