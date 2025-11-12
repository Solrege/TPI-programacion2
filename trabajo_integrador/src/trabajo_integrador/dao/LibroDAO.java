package trabajo_integrador.dao;

import trabajo_integrador.models.Libro;

import java.sql.Connection;
import java.util.List;

public class LibroDAO implements GenericDAO<Libro> {
    @Override
    public void insertar(Libro libro) throws Exception {

    }

    @Override
    public void insertarTx(Libro libro, Connection conn) throws Exception {

    }

    @Override
    public void actualizar(Libro libro) throws Exception {

    }

    @Override
    public void eliminar(int id) throws Exception {

    }

    @Override
    public Libro getById(int id) throws Exception {
        return null;
    }

    @Override
    public List<Libro> getAll() throws Exception {
        return List.of();
    }

    @Override
    public void recuperar(int id) throws Exception {

    }
}
