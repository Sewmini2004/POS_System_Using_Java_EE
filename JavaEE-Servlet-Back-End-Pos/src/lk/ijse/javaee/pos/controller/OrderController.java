package lk.ijse.javaee.pos.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.javaee.pos.Response;
import lk.ijse.javaee.pos.dto.OrderDTO;
import lk.ijse.javaee.pos.dto.Order_DetailsDTO;
import lk.ijse.javaee.pos.service.ServiceFactory;
import lk.ijse.javaee.pos.service.custome.CustomerBO;
import lk.ijse.javaee.pos.service.custome.ItemBO;
import lk.ijse.javaee.pos.service.custome.OrderBO;
import lk.ijse.javaee.pos.service.custome.Order_DtailesBO;
import lombok.SneakyThrows;
import org.eclipse.yasson.YassonConfig;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

@WebServlet("/order")
public class OrderController extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    DataSource dataSource;
    private Connection connection;
    Jsonb jsonb = JsonbBuilder.create(new YassonConfig().setProperty(YassonConfig.ZERO_TIME_PARSE_DEFAULTING, true));

    OrderBO orderBO = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.ORDER);
    Order_DtailesBO detailsBO = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.ORDER_DETAILS);
    ItemBO itemBO = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.ITEM);
    CustomerBO customerBO = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.CUSTOMER);

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderDTO orderDTO = jsonb.fromJson(req.getReader(), OrderDTO.class);
        System.out.println(orderDTO);
        resp.setStatus(HttpServletResponse.SC_OK);

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            boolean save = orderBO.save(orderDTO, connection);
            if (save) {
                for (Order_DetailsDTO dto : orderDTO.getOrderDetails()) {
                    dto.setOrder_id(orderDTO.getOrId());
                    boolean isSavedDetails = detailsBO.save(dto, connection);
                    if (!isSavedDetails) {
                        connection.rollback();
                        return;
                    }
                }
                connection.commit();
                resp.getWriter().write(jsonb.toJson(new Response(200, "Order placed", null)));
            } else {
                connection.rollback();
                resp.getWriter().write(jsonb.toJson(new Response(400, "Order placed failed", null)));
            }
        } catch (SQLException throwables) {
            connection.rollback();
            resp.getWriter().write(jsonb.toJson(new Response(500, "Order placed failed", throwables.getLocalizedMessage())));
            throwables.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Order get method called");

        try {
            connection = dataSource.getConnection();
            ArrayList<OrderDTO> all = orderBO.getAll(connection);
            ArrayList<Order_DetailsDTO> detailsAll = detailsBO.getAll(connection);
            for (OrderDTO dto : all) {
                ArrayList<Order_DetailsDTO> collect = detailsAll.stream().filter(order_detailsDTO -> order_detailsDTO.getOrder_id() == dto.getOrId()).collect(Collectors.toCollection(ArrayList::new));
                dto.setOrderDetails(collect);
            }
            resp.getWriter().write(jsonb.toJson(new Response(200,"All orders",all)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.getWriter().write(jsonb.toJson(new Response(500,"Error",throwables.getLocalizedMessage())));
        }finally {
            connection.close();
        }

        System.out.println("Order get method ended");
    }
}
