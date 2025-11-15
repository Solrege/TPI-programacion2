
package trabajo_integrador.models;

public class FichaBibliografica extends Base{
    private String isbn;
    private String clasificadoDewey;
    private String estanteria;
    private Idioma idioma;

//CONSTRUCTORES 
//Constructor vacío
   public FichaBibliografica() {
   super();
    } 

// Constructor completo 
public FichaBibliografica(int id, boolean eliminado, String isbn, String clasificadoDewey, String estanteria, Idioma idioma) {
        super(id, false);
        this.isbn = isbn;
        this.clasificadoDewey = clasificadoDewey;
        this.estanteria = estanteria;
        this.idioma = idioma;
    }
    
// Constructor simplificado SIN idioma (por defecto null)
public FichaBibliografica(String isbn, String clasificadoDewey, String estanteria) {
    super(0, false);
    this.isbn = isbn;
    this.clasificadoDewey = clasificadoDewey;
    this.estanteria = estanteria;
    this.idioma = null;
}

// Constructor simplificado CON idioma.
// Se utiliza para crear una ficha nueva (id = 0) cuando el usuario ya seleccionó un idioma.
// Es útil para instancias nuevas que aún no existen en base de datos.
public FichaBibliografica(String isbn, String clasificadoDewey, String estanteria, Idioma idioma) {
    super(0, false);
    this.isbn = isbn;
    this.clasificadoDewey = clasificadoDewey;
    this.estanteria = estanteria;
    this.idioma = idioma;
}


    //GETTERS Y SETTERS
    public String getIsbn() {
       return isbn;
    }

    public void setIsbn(String isbn) {
       this.isbn = isbn;
   }

    public String getClasificadoDewey() {
       return clasificadoDewey;
    }

    public void setClasificadoDewey(String clasificadoDewey) {
       this.clasificadoDewey = clasificadoDewey;
    }

    public String getEstanteria() {
       return estanteria;
    }

    public void setEstanteria(String estanteria){
       this.estanteria = estanteria;
    }

    public Idioma getIdioma() {
       return idioma;
   }

    public void setIdioma(Idioma idioma) {
       this.idioma = idioma;
   }

    //OTROS METODOS

    // Método para mostrar atributos de forma legible
    public void mostrarDatos() {
        System.out.println("=== Ficha Bibliográfica ===");
        System.out.println("Id: " + getId());
        System.out.println("ISBN: " + isbn);
        System.out.println("Clasificación Dewey: " + clasificadoDewey);
        System.out.println("Estantería: " + estanteria);
        System.out.println("Idioma: " + idioma);
        System.out.println("Eliminado: " + isEliminado());
    }

        @Override
        public String toString() {
            return "FichaBibliografica{" +
                    "id=" + getId() +
                    "isbn=" + isbn +
                    ", clasificadoDewey="+ clasificadoDewey +
                    ", estanteria=" + estanteria +
                    ", idioma=" + idioma +
                    ", eliminado=" + isEliminado() +
                    '}';
        }
        
}

