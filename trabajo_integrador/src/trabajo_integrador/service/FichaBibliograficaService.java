package trabajo_integrador.service;

import trabajo_integrador.dao.GenericDAO;
import trabajo_integrador.models.FichaBibliografica;

import java.util.List;

public class FichaBibliograficaService implements GenericService<FichaBibliografica> {

    private final GenericDAO<FichaBibliografica> fichaBibliograficaDAO;

    public FichaBibliograficaService(GenericDAO<FichaBibliografica> fichaBibliograficaDAO) {
        this.fichaBibliograficaDAO = fichaBibliograficaDAO;
    }

    @Override
    public boolean insertar(FichaBibliografica ficha) throws Exception {
        // Validaciones
        validacionesInsertFicha(ficha);

        try {
            fichaBibliograficaDAO.crear(ficha);
        } catch (Exception e) {
            throw new Exception("Error al insertar ficha Bibliografica: " + e.getMessage());
        }

        return true;
    }

    @Override
    public boolean actualizar(FichaBibliografica ficha) throws Exception {
        // Validaciones
        validacionesUpdateFicha(ficha);
        getById(ficha.getId());

        // Actualización
        try {
            fichaBibliograficaDAO.actualizar(ficha);
        } catch (Exception e) {
            throw new Exception("Error al actualizar ficha Bibliografica: " + e.getMessage());
        }

        return true;
    }

    @Override
    public boolean eliminar(int id) throws Exception {
        // Validaciones
        getById(id);

        // Eliminación
        try {
            fichaBibliograficaDAO.eliminar(id);
        } catch (Exception e) {
            throw new Exception("Error al eliminar ficha Bibliografica: " + e.getMessage());
        }

        return true;
    }

    @Override
    public FichaBibliografica getById(int id) throws Exception {
        // Validaciones
        validacionID(id);

        try {
            FichaBibliografica ficha = fichaBibliograficaDAO.leer(id);

            if (ficha == null) {
                throw new Exception("Ficha Bibliografica no encontrada");
            }

            return ficha;

        }  catch (Exception e) {
            throw new Exception("Error al leer ficha Bibliografica: " + e.getMessage());
        }
    }

    @Override
    public List<FichaBibliografica> getAll() throws Exception {
        try {
            return fichaBibliograficaDAO.leerTodos();
        }  catch (Exception e) {
            throw new Exception("Error al leer ficha Bibliografica: " + e.getMessage());
        }
    }

    protected static void validacionesInsertFicha(FichaBibliografica ficha) {
        if (ficha == null)
            throw new IllegalArgumentException("La ficha no puede ser nula.");

        if (ficha.getIsbn() == null || ficha.getIsbn().isBlank())
            throw new IllegalArgumentException("El ISBN es obligatorio.");

        if (ficha.getIsbn().length() > 17) {
            throw new IllegalArgumentException("EL ISBN excede la cantidad de caracteres (17)");
        }

        if (ficha.getClasificadoDewey().length() > 20) {
            throw new IllegalArgumentException("El Clasificado de dewey excede la cantidad de caracteres (20).");
        }

        if (ficha.getEstanteria().length() > 20) {
            throw new IllegalArgumentException("La estantería excede la cantidad de caracteres (20).");
        }
    }

    private static void validacionesUpdateFicha(FichaBibliografica ficha) {
        validacionID(ficha.getId());
        validacionesInsertFicha(ficha);
    }

    private static void validacionID(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("El id no puede ser 0 o negativo.");
    }
}
