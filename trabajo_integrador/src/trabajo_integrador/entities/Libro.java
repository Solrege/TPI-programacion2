
package trabajo_integrador.entities;


public class Libro {
private int id_libro;
private boolean eliminado;
private String titulo;
private String autor;
private String editorial;
private int anioEdicion;
private FichaBibliografica ficha;

//CONSTRUCTORES
//constructor vacio 
public Libro() { }

//constructor sin ID 
public Libro(boolean eliminado, String titulo, String autor, String editorial, int anioEdicion, FichaBibliografica ficha) { 
    this.eliminado = eliminado;
    this.titulo = titulo;
    this.autor = autor;
    this.editorial = editorial;
    this.anioEdicion = anioEdicion;
    this.ficha = ficha;
}

//constructor completo. Para asociación unidireccional
public Libro(int id_libro, boolean eliminado, String titulo, String autor, String editorial, int anioEdicion) {
    this.id_libro = id_libro; 
    this.eliminado = eliminado;
    this.titulo = titulo; 
    this.autor = autor;
    this.editorial = editorial;
    this.anioEdicion = anioEdicion;
}

//GETTERS Y SETTERS
public int getId_libro() {
    return id_libro;
}
public void setId_libro(int id_libro) { 
    this.id_libro = id_libro; 
} 

public boolean isEliminado() {
    return eliminado; 
} 
public void setEliminado(boolean eliminado) {
    this.eliminado = eliminado;
}

public String getTitulo() { 
    return titulo; 
} 
public void setTitulo(String titulo) {
    this.titulo = titulo; 
}

public String getAutor() {
    return autor; 
}
public void setAutor(String autor) {
    this.autor = autor; 
} 

public String getEditorial() { 
    return editorial;
} 
public void setEditorial(String editorial) {
    this.editorial = editorial; 
}

public int getAnioEdicion() {
    return anioEdicion; 
} 
public void setAnioEdicion(int anioEdicion) { 
    this.anioEdicion = anioEdicion; 
}

public FichaBibliografica getFicha() {
    return ficha; 
}
public void setFicha(FichaBibliografica ficha) {
    this.ficha = ficha;
}


//OTROS METODOS 
public void marcarEliminado() {
    this.eliminado = true;
}

// Método para mostrar atributos (y los de la ficha asociada) 
public void mostrarDatos() { 
    System.out.println("=== Libro ===");
System.out.println("ID Libro: " + id_libro);
System.out.println("Eliminado: " + eliminado);
System.out.println("Título: " + titulo);
System.out.println("Autor: " + autor);
System.out.println("Editorial: " + editorial);
System.out.println("Año de Edición: " + anioEdicion);
if (ficha != null) { 
    System.out.println("--- Datos de Ficha ---");
ficha.mostrarDatos();// usa el método de la otra clase 
} else { 
System.out.println("No tiene ficha asociada.");
    }
}

@Override public String toString() { return "Libro{" + "id_libro=" + id_libro + ", eliminado=" + eliminado + ", titulo=" + titulo + ", autor=" + autor + ", editorial=" + editorial + ", anioEdicion=" + anioEdicion + ", ficha=" + ficha + '}';
    }

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Libro)) return false;
    Libro libro = (Libro) o;
    return id_libro == libro.id_libro;
}

@Override
public int hashCode() {
    return Integer.hashCode(id_libro);
}
 
}
