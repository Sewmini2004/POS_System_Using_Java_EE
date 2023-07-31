package lk.ijse.javaee.pos.service.custome.imple;

import lk.ijse.javaee.pos.dao.DaoFactory;
import lk.ijse.javaee.pos.dao.custom.OrderDAO;
import lk.ijse.javaee.pos.dto.OrderDTO;
import lk.ijse.javaee.pos.entity.Customer;
import lk.ijse.javaee.pos.entity.OrderEntity;
import lk.ijse.javaee.pos.service.custome.OrderBO;
import lk.ijse.javaee.pos.service.util.Convert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderBOImple implements OrderBO {
   OrderDAO orderDAO =DaoFactory.getDaoFactory().getDao(DaoFactory.DaoTypes.ORDER);

    @Override
    public boolean save(OrderDTO orderDTO, Connection connection) throws SQLException {
        return orderDAO.save(Convert.toOrder(orderDTO),connection);
    }

    @Override
    public boolean delete(String id,Connection connection) throws SQLException {
        return orderDAO.delete(id,connection);
    }

    @Override
    public boolean update(OrderDTO orderDTO,Connection connection) throws SQLException {
        return orderDAO.update(Convert.toOrder(orderDTO),connection);
    }

    @Override
    public ArrayList<OrderDTO> getAll(Connection connection) throws SQLException {
        List<OrderEntity> all = orderDAO.getAll(connection);
        return all.stream().map(Convert::fromOrder).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public OrderDTO search(String id, Connection connection) throws SQLException {
        return Convert.fromOrder(orderDAO.search(id,connection));
    }

    @Override
    public boolean isExist(String id, Connection connection) throws SQLException {
        return orderDAO.isExist(id,connection);
    }
}
