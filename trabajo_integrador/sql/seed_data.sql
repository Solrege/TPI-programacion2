/** Archivo seed_data
Para realizar pruebas de inserción de datos en la tabla desde cero
insertar fichas con las relaciones 1:1
*/

-- seed_data.sql

USE tpi_bd;

-- Insertar fichas bibliográficas
INSERT INTO fichaBibliografica (isbn, clasificadoDewey, estanteria, idioma) VALUES
    ('978-84-376-0494-7','863.64','A1','es'),
    ('978-950-02-0001-8','861.64','A2','es'),
    ('978-0134670942','005.13','B1','en');

-- Insertar libros con referencia a la ficha (1:1)
INSERT INTO libro (titulo, autor, editorial, anioEdicion, id_ficha) VALUES
    ('Cien Años de Soledad','Gabriel García Márquez','Sudamericana',1967, 1),
    ('El Aleph','Jorge Luis Borges','Sur',1949, 2),
    ('Java: Cómo Programar','Deitel','Prentice Hall',2018, 3);
