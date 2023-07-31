package lk.ijse.javaee.pos.controller;

import lk.ijse.javaee.pos.Response;
import lk.ijse.javaee.pos.dao.custom.ItemDAO;
import lk.ijse.javaee.pos.dto.ItemDTO;
import lk.ijse.javaee.pos.service.ServiceFactory;
import lk.ijse.javaee.pos.service.custome.ItemBO;
import lombok.SneakyThrows;

import javax.annotation.Resource;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/item")
public class ItemController extends HttpServlet {
    @Resource(name ="java:comp/env/jdbc/pool")
    DataSource dataSource;
    private Connection connection;
    Jsonb jsonb= JsonbBuilder.create();
    ItemBO itemBO =ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.ITEM);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);
        try {
            Connection connection = dataSource.getConnection();
            boolean save = itemBO.save(itemDTO, connection);
            if (save){
                Response response = new Response(200,"Successfully",null);
                String s = jsonb.toJson(response);
                resp.setContentType("application/json");
                resp.setStatus(200);
                resp.getWriter().write(s);
            }else {
                Response response = new Response(404,"Error",null);
                String s = jsonb.toJson(response);
                resp.setStatus(404);
                resp.getWriter().write(s);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Response response = new Response(500,"Error",throwables.getLocalizedMessage());
            String s = jsonb.toJson(response);
            resp.setStatus(500);
            resp.getWriter().write(s);
        }finally {
            connection.close();
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
