/** Archivo seed_data
Para realizar pruebas de inserción de datos en la tabla desde cero
insertar fichas con las relaciones 1:1
*/

-- seed_data.sql
USE tpi_db;

-- Insertar libros de prueba
INSERT INTO libro (titulo, autor, editorial, anioEdicion) VALUES
('Cien Años de Soledad','Gabriel García Márquez','Sudamericana',1967),
('El Aleph','Jorge Luis Borges','Sur',1949),
('Java: Cómo Programar','Deitel','Prentice Hall',2018);

INSERT INTO ficha_bibliografica (isbn, clasificacion_dewey, estanteria, idioma, a_id) VALUES
('978-84-376-0494-7','863.64','A1','es', 1),
('978-950-02-0001-8','861.64','A2','es', 2),
('978-0134670942','005.13','B1','en', 3);