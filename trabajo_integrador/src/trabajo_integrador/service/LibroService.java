package trabajo_integrador.service;

import trabajo_integrador.dao.GenericDAO;
import trabajo_integrador.dao.LibroDAO;
import trabajo_integrador.models.Libro;

import java.util.List;

public class LibroService implements GenericService<Libro> {
    private final GenericDAO<Libro> libroDAO;

    public LibroService(GenericDAO<Libro> libroDAO) {
        this.libroDAO = libroDAO;
    }

    @Override
    public void insertar(Libro libro) throws Exception {
        // VALIDACIONES

    }

    @Override
    public void actualizar(Libro libro) throws Exception {
        //VALIDACIONES

        libroDAO.actualizar(libro);

    }

    @Override
    public void eliminar(int id) throws Exception {
        libroDAO.eliminar(id);

    }

    @Override
    public Libro getById(int id) throws Exception {
        return libroDAO.getById(id);
    }

    @Override
    public List<Libro> getAll() throws Exception {
        return libroDAO.getAll();
    }
}
