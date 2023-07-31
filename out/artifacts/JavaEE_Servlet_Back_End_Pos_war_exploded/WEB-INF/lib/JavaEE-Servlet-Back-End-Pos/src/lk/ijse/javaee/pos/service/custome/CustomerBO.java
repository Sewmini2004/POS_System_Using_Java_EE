package lk.ijse.javaee.pos.service.custome;

import lk.ijse.javaee.pos.dto.CustomerDTO;
import lk.ijse.javaee.pos.service.SuperBO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    public boolean save(CustomerDTO customerDTO, Connection connection) throws SQLException;
    public boolean delete(String id,Connection connection) throws SQLException;
    public boolean update(CustomerDTO customerDTO,Connection connection) throws SQLException;
    public CustomerDTO search(String id,Connection connection) throws SQLException;
    public boolean isExist(String id,Connection connection) throws SQLException;
    public ArrayList<CustomerDTO> getAll(Connection connection) throws SQLException;

}
