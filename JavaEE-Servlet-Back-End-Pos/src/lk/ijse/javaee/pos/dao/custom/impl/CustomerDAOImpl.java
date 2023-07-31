package lk.ijse.javaee.pos.dao.custom.impl;

import lk.ijse.javaee.pos.dao.custom.CustomerDAO;
import lk.ijse.javaee.pos.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(Customer customer, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("insert into customer values (?,?,?,?)");
        statement.setObject(1, customer.getCusId());
        statement.setObject(2, customer.getCusName());
        statement.setObject(3, customer.getCusAddress());
        statement.setObject(4, customer.getCusSalary());
        if (statement.executeUpdate() > 0) {
            System.out.println("Saved");
            return true;
        } else {
            System.out.println("Saved Failed");
            return false;
        }
    }

    @Override
    public boolean update(Customer customer, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("update customer set name=?,address=?,salary=? where id=? ");
        statement.setObject(1, customer.getCusName());
        statement.setObject(2, customer.getCusAddress());
        statement.setObject(3, customer.getCusSalary());
        statement.setObject(4, customer.getCusId());
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
        PreparedStatement statement = connection.prepareStatement("DELETE FROM customer WHERE id=?");
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
    public Customer search(String id, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from customer where id=?");
        statement.setObject(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
        }
        throw new SQLException("Customer not found");
    }

    @Override
    public List<Customer> getAll(Connection connection) throws SQLException {
        ResultSet resultSet = connection.prepareStatement("select * from customer").executeQuery();
        List<Customer> customers = new ArrayList<>();
        while (resultSet.next()) {
            customers.add(
                    new Customer(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4)
                    )
            );
        }
        return customers;
    }

    @Override
    public String generateNextId(Connection connection) throws SQLException {
        ResultSet resultSet = connection.prepareStatement("SELECT id FROM customer ORDER BY id DESC limit 1").executeQuery();
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            int i = Integer.parseInt(lastId.replace("C00-", "")) + 1;
            return String.format("C00-%03d", i);
        } else {
            return "C00-001";
        }
    }

    @Override
    public boolean isExist(String id, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from customer where id=?");
        statement.setObject(1, id);
        return statement.executeQuery().next();
    }
}
