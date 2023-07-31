package lk.ijse.javaee.pos.service.custome.imple;

import lk.ijse.javaee.pos.dao.DaoFactory;
import lk.ijse.javaee.pos.dao.custom.CustomerDAO;
import lk.ijse.javaee.pos.dto.CustomerDTO;
import lk.ijse.javaee.pos.entity.Customer;
import lk.ijse.javaee.pos.service.custome.CustomerBO;
import lk.ijse.javaee.pos.service.util.Convert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerBOImple implements CustomerBO {
  private final  CustomerDAO customerDAO=DaoFactory.getDaoFactory().getDao(DaoFactory.DaoTypes.CUSTOMER);

    @Override
    public boolean save(CustomerDTO customerDTO, Connection connection) throws SQLException {
        return customerDAO.save(Convert.toCustomer(customerDTO),connection);
    }

    @Override
    public boolean delete(String id, Connection connection) throws SQLException {
       return customerDAO.delete(id,connection);
    }

    @Override
    public CustomerDTO search(String id, Connection connection) throws SQLException {
        return Convert.fromCutomer(customerDAO.search(id,connection));
    }

    @Override
    public boolean isExist(String id, Connection connection) throws SQLException {
        return customerDAO.isExist(id,connection);
    }

    @Override
    public boolean update(CustomerDTO customerDTO, Connection connection) throws SQLException {
        return customerDAO.update(Convert.toCustomer(customerDTO),connection);
    }

    @Override
    public ArrayList<CustomerDTO> getAll(Connection connection) throws SQLException {
        List<Customer> all = customerDAO.getAll(connection);
        return all.stream().map(Convert::fromCutomer).collect(Collectors.toCollection(ArrayList::new));
    }

}
