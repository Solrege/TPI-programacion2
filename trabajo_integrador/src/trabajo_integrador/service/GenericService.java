package trabajo_integrador.service;

import java.util.List;

public interface GenericService <T> {
    boolean insertar(T entidad) throws Exception;
    boolean actualizar(T entidad) throws Exception;
    boolean eliminar(int id) throws Exception;
    T getById(int id) throws Exception;
    List<T> getAll() throws Exception;
}
