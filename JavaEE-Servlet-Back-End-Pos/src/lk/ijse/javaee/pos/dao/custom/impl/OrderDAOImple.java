package lk.ijse.javaee.pos.dao.custom.impl;

import lk.ijse.javaee.pos.dao.custom.OrderDAO;
import lk.ijse.javaee.pos.entity.ItemEntity;
import lk.ijse.javaee.pos.entity.OrderEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImple implements OrderDAO {
    @Override
    public boolean save(OrderEntity orderEntity, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("insert into `order` values (?,?,?,?,?)");
        statement.setObject(1, orderEntity.getOrId());
        statement.setObject(2, orderEntity.getOrcustomer_id());
        statement.setObject(3, orderEntity.getOrDate());
        statement.setObject(4, orderEntity.getOrDis());
        statement.setObject(5, orderEntity.getOrSubTotal());
        if (statement.executeUpdate() > 0) {
            System.out.println("Saved");
            return true;
        } else {
            System.out.println("Saved Failed");
            return false;
        }
    }


    @Override
    public boolean update(OrderEntity orderEntity, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("update `order` set customer_id=?,date=?,discount=?,sub_total where id=? ");
        statement.setObject(1, orderEntity.getOrcustomer_id());
        statement.setObject(2, orderEntity.getOrDate());
        statement.setObject(3, orderEntity.getOrDis());
        statement.setObject(4, orderEntity.getOrSubTotal());
        statement.setObject(5, orderEntity.getOrId());
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
        PreparedStatement statement = connection.prepareStatement("DELETE FROM `order` WHERE code=?;");
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
    public OrderEntity search(String id, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from `order` where code=?");
        statement.setObject(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new OrderEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5)
            );
        }
        throw new SQLException("Order not found");
    }

    @Override
    public List<OrderEntity> getAll(Connection connection) throws SQLException {
        ResultSet resultSet = connection.prepareStatement("select * from `order`").executeQuery();
        List<OrderEntity> orders = new ArrayList<>();
        while (resultSet.next()) {
            orders.add(
                    new OrderEntity(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getDate(3),
                            resultSet.getDouble(4),
                            resultSet.getDouble(5)

                    )
            );
        }
        return orders;
    }

    @Override
    public String generateNextId(Connection connection) throws SQLException {
        ResultSet resultSet = connection.prepareStatement("SELECT id FROM `order` ORDER BY id DESC limit 1").executeQuery();
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            int i = Integer.parseInt(lastId.replace("O00-", "")) + 1;
            return String.format("O00-%03d", i);
        } else {
            return "O00-001";
        }
    }

    @Override
    public boolean isExist(String id, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from `order` where id=?");
        statement.setObject(1, id);
        return statement.executeQuery().next();
    }

}
