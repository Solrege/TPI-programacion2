package trabajo_integrador.dao;

import trabajo_integrador.models.FichaBibliografica;
import trabajo_integrador.models.Idioma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FichaBibliograficaDAO implements GenericDAO<FichaBibliografica> {

    @Override
    public void crear(FichaBibliografica fichaBibliografica, Connection conn) throws Exception {

    }

    @Override
    public void crear(FichaBibliografica entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(FichaBibliografica fichaBibliografica, Connection conn) throws Exception {

    }

    @Override
    public void actualizar(FichaBibliografica entidad) throws Exception {
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
    public FichaBibliografica leer(int id, Connection conn) throws Exception {
        return new FichaBibliografica();
    }

    @Override
    public FichaBibliografica leer(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<FichaBibliografica> leerTodos(Connection conn) throws Exception {
        return List.of();
    }

    @Override
    public List<FichaBibliografica> leerTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void recuperar(int id, Connection conn) throws Exception {

    }

    @Override
    public void recuperar(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // TODO: Ver si poner sentencia sql como constante y chequear que nombre de las columnas sean válidos
    public FichaBibliografica getByISBN(String isbn, Connection conn) throws SQLException {
        String sql = "SELECT id, isbn, clasificacionDewey, estanteria FROM ficha_bibliografica WHERE isbn = ? AND eliminado = FALSE";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, isbn);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    FichaBibliografica ficha = new FichaBibliografica();
                    ficha.setId(rs.getInt("id"));
                    ficha.setIsbn(rs.getString("isbn"));
                    ficha.setClasificadoDewey(rs.getString("clasificacionDewey"));
                    ficha.setEstanteria(rs.getString("estanteria"));
                    ficha.setIdioma(Idioma.valueOf(rs.getString("idioma")));

                    return ficha;
                }
            }
        }

        return null;
    }

    // TODO: Ver si poner sentencia sql como constante y chequear que nombre de la columna sea válido
    public boolean existeFichaAsociada(int idFicha, Connection conn) throws SQLException {
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
