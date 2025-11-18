# Trabajo Final Integrador de programación 



**Descripción del dominio elegido.**

El proyecto modela un sistema de gestión para una Biblioteca, basándose en los siguientes modelos de relación:

- Foco principal: Relación 1 -> 1 unidireccional entre Libro y FichaBibliografica. Cada libro tiene una ficha única que detalla su información técnica.

- Entidades de apoyo 



**Requisitos (Java/BD) y pasos para crear la base con el .sql provisto.**

Base de Datos:
- MySQL 8.0 o superior
- DBeaver Community (o cualquier cliente SQL compatible con MySQL)

Java:
- JDK 11 o superior

Driver JDBC de MySQL (mysql-connector-java) versión 8.4

---------------------------------------------------------------------
## Estructura del Proyecto

```
TPI-programacion2/  
├── build/  
├── nbproject/
├── sql/
│   ├─ create_db.sql
│   └─ seed_data.sql  
└── src/  
    └── trabajo_integrador/  
        ├── config/  
        │   └── DatabaseConnection.java  
        ├── dao/  
        │   ├── FichaBibliograficaDAO.java  
        │   ├── GenericDAO.java  
        │   └── LibroDAO.java  
        ├── main/  
        │   └── Principal.java
            ├── FichaBibliografica.java    
        ├── models/  
        │   ├── Base.java  
        │   ├── FichaBibliografica.java  
        │   ├── Idioma.java  
        │   └── Libro.java  
        └── service/  
            ├── FichaBibliograficaService.java  
            ├── GenericService.java  
            └── LibroService.java  
```
-----------------------------------------------------------------------

## Pasos para la carga de datos:

La carga de datos se realiza ejecutando dos archivos SQL en el orden especificado, primero el `create\_db.sql`, y luego la inserción de datos desde `seed_data.sql`

A continuación, explicaremos el proceso utilizando MySQL Workbench como herramienta de gestión de base de datos.

1. Ejecutar el script de creación de tablas

    Abrir MySQL y asegurarse de conectar la base de datos

    Abrir el archivo `create_db.sql`

    Ejecuta el script completo (Ctrl+Enter o botón "Execute SQL Script" con forma de rayo)

Este script creará la base de datos y todas las tablas necesarias con sus respectivas relaciones:

2. Ejecutar el script de carga de datos

    Una vez creadas las tablas, abrir el archivo `seed_data.sql`

    Y ejecutar el script completo.

    Este script lo que hace es insertar todos los datos iniciales en las tablas previamente creadas, en nuestro caso 3 libros y 3 fichas bibliográficas.

3. Verificar la carga

    Para verificar que los datos se cargaron correctamente podemos ejecutar el siguiente comando:

    SELECT COUNT(\*) FROM nombre\_tabla;


-------------------------------------------------------------------------------

## Configuración de la conexión (DatabaseConnection)

Para configurar la conexión con la base de datos, es necesario abrir el archivo clase DatabaseConnection.java ubicado en `/src/trabajo_integrador/config/`, lo ideal es realizarlo desde un IDE como NetBeans, cargar el proyecto y luego abrir el paquete config en donde se encuentra ubicado el archivo. Se debe tener en cuenta que establece la conexión entre el sistema y la base cargando también previamente la versión del Driver de JDBC de MySQL, éste se carga desde Libraries --> Add Jar/Folder --> mysql-connector-j-8.4.0.jar

Al ejecutarl el código, valida que la url sea la de por defecto "localhost:3306", el usuario y la contraseña sean correctos, dependiendo en qué computadora se ejecutan, sino modificarlos.

El objetivo es que cualquier DAO pueda obtener una conexión simplemente llamando: Connection conn = DatabaseConnection.getConnection();


### Probar la conexión (TestConnection)

Con éste archivo que se ubica en `main\TestConnection.java` se debe ejecutar y si muestra el mensaje ¡Conexión a la BBDD exitosa!, se estableció la conexión, de lo contrario devuelve Error en la conexión.


--------------------------------------------------------------------------------------------------------------

# Ejecutar el Sistema Completo

Abrir el proyecto en tu IDE (IntelliJ IDEA, NetBeans, Eclipse), nosotras utilizamos NetBeans

Hay que asegurarse de tener el driver JDBC de MySQL en el classpath del proyecto

El archivo principal es `\Principal.Java` ubicado en el main. Es el punto de entrada del sistema y el lo primero que se eje

Su funcion es inicializar objetos básicos (Libro y ficha_bibliografica), mostrar un ejemplo simple por consola e iniciar un menú interactivo del sistema.

### Pasos a seguir:

1\. Ejecutar `create_db.sql`  

2\. Ejecutar `seed_data.sql`  

3\. Configurar DatabaseConnection.java  

4\. Ejecutar `Principal.java`  

5\. Navegar con el menú por consola  



### Funcionalidades del Sistema

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

### ▪ Enlace al video

---------------------------------------------------------------------------



