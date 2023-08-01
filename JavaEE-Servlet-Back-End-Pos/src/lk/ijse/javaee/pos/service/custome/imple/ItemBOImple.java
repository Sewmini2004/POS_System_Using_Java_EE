package lk.ijse.javaee.pos.service.custome.imple;

import lk.ijse.javaee.pos.dao.DaoFactory;
import lk.ijse.javaee.pos.dao.custom.ItemDAO;
import lk.ijse.javaee.pos.dto.CustomerDTO;
import lk.ijse.javaee.pos.dto.ItemDTO;
import lk.ijse.javaee.pos.entity.Customer;
import lk.ijse.javaee.pos.entity.ItemEntity;
import lk.ijse.javaee.pos.service.custome.ItemBO;
import lk.ijse.javaee.pos.service.util.Convert;

import javax.mail.FetchProfile;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemBOImple implements ItemBO {
    private final ItemDAO itemDAO = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoTypes.ITEM);

    @Override
    public boolean save(ItemDTO itemDTO, Connection connection) throws SQLException {
        return itemDAO.save(Convert.toItem(itemDTO),connection);
    }

    @Override
    public boolean delete(String id, Connection connection) throws SQLException {
        return itemDAO.delete(id,connection);
    }

    @Override
    public ItemDTO search(String id, Connection connection) throws SQLException {
        return Convert.fromItem(itemDAO.search(id,connection));
    }

    @Override
    public boolean isExist(String id, Connection connection) throws SQLException {
        return itemDAO.isExist(id,connection);
    }

    @Override
    public boolean update(ItemDTO itemDTO, Connection connection) throws SQLException {
        return itemDAO.update(Convert.toItem(itemDTO),connection);
    }

    @Override
    public ArrayList<ItemDTO> getAll(Connection connection) throws SQLException {
        List<ItemEntity> all = itemDAO.getAll(connection);
        return all.stream().map(Convert::fromItem).collect(Collectors.toCollection(ArrayList::new));
    }
}
