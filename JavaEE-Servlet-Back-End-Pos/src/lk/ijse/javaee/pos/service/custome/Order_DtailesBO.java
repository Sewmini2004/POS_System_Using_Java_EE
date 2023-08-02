package lk.ijse.javaee.pos.service.custome;

import lk.ijse.javaee.pos.dto.CustomerDTO;
import lk.ijse.javaee.pos.dto.Order_DetailsDTO;
import lk.ijse.javaee.pos.service.SuperBO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Order_DtailesBO extends SuperBO {
    public boolean save(Order_DetailsDTO order_detailsDTO, Connection connection) throws SQLException;
    public boolean delete(String id,Connection connection) throws SQLException;
    public boolean update(Order_DetailsDTO order_detailsDTO,Connection connection) throws SQLException;
    public ArrayList<Order_DetailsDTO> getAll(Connection connection) throws SQLException;
    public Order_DetailsDTO search(String id, Connection connection) throws SQLException;
    public boolean isExist(String id,Connection connection) throws SQLException;
}
