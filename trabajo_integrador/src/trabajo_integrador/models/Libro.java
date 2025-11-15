
package trabajo_integrador.models;


public class Libro extends Base {
    private String titulo;
    private String autor;
    private String editorial;
    private int anioEdicion;
    private FichaBibliografica ficha;

    //CONSTRUCTORES
    //constructor vacio

    public Libro() { super(); }

    //constructor completo. Para asociación unidireccional
    public Libro(int id, boolean eliminado, String titulo, String autor, String editorial, int anioEdicion) {
            super(id, false);
            this.titulo = titulo;
            this.autor = autor;
            this.editorial = editorial;
            this.anioEdicion = anioEdicion;
    }
    
public Libro(String titulo, String autor, String editorial, int anioEdicion, FichaBibliografica ficha) {
    super(0, false);
    this.titulo = titulo;
    this.autor = autor;
    this.editorial = editorial;
    this.anioEdicion = anioEdicion;
    this.ficha = ficha;
}



    //GETTERS Y SETTERS
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

    // Método para mostrar atributos (y los de la ficha asociada)
    public void mostrarDatos() {
        System.out.println("=== Libro ===");
        System.out.println("Id: " + getId());
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Editorial: " + editorial);
        System.out.println("Año de Edición: " + anioEdicion);
        System.out.println("Eliminado: " + isEliminado());

        if (ficha != null) {
            ficha.mostrarDatos();// usa el método de la otra clase
        } else {
            System.out.println("No tiene ficha asociada.");
        }
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + getId() +
                "titulo=" + titulo +
                ", autor=" + autor +
                ", editorial=" + editorial +
                ", anioEdicion=" + anioEdicion +
                ", ficha=" + ficha +
                ", eliminado=" + isEliminado() + '}';
    }
}
