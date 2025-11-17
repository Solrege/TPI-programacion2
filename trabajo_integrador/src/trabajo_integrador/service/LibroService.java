package trabajo_integrador.service;

import trabajo_integrador.config.DatabaseConnection;
import trabajo_integrador.dao.FichaBibliograficaDAO;
import trabajo_integrador.dao.LibroDAO;
import trabajo_integrador.models.FichaBibliografica;
import trabajo_integrador.models.Libro;

import java.sql.Connection;
import java.time.Year;
import java.util.List;

public class LibroService implements GenericService<Libro> {
    private final LibroDAO libroDAO;
    private final FichaBibliograficaDAO fichaBibliograficaDAO;

    public LibroService(LibroDAO libroDAO, FichaBibliograficaDAO fichaBibliograficaDAO) {
        this.libroDAO = libroDAO;
        this.fichaBibliograficaDAO = fichaBibliograficaDAO;
    }

    @Override
    public boolean insertar(Libro libro) throws Exception {
        FichaBibliografica ficha = libro.getFicha();

        // VALIDACIONES
        validacionesInsertLibro(libro);
        FichaBibliograficaService.validacionesInsertFicha(ficha);

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try {
                // Búsqueda de Ficha existente mediante el ISBN
                FichaBibliografica fichaExiste = fichaBibliograficaDAO.getByISBN(ficha.getIsbn(), conn);

                // Si existe, se verifica que no esté asociada, se reutiliza y se setea en Libro.
                if (fichaExiste != null) {

                    // Verificación que Ficha no esté asociada a otro libro
                    if (fichaBibliograficaDAO.existeFichaAsociada(fichaExiste.getId(), conn)) {
                        throw new IllegalArgumentException("La Ficha Bibliográfica ya está asociada a otro libro");
                    }

                    libro.setFicha(fichaExiste);

                } else {
                    // Como no existe, se crea
                    try {
                        fichaBibliograficaDAO.crear(ficha, conn);
                    } catch (Exception e) {
                        throw new Exception("Error al crear el libro", e);
                    }
                }

                // Creación de Libro
                try {
                    libroDAO.crear(libro, conn);
                } catch (Exception e) {
                    throw new Exception("Error al crear libroDAO", e);
                }

                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                throw new Exception("Error al insertar libro y ficha", e);
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (Exception e) {
            throw new Exception("Error inesperado al insertar libro", e);
        }

        return true;
    }

    @Override
    public boolean actualizar(Libro libro) throws Exception {
        //VALIDACIONES
        validacionesUpdateLibro(libro);
        getById(libro.getId());

        // Actualización
        try {
            libroDAO.actualizar(libro);
        } catch (Exception e) {
            throw new Exception("Error al actualizar libro: " + e.getMessage());
        }

        return true;
    }

    @Override
    public boolean eliminar(int id) throws Exception {
        // VALIDACIONES
        getById(id);

        // Eliminación
        try {
            libroDAO.eliminar(id);
        } catch (Exception e) {
            throw new Exception("Error al eliminar libro: " + e.getMessage());
        }

        return true;
    }

    @Override
    public Libro getById(int id) throws Exception {
        // VALIDACIONES
        validacionID(id);

        try {
            Libro libro = libroDAO.leer(id);
            if (libro == null) {
                throw new Exception("El libro no existe");
            }

            return libro;

        } catch (Exception e) {
            throw new Exception("Error al leer libro: " + e.getMessage());
        }
    }

    @Override
    public List<Libro> getAll() throws Exception {
        try {
            return libroDAO.leerTodos();
        }  catch (Exception e) {
            throw new Exception("Error al leer libros: " + e.getMessage());
        }
    }

    private static void validacionesInsertLibro(Libro libro)  {
        if (libro == null)
            throw new IllegalArgumentException("El libro no puede ser nulo.");

        if (libro.getTitulo() == null || libro.getTitulo().isBlank())
            throw new IllegalArgumentException("El título del libro es obligatorio.");

        if (libro.getTitulo().length() > 150) {
            throw new IllegalArgumentException("El título no puede exceder los 150 caracteres.");
        }

        if (libro.getAutor() == null || libro.getAutor().isBlank())
            throw new IllegalArgumentException("El autor del libro es obligatorio.");

        if (libro.getAutor().length() > 120) {
            throw new IllegalArgumentException("El nombre del autor no puede exceder los 120 caracteres.");
        }

        if (libro.getEditorial().length() > 100) {
            throw new IllegalArgumentException("El nombre de la editorial no puede exceder los 100 caracteres.");
        }

        int currentYear = Year.now().getValue();
        if (libro.getAnioEdicion() < 1400 || libro.getAnioEdicion() > currentYear) {
            throw new IllegalArgumentException("El año de edición debe ser válido (entre 1400 y " + currentYear + ")");
        }

        if (libro.getFicha() == null)
            throw new IllegalArgumentException("El libro debe tener una ficha bibliográfica asociada.");

    }

    private static void validacionesUpdateLibro(Libro libro)  {
        validacionID(libro.getId());
        validacionesInsertLibro(libro);
    }

    private static void validacionID(int id)  {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del libro no puede ser 0 o negativo.");
        }
    }
}
