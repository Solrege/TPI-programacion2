package trabajo_integrador.dao;

import trabajo_integrador.models.Libro;

import java.sql.Connection;
import java.util.List;

public class LibroDAO implements GenericDAO<Libro> {

    @Override
    public void crear(Libro libro, Connection conn) throws Exception {

    }

    @Override
    public void crear(Libro entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(Libro libro, Connection conn) throws Exception {

    }

    @Override
    public void actualizar(Libro entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int id, Connection conn) throws Exception {

    }

    @Override
    public void eliminar(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Libro leer(int id, Connection conn) throws Exception {
        return new Libro();
    }

    @Override
    public Libro leer(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Libro> leerTodos(Connection conn) throws Exception {
        return List.of();
    }

    @Override
    public List<Libro> leerTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void recuperar(int id, Connection conn) throws Exception {

    }

    @Override
    public void recuperar(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
