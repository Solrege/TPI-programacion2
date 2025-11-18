package trabajo_integrador.dao;

import trabajo_integrador.models.Idioma;
import trabajo_integrador.models.Libro;
import trabajo_integrador.config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import trabajo_integrador.models.FichaBibliografica;

public class LibroDAO implements GenericDAO<Libro> {

    @Override
    public void crear(Libro libro, Connection conn) throws Exception {
        String sql = "INSERT INTO libro (titulo, autor, editorial, anio_edicion, id_ficha) VALUES (?,?,?,?,?) ";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getEditorial());
            stmt.setInt(4, libro.getAnioEdicion());
            if (libro.getFicha() != null && libro.getFicha().getId() > 0) {
                stmt.setInt(5, libro.getFicha().getId());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    libro.setId(generatedKeys.getInt(1));
                    System.out.println("Libro creado con ID: " + libro.getId());
                } else {
                    throw new Exception("La creación del libro falló. No se obtuvo el ID generado");
                }
            }
        }

    }

    @Override
    public void crear(Libro libro) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            this.crear(libro, conn);
        }
    }

    @Override
    public void actualizar(Libro libro, Connection conn) throws Exception {
        String sql = "UPDATE libro SET titulo = ?, autor = ?, editorial = ?, anio_edicion = ?, id_ficha = ? WHERE id_libro = ? AND eliminado = FALSE";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getEditorial());
            stmt.setInt(4, libro.getAnioEdicion());
            if (libro.getFicha() != null && libro.getFicha().getId() > 0) {
                stmt.setInt(5, libro.getFicha().getId());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }
            stmt.setInt(6, libro.getId());

            int result = stmt.executeUpdate();

            if (result == 0) {
                throw new Exception("La actualizacion del libro " + libro.getId() + " falló.");
            }
        }

    }

    @Override
    public void actualizar(Libro libro) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            this.actualizar(libro, conn);
        }
    }

    @Override
    public void eliminar(int id, Connection conn) throws Exception {
        String sql = "UPDATE libro SET eliminado = TRUE WHERE id_libro = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int result = stmt.executeUpdate();

            if (result == 0) {
                throw new Exception("La eliminación del libro " + id + " falló.");
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
    public Libro leer(int id, Connection conn) throws Exception {
        String sql = "SELECT l.id_libro, l.titulo, l.autor, l.editorial, l.anio_edicion, f.id AS ficha_id, f.estanteria, f.isbn, f.clasificacion_dewey, f.idioma FROM libro l LEFT JOIN ficha_bibliografica f ON l.id_ficha = f.id WHERE l.id_libro = ? AND l.eliminado = FALSE";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Libro libro = new Libro();
                    libro.setId(rs.getInt("id_libro"));
                    libro.setTitulo(rs.getString("titulo"));
                    libro.setAutor(rs.getString("autor"));
                    libro.setEditorial(rs.getString("editorial"));
                    libro.setAnioEdicion(rs.getInt("anio_edicion"));

                    int fichaId = rs.getInt("ficha_id");
                    if (fichaId > 0 && !rs.wasNull()) {
                        FichaBibliografica ficha = new FichaBibliografica();
                        ficha.setId(rs.getInt("ficha_id"));
                        ficha.setEstanteria(rs.getString("estanteria"));
                        ficha.setIsbn(rs.getString("isbn"));
                        ficha.setClasificacionDewey(rs.getString("clasificacion_dewey"));
                        ficha.setIdioma(Idioma.valueOf(rs.getString("idioma")));

                        libro.setFicha(ficha);
                    }

                    return libro;
                }
            }
        }

        return null;
    }

    @Override
    public Libro leer(int id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return this.leer(id, conn);
        }
    }

    @Override
    public List<Libro> leerTodos(Connection conn) throws Exception {
        List<Libro> libros = new ArrayList<>();

        String sql = "SELECT l.id_libro, l.titulo, l.autor, l.editorial, l.anio_edicion, f.id AS ficha_id, f.estanteria, f.isbn FROM libro l LEFT JOIN ficha_bibliografica f ON l.id_ficha = f.id WHERE l.eliminado = FALSE";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Libro libro = new Libro();
                    libro.setId(rs.getInt("id_libro"));
                    libro.setTitulo(rs.getString("titulo"));
                    libro.setAutor(rs.getString("autor"));
                    libro.setEditorial(rs.getString("editorial"));
                    libro.setAnioEdicion(rs.getInt("anio_edicion"));

                    int fichaId = rs.getInt("ficha_id");
                    if (!rs.wasNull()) {
                        FichaBibliografica ficha = new FichaBibliografica();
                        ficha.setId(fichaId);
                        ficha.setEstanteria(rs.getString("estanteria"));
                        ficha.setIsbn(rs.getString("isbn"));
                        
                        libro.setFicha(ficha);
                    }
                    libros.add(libro);
                }
            }

            
        }
        return libros;
    }

    @Override
    public List<Libro> leerTodos() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return this.leerTodos(conn);
        }
    }

    @Override
    public void recuperar(int id, Connection conn) throws Exception {
        String sql = "UPDATE libro SET eliminado = FALSE WHERE id_libro = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int result = stmt.executeUpdate();

            if (result == 0) {
                throw new Exception("La eliminación del libro " + id + " falló.");
            }
        }

    }

    @Override
    public void recuperar(int id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            this.recuperar(id, conn);
        }
    }

}
