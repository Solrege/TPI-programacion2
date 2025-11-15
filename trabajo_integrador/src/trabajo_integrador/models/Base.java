
package trabajo_integrador.models;


public abstract class Base {
private int id;
private boolean eliminado;


    public Base(int id, boolean eliminado) {
        this.id = id;
        this.eliminado = eliminado;
    }

    public Base() {
    }
            
    
    
   public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public boolean isEliminado() {
        return eliminado;
    }
    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

}
