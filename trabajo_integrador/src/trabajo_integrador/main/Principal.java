
package trabajo_integrador.main;

import trabajo_integrador.entities.FichaBibliografica;
import trabajo_integrador.entities.Libro;


public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       FichaBibliografica ficha = new FichaBibliografica(1, false, "978-987-1234567", "500", "A1", "Español");
       Libro l1 = new Libro(1, false, "Fundamentos de Base de Datos", "Elmasri & Navathe", "Pearson", 2220);
       l1.setFicha(ficha);
       l1.mostrarDatos();
    }

}
