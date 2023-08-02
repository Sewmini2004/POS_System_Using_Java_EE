package lk.ijse.javaee.pos.service.custome;

import lk.ijse.javaee.pos.dto.CustomerDTO;
import lk.ijse.javaee.pos.dto.OrderDTO;
import lk.ijse.javaee.pos.service.SuperBO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    public boolean save(OrderDTO orderDTO, Connection connection) throws SQLException;
    public boolean delete(String id,Connection connection) throws SQLException;
    public boolean update(OrderDTO orderDTO,Connection connection) throws SQLException;
    public ArrayList<OrderDTO> getAll(Connection connection) throws SQLException;
    public OrderDTO search(String id, Connection connection) throws SQLException;
    public boolean isExist(String id,Connection connection) throws SQLException;
}
