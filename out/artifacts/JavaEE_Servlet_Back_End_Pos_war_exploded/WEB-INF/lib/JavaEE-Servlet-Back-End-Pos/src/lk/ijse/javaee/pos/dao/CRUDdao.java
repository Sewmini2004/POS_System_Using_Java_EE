package lk.ijse.javaee.pos.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CRUDdao<T> extends SuperDAO {
    boolean save(T t, Connection connection) throws SQLException;

    boolean update(T t, Connection connection) throws SQLException;

    boolean delete(String id, Connection connection) throws SQLException;

    T search(String id, Connection connection) throws SQLException;

    List<T> getAll(Connection connection) throws SQLException;

    String generateNextId(Connection connection) throws SQLException;

    boolean isExist(String id, Connection connection) throws SQLException;
}
