package lk.ijse.javaee.pos.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.javaee.pos.dto.CustomerDTO;
import lk.ijse.javaee.pos.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.annotation.Resource;
import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class CustomerController extends HttpServlet {
    @Resource(name = "")
    DataSource dataSource;
    Jsonb jsonb = JsonbBuilder.create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//        Mdal mapper
//        dn uda thynne customer dto obj ekk manika hri ek entity ekta Customer kyn entity ekt mnodal mapper eken mem convert krnn pluwm
        try {
            resp.setContentType("application/json");
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            ModelMapper modelMapper = new ModelMapper();
            Customer map = modelMapper.map(customerDTO, Customer.class);
            Connection connection = dataSource.getConnection();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
