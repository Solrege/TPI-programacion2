package trabajo_integrador.dao;

import java.sql.Connection;
import java.util.List;

public interface GenericDAO<T> {

    void crear(T entidad, Connection conn) throws Exception;

    void crear(T entidad) throws Exception;

    T leer(int id, Connection conn) throws Exception;

    T leer(int id) throws Exception;

    List<T> leerTodos(Connection conn) throws Exception;

    List<T> leerTodos() throws Exception;

    void actualizar(T entidad, Connection conn) throws Exception;

    void actualizar(T entidad) throws Exception;

    void eliminar(int id, Connection conn) throws Exception;

    void eliminar(int id) throws Exception;
    //eliminar -- SET booleano de eliminado = true

    void recuperar(int id, Connection conn) throws Exception;

    void recuperar(int id) throws Exception;
    // set booleano en false 
}
