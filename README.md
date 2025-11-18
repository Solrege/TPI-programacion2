# Trabajo Final Integrador de programación 



▪ Descripción del dominio elegido.

El proyecto modela un sistema de gestión para una Biblioteca, basándose en los siguientes modelos de relación:

\- Foco principal: Relación 1 -> 1 unidireccional entre Libro y FichaBibliografica. Cada libro tiene una ficha única que detalla su información técnica.

\- Entidades de apoyo: 



▪ Requisitos (Java/BD) y pasos para crear la base con el .sql

provisto.



Base de Datos:



MySQL 8.0 o superior

DBeaver Community (o cualquier cliente SQL compatible con MySQL)



Java:



JDK 11 o superior

Driver JDBC de MySQL (mysql-connector-java) versión 8.4

---------------------------------------------------------------------

Estructura del proyecto:



/src/main/java/

│

├─ trabajo\_integrador.config/

│     └─ DatabaseConnection.java

│

├─ trabajo\_integrador.dao/

│     ├─ GenericDAO.java

│     ├─ LibroDAO.java

│     └─ FichaBibliograficaDAO.java

│

├─ trabajo\_integrador.service/

│     ├─ GenericService.java

│     ├─ LibroService.java

│     └─ FichaBibliograficaService.java

│

├─ trabajo\_integrador.models/

│     ├─ Base.java

│     ├─ Libro.java

│     ├─ FichaBibliografica.java

│     └─ Idioma.java

│

└─ trabajo\_integrador.main/

&nbsp;     ├─ Principal.java

&nbsp;     ├─ AppMenu.java

&nbsp;     ├─ MenuHandler.java

&nbsp;     ├─ MenuDisplay.java

&nbsp;     └─ TestConnection.java



/sql/

│   ├─ create\_db.sql

│   └─ seed\_data.sql



README.md



-----------------------------------------------------------------------



Pasos para la carga de datos:



La carga de datos se realiza ejecutando dos archivos SQL en el orden especificado , primero el create\_db.sql, y luego la inserción de datos desde seed\_data.sql

A continuación, explicaremos el proceso utilizando MySQL Workbench como herramienta de gestión de base de datos.

1\. Ejecutar el script de creación de tablas



Abrir MySQL y asegurarse de conectar la base de datos

