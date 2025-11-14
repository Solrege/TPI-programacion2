package trabajo_integrador.dao;

import trabajo_integrador.models.FichaBibliografica;
import trabajo_integrador.models.Idioma;
import trabajo_integrador.config.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String sql = "UPDATE fichaBibliografica SET isbn = ?, clasificadoDewey = ?, estanteria = ?, idioma = ? WHERE id = ? AND eliminado = FALSE";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fichaBibliografica.getIsbn());
            stmt.setString(2, fichaBibliografica.getClasificadoDewey());
            stmt.setString(3, fichaBibliografica.getEstanteria());
            stmt.setString(4, fichaBibliografica.getIdioma().name());
            stmt.setInt(5, fichaBibliografica.getId());
            int result = stmt.executeUpdate();

            if (result == 0) {
                throw new Exception("El update falló");
            }
        }

    }

    @Override
    public void actualizar(FichaBibliografica ficha) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            this.actualizar(ficha, conn);
        }
    }

    @Override
    public void eliminar(int id, Connection conn) throws Exception {

    }

    @Override
    public void eliminar(int id) throws Exception {
        // borrar la ficha y el libro asociado?
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public FichaBibliografica leer(int id, Connection conn) throws Exception {
        String sql = "SELECT id, isbn, clasificadoDewey, estanteria, idioma FROM fichaBibliografica WHERE id = ? AND eliminado = FALSE";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    FichaBibliografica fichaBibliografica = new FichaBibliografica();
                    fichaBibliografica.setId(rs.getInt("id"));
                    fichaBibliografica.setIsbn(rs.getString("isbn"));
                    fichaBibliografica.setClasificadoDewey(rs.getString("clasificadoDewey"));
                    fichaBibliografica.setEstanteria(rs.getString("estanteria"));
                    fichaBibliografica.setIdioma(Idioma.valueOf(rs.getString("idioma").toUpperCase()));

                    return fichaBibliografica;
                }
            }
        }

        return null;
    }

    @Override
    public FichaBibliografica leer(int id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return this.leer(id, conn);
        }
    }

    @Override
    public List<FichaBibliografica> leerTodos(Connection conn) throws Exception {
        List<FichaBibliografica> fichas = new ArrayList<>();

        String sql = "SELECT * FROM FichaBibliografica WHERE eliminado = FALSE";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    FichaBibliografica fichaBibliografica = new FichaBibliografica();
                    fichaBibliografica.setId(rs.getInt("id"));
                    fichaBibliografica.setIsbn(rs.getString("isbn"));
                    fichaBibliografica.setClasificadoDewey(rs.getString("clasificadoDewey"));
                    fichaBibliografica.setEstanteria(rs.getString("estanteria"));
                    fichaBibliografica.setIdioma(Idioma.valueOf(rs.getString("idioma").toUpperCase()));
                    fichas.add(fichaBibliografica);
                }
            }
        }

        return fichas;
    }

    @Override
    public List<FichaBibliografica> leerTodos() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return this.leerTodos(conn);
        }
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
