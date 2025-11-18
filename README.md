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







3\. Verificar la carga

Para verificar que los datos se cargaron correctamente podemos ejecutar el siguiente comando:

SELECT COUNT(\*) FROM nombre\_tabla;

por ejemplo SELECT COUNT(\*) FROM ficha\_bibliografica;





-------------------------------------------------------------------------------

Configuración de la conexión (DatabaseConnection)

--------------------------------------------------------------------------------



Para configurar la conexión con la base de datos, es necesario abrir el archivo \[DatabaseConnection.java] ubicado en (https://github.com/Solrege/TPI-programacion2/blob/main/trabajo\_integrador/src/trabajo\_integrador/config/DatabaseConnection.java), lo ideal es realizarlo desde un IDE como NetBeans

















---

▪ Cómo compilar y ejecutar (credenciales de prueba y flujo de uso).

---





Cómo compilar y ejecutar

Configuración de credenciales de base de datos:



Antes de ejecutar la aplicación, debes configurar las credenciales de conexión a MySQL en la clase DatabaseConnection:



Ubicación: Config/DatabaseConnection.java



javaprivate static final String URL = "jdbc:mysql://localhost:3306/tpi\_bd";



private static final String USER = "tu\_usuario";



private static final String PASSWORD = "tu\_contraseña";





-----------------------------------------------------------------------

Compilación y ejecución

&nbsp;Desde el IDE (recomendado)



Abrir el proyecto en tu IDE (IntelliJ IDEA, NetBeans, Eclipse), nosotras utilizamos NetBeans

Hay que asegurarse de tener el driver JDBC de MySQL en el classpath del proyecto

Ejecuta la clase principal: Main.Principal.java



\### Flujo de uso de la aplicación



Al ejecutar la aplicación, se mostrará el menú principal con las siguientes opciones:



\#### \*\*Menú Principal\*\*

```

======================================

&nbsp;  SISTEMA BIBLIOTECARIO

======================================

&nbsp;MEN PRINCIPAL        

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



Seleccione una opción: 



\### \*\*Primera Sección\*\*



Permite gestionar los libros del sistema:



1 - \*\*Crear libro:\*\* Solicita título, autor, año de publicación y género

2 - \*\*Listar libros:\*\* Muestra todos los libros activos en el sistema

3- \*\*Actualizar libro:\*\* Modifica los datos de un libro existente

4- \*\*Eliminar libro:\*\* Realiza una baja lógica del libro



\*\*Ejemplo de creación de libro:\*\*

```

Título: Cien años de soledad

Autor: Gabriel García Márquez

Editorial: Sudamericana

Año Edicion: 1967



```



\#### \*\*Segunda Sección\*\*



Gestiona la información bibliográfica detallada:



1- \*\*Crear Ficha Bibliográfica:\*\* Solicita editorial, ISBN, idioma, número de páginas y sinopsis

2- \*\*Listar fichas:\*\* Muestra todas las fichas activas

\- \*\*Ver ficha por ID:\*\* Busca y muestra una ficha específica

3- \*\*Actualizar ficha:\*\* Modifica los datos de una ficha existente

-4 \*\*Eliminar ficha:\*\* Elimina la ficha del sistema



\*\*Ejemplo de creación de ficha:\*\*

```

Editorial: Sudamericana

ISBN: 978-0307474728

Idioma: Español

Número de páginas: 496

Sinopsis: La historia de la familia Buendía...

```



\#### \*\*3. Buscar libro por ISBN\*\*



Permite buscar un libro específico ingresando su código ISBN. El sistema buscará en las fichas bibliográficas asociadas.



\*\*Ejemplo:\*\*

```

ISBN: 978-0307474728

```



\#### \*\*4. Buscar libro por título\*\*



Realiza una búsqueda flexible por título (coincidencia parcial, no sensible a mayúsculas).



\*\*Ejemplo:\*\*

```

Título (o parte): años

\# Encontrará "Cien años de soledad"

```

5\. Probar rollback (error simulado)

Función de demostración que muestra el funcionamiento del sistema de transacciones:



Intenta insertar un libro

Fuerza un error intencionalmente

Demuestra que la transacción se revierte correctamente

Verifica que el libro NO quedó guardado en la base de datos



Validaciones implementadas

El sistema implementa las siguientes validaciones automáticas:

Libros:



Título y autor son obligatorios

Año de publicación debe ser positivo

No se puede eliminar un libro con ficha bibliográfica asociada



Fichas Bibliográficas:



Editorial, ISBN e idioma son obligatorios

ISBN debe ser único en el sistema

Número de páginas debe ser positivo (si se proporciona)



Manejo de transacciones

Todas las operaciones de escritura (crear, actualizar, eliminar) utilizan transacciones mediante TransactionManager:



Si la operación es exitosa: se confirma con commit()

Si ocurre un error: se revierte automáticamente con rollback()



Esto garantiza la integridad de los datos en todo momento.



--------------------------------------------------------------------------

▪ Enlace al video

---------------------------------------------------------------------------