Abrir el archivo \[**create\_db.sql**] ubicado en el siguiente repositorio: (https://github.com/Solrege/TPI-programacion2/blob/main/trabajo\_integrador/sql/create\_db.sql)

Ejecuta el script completo (Ctrl+Enter o botón "Execute SQL Script" con forma de rayo)

Este script creará la base de datos y todas las tablas necesarias con sus respectivas relaciones:



DROP DATABASE IF EXISTS tpi\_bd;

CREATE DATABASE tpi\_bd CHARACTER SET utf8mb4 COLLATE utf8mb4\_unicode\_ci;

USE tpi\_bd;



-- Tabla fichaBibliografica (se crea primero porque libro depende de ella)

CREATE TABLE ficha\_bibliografica (

&nbsp;   id BIGINT AUTO\_INCREMENT PRIMARY KEY,

&nbsp;   eliminado BOOLEAN NOT NULL DEFAULT FALSE,

&nbsp;   isbn VARCHAR(17) UNIQUE,

&nbsp;   clasificacion\_dewey VARCHAR(20),

&nbsp;   estanteria VARCHAR(20),

&nbsp;   idioma VARCHAR(30)

);



-- Tabla libro

CREATE TABLE libro (

&nbsp;  id\_libro BIGINT AUTO\_INCREMENT PRIMARY KEY,

&nbsp;  eliminado BOOLEAN NOT NULL DEFAULT FALSE,

&nbsp;  titulo VARCHAR(150) NOT NULL,

&nbsp;  autor VARCHAR(120) NOT NULL,

&nbsp;  editorial VARCHAR(100),

&nbsp;  anio\_edicion INT,

&nbsp;  id\_ficha BIGINT UNIQUE, -- relación 1:1

&nbsp;  CONSTRAINT fk\_libro\_ficha FOREIGN KEY (id\_ficha)

&nbsp;      REFERENCES ficha\_bibliografica(id)

&nbsp;      ON DELETE SET NULL

&nbsp;      ON UPDATE CASCADE

);





2\. Ejecutar el script de carga de datos



Una vez creadas las tablas, abrir el archivo \[**seed\_data.sql]** (https://github.com/Solrege/TPI-programacion2/blob/main/trabajo\_integrador/sql/seed\_data.sql)

Y ejecutar el script completo.

Este script lo que hace es insertar todos los datos iniciales en las tablas previamente creadas, en nuestro caso 3 libros y 3 fichas bibliográficas.



USE tpi\_bd;



-- Insertar fichas bibliográficas

INSERT INTO ficha\_bibliografica (isbn, clasificacion\_Dewey, estanteria, idioma) VALUES

&nbsp;   ('978-84-376-0494-7','863.64','A1','es'),

&nbsp;   ('978-950-02-0001-8','861.64','A2','es'),

&nbsp;   ('978-0134670942','005.13','B1','en');



-- Insertar libros con referencia a la ficha (1:1)

INSERT INTO libro (titulo, autor, editorial, anio\_edicion, id\_ficha) VALUES

&nbsp;   ('Cien Años de Soledad','Gabriel García Márquez','Sudamericana',1967, 1),

&nbsp;   ('El Aleph','Jorge Luis Borges','Sur',1949, 2),

&nbsp;   ('Java: Cómo Programar','Deitel','Prentice Hall',2018, 3);

&nbsp;   

&nbsp;   SELECT COUNT(\*) FROM ficha\_bibliografica; 



3\. Verificar la carga

Para verificar que los datos se cargaron correctamente podemos ejecutar el siguiente comando:

SELECT COUNT(\*) FROM nombre\_tabla;

por ejemplo SELECT COUNT(\*) FROM ficha\_bibliografica;





-------------------------------------------------------------------------------

Configuración de la conexión (DatabaseConnection)

--------------------------------------------------------------------------------



Para configurar la conexión con la base de datos, es necesario abrir el archivo clase \[DatabaseConnection.java] ubicado en (https://github.com/Solrege/TPI-programacion2/blob/main/trabajo\_integrador/src/trabajo\_integrador/config/DatabaseConnection.java), lo ideal es realizarlo desde un IDE como NetBeans, cargar el proyecto

y luego abrir el paquete config en donde se encuentra ubicado el archivo. Se debe tener en cuenta que establece la conexión entre el sistema y la base cargando también previamente

la versión del Driver de JDBC de MySQL, éste se carga desde Libraries --> Add Jar/Folder --> mysql-connector-j-8.4.0.jar

Al ejecutarl el código, valida que la url sea la de por defecto "localhost:3306", el usuario y la contraseña sean correctos, dependiendo en qué computadora se ejecutan, sino modificarlos.



\*\*\*\*package trabajo\_integrador.config;



import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;



public class DatabaseConnection {



&nbsp;   private static final String URL = "jdbc:mysql://localhost:3308/tpi\_bd";

&nbsp;   private static final String USER = "root";

&nbsp;   private static final String PASSWORD = "test";



&nbsp;   static {

&nbsp;       try {

&nbsp;           Class.forName("com.mysql.cj.jdbc.Driver");

&nbsp;       } catch (ClassNotFoundException e) {

&nbsp;           throw new RuntimeException("Error: No se encontró el driver JDBC.", e);

&nbsp;       }

&nbsp;   }



&nbsp;   public static Connection getConnection() throws SQLException {

&nbsp;       if (URL == null || URL.isEmpty() || USER == null || USER.isEmpty() || PASSWORD == null || PASSWORD.isEmpty()) {

&nbsp;           throw new SQLException("Configuración de la base de datos incompleta o inválida.");

&nbsp;       }

&nbsp;       return DriverManager.getConnection(URL, USER, PASSWORD);

&nbsp;   }

}



\*\*\*\*\*

El objetivo es que cualquier DAO pueda obtener una conexión simplemente llamando: Connection conn = DatabaseConnection.getConnection();





------------------------------------------------------------------------------------

Probar la conexión (TestConnection)

------------------------------------------------------------------------------------



Con éste archivo que se ubica en el main\[TestConnection.java],ubicado en el repositorio (https://github.com/Solrege/TPI-programacion2/blob/main/trabajo\_integrador/src/trabajo\_integrador/main/TestConnection.java) 

se debe ejecutar y si muestra el mensaje ¡Conexión a la BBDD exitosa!, se estableció la conexión, de lo contrario devuelve Error en la conexión.



package trabajo\_integrador.main;



import java.sql.Connection;

import java.sql.SQLException;

import trabajo\_integrador.config.DatabaseConnection;





public class TestConnection {

&nbsp;   

&nbsp;   public static void main(String\[] args) {

&nbsp;       System.out.println("Intentando conectar a la base de datos...");

&nbsp;       

&nbsp;       try (Connection conn = DatabaseConnection.getConnection()) {

&nbsp;           

&nbsp;           if (conn != null) {

&nbsp;               System.out.println("¡Conexión a la BBDD exitosa!");

&nbsp;               System.out.println("AutoCommit: " + conn.getAutoCommit());

&nbsp;           }

&nbsp;           

&nbsp;       } catch (SQLException e) {

&nbsp;           System.out.println(" Error en la conexión");

&nbsp;       }

&nbsp;   }

}





--------------------------------------------------------------------------------------------------------------

Ejecutar el Sistema Completo

--------------------------------------------------------------------------------------------------------------

Abrir el proyecto en tu IDE (IntelliJ IDEA, NetBeans, Eclipse), nosotras utilizamos NetBeans

Hay que asegurarse de tener el driver JDBC de MySQL en el classpath del proyecto





El archivo principal es \[Principal.Java] ubicado en el main. Es el punto de entrada del sistema y el lo primero que se eje

Su funcion es inicializar objetos básicos (Libro y ficha\_bibliografica), mostrar un ejemplo simple por consola e iniciar un menú interactivo del sistema.

Pasos a seguir:



1\. Ejecutar `create\_db.sql`  

2\. Ejecutar `seed\_data.sql`  

3\. Configurar DatabaseConnection.java  

4\. Ejecutar `Principal.java`  

5\. Navegar con el menú por consola  



Funcionalidades del Sistema



\- Crear libros  

\- Crear fichas bibliográficas  

\- Relacionar fichas con libros (1→1)  

\- Listar  

\- Actualizar  

\- Baja lógica  

\- Integración DAO + Service 





Ejemplo de Menú:
=====================================

&nbsp;        SISTEMA BIBLIOTECARIO       

=====================================

&nbsp;              MEN PRINCIPAL        

-------------------------------------

&nbsp;1. Crear Libro

&nbsp;2. Listar Libros

&nbsp;3. Actualizar Libro

&nbsp;4. Eliminar Libro

-------------------------------------

&nbsp;5. Crear Ficha Bibliogrfica

&nbsp;6. Listar Fichas

&nbsp;7. Actualizar Ficha Bibliogrfica

&nbsp;8. Eliminar Ficha Bibliogrfica

-------------------------------------

&nbsp;9. Leer Libro por ID

10\. Leer Ficha por ID

-------------------------------------

&nbsp;0. Salir

=====================================



Seleccione una opción: 1

=== Crear Libro ===

Título: Cien años de Soledad

Autor: Gabriel Garcia Marquez

Editorial: Sudamericana

Año edición: 1967

Debe crear una Ficha Bibliogrfica para este libro.

=== Crear Ficha Bibliogrfica ===

ISBN: 1

Clasificado Dewey: 863.64

Estantería: A1

Idiomas disponibles:

1\. ESPAOL

2\. INGLES

3\. FRANCES

4\. PORTUGUES

5\. OTRO

Seleccione idioma: 1

--------------------------------------------------------------------------

▪ Enlace al video

---------------------------------------------------------------------------



