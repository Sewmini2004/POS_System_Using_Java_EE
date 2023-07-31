package lk.ijse.javaee.pos.dao.custom.impl;

import lk.ijse.javaee.pos.dao.custom.Order_DetailesDAO;
import lk.ijse.javaee.pos.entity.ItemEntity;
import lk.ijse.javaee.pos.entity.Order_DetailesEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Order_DetailesDAOImple implements Order_DetailesDAO {
    @Override
    public boolean save(Order_DetailesEntity order_detailesEntity, Connection connection) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("insert into order_details values (?,?,?,?,?,?)");
        statement.setObject(1, order_detailesEntity.getId());
        statement.setObject(2, order_detailesEntity.getOrder_id());
        statement.setObject(3, order_detailesEntity.getOrItemCOde());
        statement.setObject(4, order_detailesEntity.getOrItemQTY());
        statement.setObject(5, order_detailesEntity.getOrItemPrice());
        statement.setObject(6, order_detailesEntity.getOrItemTotal());
        if (statement.executeUpdate() > 0) {
            System.out.println("Saved");
            return true;
        } else {
            System.out.println("Saved Failed");
            return false;
        }
    }

    @Override
    public boolean update(Order_DetailesEntity order_detailesEntity, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("update order_details set order_id=?,item_code=?,qty=?,unit_price=?,total=? where id=?");
        statement.setObject(1, order_detailesEntity.getOrder_id());
        statement.setObject(2, order_detailesEntity.getOrItemCOde());
        statement.setObject(3, order_detailesEntity.getOrItemQTY());
        statement.setObject(4, order_detailesEntity.getOrItemPrice());
        statement.setObject(5, order_detailesEntity.getOrItemTotal());
        statement.setObject(6, order_detailesEntity.getId());
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
        PreparedStatement statement = connection.prepareStatement("DELETE FROM order_details WHERE id=?;");
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
    public Order_DetailesEntity search(String id, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from order_details where id=?");
        statement.setObject(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Order_DetailesEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5),
                    resultSet.getDouble(6)
            );
        }
        throw new SQLException("order details not found");
    }

    @Override
    public List<Order_DetailesEntity> getAll(Connection connection) throws SQLException {
        ResultSet resultSet = connection.prepareStatement("select * from order_details").executeQuery();
        List<Order_DetailesEntity> order_detailesEntities= new ArrayList<>();
        while (resultSet.next()) {
            order_detailesEntities.add(
                    new Order_DetailesEntity(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4),
                            resultSet.getInt(5),
                            resultSet.getDouble(6)
                    )
            );
        }
        return order_detailesEntities;
    }

    @Override
    public String generateNextId(Connection connection) throws SQLException {
        ResultSet resultSet = connection.prepareStatement("SELECT id FROM order_details ORDER BY id DESC limit 1").executeQuery();
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            int i = Integer.parseInt(lastId.replace("OD00-", "")) + 1;
            return String.format("OD00-%03d", i);
        } else {
            return "OD00-001";
        }
    }

    @Override
    public boolean isExist(String id, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from order_details where id=?");
        statement.setObject(1, id);
        return statement.executeQuery().next();
    }
}
