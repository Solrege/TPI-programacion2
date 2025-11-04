
package trabajo_integrador.models;



public class FichaBibliografica extends Base{
    private String isbn;
    private String clasificadoDewey;
    private String estanteria;
    private String idioma; 
    
    
//CONSTRUCTORES 
//Constructor vacío
   public FichaBibliografica() {
   super();
    } 

// Constructor completo 
    public FichaBibliografica(int id, boolean eliminado, String isbn, String clasificadoDewey, String estanteria, String idioma) {
        super(id, false);
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
    this.isbn = isbn; } 

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

public String getIdioma() 
{ return idioma;
} 
public void setIdioma(String idioma) {
    this.idioma = idioma; } 

//OTROS METODOS 
//Metodo de validacion para mover posteriormente a Service 
public boolean idiomaValido() { boolean valido = idioma.equals("Español") || idioma.equals("Inglés") || idioma.equals("Francés") || idioma.equals("Portugués") || idioma.equals("Otro"); if (!valido) {
    System.out.println("❌ El idioma ingresado no es válido. Debe ser: Español, Inglés, Francés, Portugués u Otro."); 
}
return valido;
} 


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

