
package trabajo_integrador.entities;



public class FichaBibliografica {
    private int id;
    private boolean eliminado;
    private String isbn;
    private String clasificadoDewey;
    private String estanteria;
    private String idioma; 
    
    
//CONSTRUCTORES 
//Constructor vacío
public FichaBibliografica() { } 

// Constructor sin ID (útil para crear nuevos objetos antes de insertar en BD) 
public FichaBibliografica(boolean eliminado, String isbn, String clasificadoDewey, String estanteria, String idioma) {
    this.eliminado = eliminado;
    this.isbn = isbn;
    this.clasificadoDewey = clasificadoDewey;
    this.estanteria = estanteria;
    this.idioma = idioma; 
} 

// Constructor completo (incluye ID) 
public FichaBibliografica(int id, boolean eliminado, String isbn, String clasificadoDewey, String estanteria, String idioma) {
    this.id = id;
    this.eliminado = eliminado;
    this.isbn = isbn;
    this.clasificadoDewey = clasificadoDewey;
    this.estanteria = estanteria;
    this.idioma = idioma; 
} 

//GETTERS Y SETTERS 
public int getId() { 
    return id;
}
public void setId(int id) {
    this.id = id; } 

public boolean isEliminado() {
    return eliminado;
}
public void setEliminado(boolean eliminado) {
    this.eliminado = eliminado; } 

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
    System.out.println("ID: " + id);
    System.out.println("Eliminado: " + eliminado);
    System.out.println("ISBN: " + isbn);
    System.out.println("Clasificación Dewey: " + clasificadoDewey);
    System.out.println("Estantería: " + estanteria);
    System.out.println("Idioma: " + idioma); } 


@Override public String toString() { return "FichaBibliografica{" + "id=" + id + ", eliminado=" + eliminado + ", isbn=" + isbn + ", clasificadoDewey=" + clasificadoDewey + ", estanteria=" + estanteria + ", idioma=" + idioma + '}'; }

@Override
public boolean equals(Object o) {
    if (this == o) return true;   
    if (!(o instanceof FichaBibliografica)) return false;
    FichaBibliografica ficha = (FichaBibliografica) o;
    return id == ficha.id;                              
}

@Override
public int hashCode() {
    return Integer.hashCode(id);                       
}


}

