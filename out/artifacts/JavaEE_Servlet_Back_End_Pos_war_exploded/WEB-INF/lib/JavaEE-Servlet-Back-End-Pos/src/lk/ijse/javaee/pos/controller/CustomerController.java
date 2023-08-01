package lk.ijse.javaee.pos.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.javaee.pos.Response;
import lk.ijse.javaee.pos.dto.CustomerDTO;
import lk.ijse.javaee.pos.entity.Customer;
import lk.ijse.javaee.pos.service.ServiceFactory;
import lk.ijse.javaee.pos.service.custome.CustomerBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;

import javax.annotation.Resource;
import javax.json.Json;
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

@WebServlet("/customer")
public class CustomerController extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    DataSource dataSource;
    CustomerBO customerBO = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.CUSTOMER);
    Jsonb jsonb = JsonbBuilder.create();
    private Connection connection;

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("customer get method called");
        try {
            connection = dataSource.getConnection();
            ArrayList<CustomerDTO> all = customerBO.getAll(connection);
            Response response = new Response(200, "Successfully", all);
            String responseJsonObject = jsonb.toJson(response);
            resp.setContentType("application/json");
            resp.setStatus(200);
            resp.getWriter().write(responseJsonObject);

        } catch (SQLException throwables) {
            throwables.printStackTrace();

            Response response = new Response(404, "error", null);
            String s = jsonb.toJson(response);

            resp.setStatus(400);

            resp.getWriter().write(s);
        } finally {
            connection.close();
        }

    }


    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("customer post method called");
        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);

        try {
            connection = dataSource.getConnection();

            boolean save = customerBO.save(customerDTO, connection);
            if (save) {

                Response response = new Response(200, "Customer saved", null);

//                java obj -> json
                String jsonResponse = jsonb.toJson(response);

//                set response headers
                resp.setContentType("application/json");
                resp.setStatus(200);

//                Send response to front end
                resp.getWriter().write(jsonResponse);

            } else {
                Response response = new Response(404, "error", null);
                String s = jsonb.toJson(response);
                resp.setStatus(400);
                resp.getWriter().write(s);
//                 mn mek hdnkm pddk manika klpn krno ar tk nkm mthk krno pana watch ek dev tool wla ewngen mon wge wdada krnn pluwm ema ko
            }
        } catch (SQLException throwables) {//mokd une dn nee router ekt mru wenn haa
            throwables.printStackTrace();

            Response response = new Response(500, "error", throwables.getLocalizedMessage());


            String s = jsonb.toJson(response);
            resp.setStatus(500);
            resp.getWriter().write(s);

        } finally {
            connection.close();
        }


    }

    @SneakyThrows
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);

        try {
            connection = dataSource.getConnection();
            boolean update = customerBO.update(customerDTO, connection);
            if (update) {
                Response response = new Response(200, "Customer  Updated Successfully", null);
                String s = jsonb.toJson(response);
                resp.setContentType("application/json");
                resp.setStatus(200);
                resp.getWriter().write(s);
            } else {

                Response response = new Response(404, "error", null);
                String s = jsonb.toJson(response);
                resp.setStatus(404);
                resp.getWriter().write(s);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Response response = new Response(500, "error", throwables.getLocalizedMessage());
            String s = jsonb.toJson(response);
            resp.setStatus(500);
            resp.getWriter().write(s);

        } finally {
            connection.close();
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerId = req.getParameter("customerId");

        try {
            Connection connection = dataSource.getConnection();
            boolean delete = customerBO.delete(customerId, connection);
            if (delete) {

                Response response = new Response(200, "Successfully", null);
                String s = jsonb.toJson(response);
                resp.setContentType("application/json");
                resp.setStatus(200);
                resp.getWriter().write(s);
            } else {

                Response response = new Response(404, "error", null);
                String s = jsonb.toJson(response);
                resp.setStatus(404);
                resp.getWriter().write(s);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throwables.printStackTrace();
            Response response = new Response(500, "error", throwables.getLocalizedMessage());
            String s = jsonb.toJson(response);
            resp.setStatus(500);
            resp.getWriter().write(s);
        }
    }
}
