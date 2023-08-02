package lk.ijse.javaee.pos.service.custome;

import lk.ijse.javaee.pos.dto.CustomerDTO;
import lk.ijse.javaee.pos.dto.ItemDTO;
import lk.ijse.javaee.pos.service.SuperBO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    public boolean save(ItemDTO itemDTO, Connection connection) throws SQLException;
    public boolean delete(String code,Connection connection) throws SQLException;
    public boolean update(ItemDTO itemDTO,Connection connection) throws SQLException;
    public ArrayList<ItemDTO> getAll(Connection connection) throws SQLException;
    public ItemDTO search(String id, Connection connection) throws SQLException;
    public boolean isExist(String id,Connection connection) throws SQLException;
}
