package trabajo_integrador.dao;

import trabajo_integrador.models.FichaBibliografica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FichaBibliograficaDAO implements GenericDAO<FichaBibliografica> {

    @Override
    public void insertar(FichaBibliografica fichaBibliografica) throws Exception {

    }

    @Override
    public void insertarTx(FichaBibliografica fichaBibliografica, Connection conn) throws Exception {

    }

    @Override
    public void actualizar(FichaBibliografica fichaBibliografica) throws Exception {

    }

    @Override
    public void eliminar(int id) throws Exception {

    }

    @Override
    public FichaBibliografica getById(int id) throws Exception {
        return null;
    }

    @Override
    public List<FichaBibliografica> getAll() throws Exception {
        return List.of();
    }

    @Override
    public void recuperar(int id) throws Exception {

    }

    public boolean existeFichaAsociada(int idFicha, Connection conn) throws SQLException {

        // TODO: Ver si poner sentencia sql como constante
        String sql = "SELECT COUNT(*) FROM libro WHERE ficha_id = ? AND eliminado = FALSE";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFicha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }

        return false;
    }

}
