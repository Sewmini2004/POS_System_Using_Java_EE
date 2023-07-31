package lk.ijse.javaee.pos.dao.custom.impl;

import lk.ijse.javaee.pos.dao.custom.ItemDAO;
import lk.ijse.javaee.pos.entity.Customer;
import lk.ijse.javaee.pos.entity.ItemEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImple implements ItemDAO {
    @Override
    public boolean save(ItemEntity itemEntity, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("insert into item values (?,?,?,?)");
        statement.setObject(1, itemEntity.getItemCode());
        statement.setObject(2, itemEntity.getItemName());
        statement.setObject(3, itemEntity.getQtyOnHand());
        statement.setObject(4, itemEntity.getItemPrice());
        if (statement.executeUpdate() > 0) {
            System.out.println("Saved");
            return true;
        } else {
            System.out.println("Save Failed");
            return false;
        }
    }

    @Override
    public boolean update(ItemEntity itemEntity, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("update item set name=?,qty=?,unit_price=? where code=? ");
        statement.setObject(1, itemEntity.getItemName());
        statement.setObject(2, itemEntity.getQtyOnHand());
        statement.setObject(3, itemEntity.getItemPrice());
        statement.setObject(4, itemEntity.getItemCode());
        if (statement.executeUpdate() > 0) {
            System.out.println("Updated");
            return true;
        } else {
            System.out.println("Update Failed");
            return false;
        }
    }

    @Override
    public boolean delete(String id, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM item WHERE code=?;");
        statement.setObject(1, id);
        if (statement.executeUpdate() > 0) {
            System.out.println("Deleted");
            return true;
        } else {
            System.out.println("Delete Failed");
            return false;
        }
    }

    @Override
    public ItemEntity search(String id, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from item where code=?");
        statement.setObject(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new ItemEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)
            );
        }
        throw new SQLException("Item not found");
    }

    @Override
    public List<ItemEntity> getAll(Connection connection) throws SQLException {
        ResultSet resultSet = connection.prepareStatement("select * from item").executeQuery();
        List<ItemEntity> items= new ArrayList<>();
        while (resultSet.next()) {
           items.add(
                    new ItemEntity(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getDouble(4)
                    )
            );
        }
        return items;
    }

    @Override
    public String generateNextId(Connection connection) throws SQLException {
        ResultSet resultSet = connection.prepareStatement("SELECT code FROM item ORDER BY code DESC limit 1").executeQuery();
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            int i = Integer.parseInt(lastId.replace("I00-", "")) + 1;
            return String.format("I00-%03d", i);
        } else {
            return "I00-001";
        }
    }

    @Override
    public boolean isExist(String id, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from item where code=?");
        statement.setObject(1, id);
        return statement.executeQuery().next();
    }
}
