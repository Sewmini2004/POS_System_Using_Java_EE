package lk.ijse.javaee.pos.service.custome.imple;

import lk.ijse.javaee.pos.dao.DaoFactory;
import lk.ijse.javaee.pos.dao.custom.Order_DetailesDAO;
import lk.ijse.javaee.pos.dto.Order_DetailsDTO;
import lk.ijse.javaee.pos.entity.Customer;
import lk.ijse.javaee.pos.entity.Order_DetailesEntity;
import lk.ijse.javaee.pos.service.custome.Order_DtailesBO;
import lk.ijse.javaee.pos.service.util.Convert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Order_DetailesBOImple implements Order_DtailesBO {
    private final Order_DetailesDAO order_detailesDAO = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoTypes.ORDER_DETAILS);

    @Override
    public boolean save(Order_DetailsDTO order_detailsDTO, Connection connection) throws SQLException {
        return order_detailesDAO.save(Convert.toOrder_Detailes(order_detailsDTO),connection);
    }

    @Override
    public boolean delete(String id, Connection connection) throws SQLException {
        return order_detailesDAO.delete(id,connection);
    }

    @Override
    public Order_DetailsDTO search(String id, Connection connection) throws SQLException {
        return Convert.fromOrder_Detailes(order_detailesDAO.search(id,connection));
    }

    @Override
    public boolean isExist(String id, Connection connection) throws SQLException {
        return order_detailesDAO.isExist(id,connection);
    }

    @Override
    public boolean update(Order_DetailsDTO order_detailsDTO, Connection connection) throws SQLException {
        return order_detailesDAO.update(Convert.toOrder_Detailes(order_detailsDTO),connection);
    }

    @Override
    public ArrayList<Order_DetailsDTO> getAll(Connection connection) throws SQLException {
        List<Order_DetailesEntity> all = order_detailesDAO.getAll(connection);
        return all.stream().map(Convert::fromOrder_Detailes).collect(Collectors.toCollection(ArrayList::new));
    }
}
