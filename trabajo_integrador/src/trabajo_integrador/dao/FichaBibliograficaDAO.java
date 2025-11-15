package trabajo_integrador.dao;

import trabajo_integrador.models.FichaBibliografica;
import trabajo_integrador.models.Idioma;
import trabajo_integrador.config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FichaBibliograficaDAO implements GenericDAO<FichaBibliografica> {

    @Override
    public void crear(FichaBibliografica fichaBibliografica, Connection conn) throws Exception {
        String sql = "INSERT INTO fichaBibliografica (isbn,clasificadoDewey, estanteria, idioma) VALUES (?,?,?,?) ";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, fichaBibliografica.getIsbn());
            stmt.setString(2, fichaBibliografica.getClasificadoDewey());
            stmt.setString(3, fichaBibliografica.getEstanteria());
            stmt.setString(4, fichaBibliografica.getIdioma().name());
            stmt.setInt(5, fichaBibliografica.getId());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    fichaBibliografica.setId(generatedKeys.getInt(1));
                    System.out.println("Ficha creada con ID: " + fichaBibliografica.getId());
                } else {
                    throw new Exception("La creación de la ficha falló. No se obtuvo el ID generado");
                }
            }
        }

    }
    //probar

    @Override
    public void crear(FichaBibliografica fichaBibliografica) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            this.crear(fichaBibliografica, conn);
        }
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
                throw new Exception("La actualizacion de la ficha " + fichaBibliografica.getId() + " falló.");
            }
        }

    }

    @Override
    public void actualizar(FichaBibliografica fichaBibliografica) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            this.actualizar(fichaBibliografica, conn);
        }
    }

    @Override
    public void eliminar(int id, Connection conn) throws Exception {
        String sql = "UPDATE fichaBibliografica SET eliminado = TRUE WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int result = stmt.executeUpdate();

            if (result == 0) {
                throw new Exception("La eliminación de la ficha " + id + " falló.");
            }
        }

    }

    @Override
    public void eliminar(int id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            this.eliminar(id, conn);
        }
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
        String sql = "UPDATE fichaBibliografica SET eliminado = FALSE WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int result = stmt.executeUpdate();

            if (result == 0) {
                throw new Exception("La recuperación de la ficha " + id + " falló.");
            }
        }

    }

    @Override
    public void recuperar(int id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            this.recuperar(id, conn);
        }
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
