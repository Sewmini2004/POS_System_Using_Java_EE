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
//error ek kywl blnn mkdd krnnpluwm deyk thid kyla pana thme tinne ara klin ekelu enm hdnnko ela
@WebServlet("/customer")
public class CustomerController extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    DataSource dataSource;
    CustomerBO customerBO = ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceType.CUSTOMER);
    Jsonb jsonb = JsonbBuilder.create();
    private Connection connection;
//wetil chu dla enn awa dn api ar gypu data tkem front end eke krnn thyen tk blmu manika

//    me tk thynnd manika dn item eke post ek hdnn me wge dendoo athi neh mokk ai aa rthkot eekewth oye resource kiyn ek danod ow neh ow dno manika ekenne api connection ek hdg hri hri
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("customer get method called");
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

                Response response = new Response(200,"Customer saved", null);

//                java obj -> json
                String jsonResponse = jsonb.toJson(response);
//                me hdnne ywnnona data ek json krn ek


//                set response headers
                resp.setContentType("application/json");
                resp.setStatus(200);


//                Send response to front end
                resp.getWriter().write(jsonResponse);

            } else {
                Response response = new Response(404,"error",null);
                String s = jsonb.toJson(response);

                resp.setStatus(400);

                resp.getWriter().write(s);


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

            Response response = new Response(500,"error",throwables.getLocalizedMessage());


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
