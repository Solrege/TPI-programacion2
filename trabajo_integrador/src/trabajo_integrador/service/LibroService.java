package trabajo_integrador.service;

import trabajo_integrador.dao.FichaBibliograficaDAO;
import trabajo_integrador.dao.GenericDAO;
import trabajo_integrador.dao.LibroDAO;
import trabajo_integrador.models.FichaBibliografica;
import trabajo_integrador.models.Libro;

import java.sql.Connection;
import java.util.List;

public class LibroService implements GenericService<Libro> {
    private final LibroDAO libroDAO;
    private final FichaBibliograficaDAO fichaBibliograficaDAO;

    public LibroService(LibroDAO libroDAO, FichaBibliograficaDAO fichaBibliograficaDAO) {
        this.libroDAO = libroDAO;
        this.fichaBibliograficaDAO = fichaBibliograficaDAO;
    }

    @Override
    public void insertar(Libro libro) throws Exception {
        // VALIDACIONES

        // TODO: REFACTOR CON NOMBRE REAL DE LA CLASE Y DEL METODO
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            FichaBibliografica ficha = libro.getFicha();

            // Búsqueda de Ficha existente mediante el ISBN
            FichaBibliografica fichaExiste = fichaBibliograficaDAO.getByISBN(ficha.getIsbn(), conn);

            // Si existe, se verifica que no esté asociada, se reutiliza y se setea en Libro.
            if (fichaExiste != null) {

                // Verificación que en Ficha no exista otro Libro
                if (fichaBibliograficaDAO.existeFichaAsociada(libro.getFicha().getId(), conn)) {
                    throw new IllegalArgumentException("La Ficha Bibliográfica ya está asociada a otro libro");
                }

                libro.setFicha(fichaExiste);

            } else {
                // Como no existe, se crea
                fichaBibliograficaDAO.insertarTx(ficha, conn);
            }

            // Creación de Libro
            libroDAO.insertarTx(libro, conn);

            conn.commit();
            conn.setAutoCommit(true);

            System.out.println("Libro y Ficha creada correctamente");
        } catch (Exception e) {
            throw new Exception("Error al insertar libro: " + e.getMessage());
        }

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
