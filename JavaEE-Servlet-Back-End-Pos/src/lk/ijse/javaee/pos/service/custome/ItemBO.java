package lk.ijse.javaee.pos.service.custome;

import lk.ijse.javaee.pos.dto.CustomerDTO;
import lk.ijse.javaee.pos.dto.ItemDTO;
import lk.ijse.javaee.pos.service.SuperBO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
//mn manika mek ht 12 wenn klim hdno okkom ad welwa gttu elkta sory oi thmuse mon krnnd bn mtne ghnn oni ;; em nee mnne mek gdak mg ko awd
public interface ItemBO extends SuperBO {
    public boolean save(ItemDTO itemDTO, Connection connection) throws SQLException;
    public boolean delete(String code,Connection connection) throws SQLException;
    public boolean update(ItemDTO itemDTO,Connection connection) throws SQLException;
    public ArrayList<ItemDTO> getAll(Connection connection) throws SQLException;
    public ItemDTO search(String id, Connection connection) throws SQLException;
    public boolean isExist(String id,Connection connection) throws SQLException;
}
