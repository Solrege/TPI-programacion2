package trabajo_integrador.service;

import trabajo_integrador.dao.GenericDAO;
import trabajo_integrador.models.FichaBibliografica;

import java.util.List;

public class FichaBibliograficaService implements GenericService<FichaBibliografica> {

    private final GenericDAO<FichaBibliografica> FichaBibliograficaDAO;

    public FichaBibliograficaService(GenericDAO<FichaBibliografica> fichaBibliograficaDAO) {
        this.FichaBibliograficaDAO = fichaBibliograficaDAO;
    }

    @Override
    public void insertar(FichaBibliografica ficha) throws Exception {
        // Validaciones

        System.out.println("Insertando Ficha Bibliografica.....");
        FichaBibliograficaDAO.crear(ficha);

    }

    @Override
    public void actualizar(FichaBibliografica ficha) throws Exception {
        FichaBibliograficaDAO.actualizar(ficha);

    }

    @Override
    public void eliminar(int id) throws Exception {
        FichaBibliograficaDAO.eliminar(id);

    }

    @Override
    public FichaBibliografica getById(int id) throws Exception {
        return FichaBibliograficaDAO.leer(id);
    }

    @Override
    public List<FichaBibliografica> getAll() throws Exception {
        return FichaBibliograficaDAO.leerTodos();
    }
}
