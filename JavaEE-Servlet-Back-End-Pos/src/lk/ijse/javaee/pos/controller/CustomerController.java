package lk.ijse.javaee.pos.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.javaee.pos.dto.CustomerDTO;

import javax.annotation.Resource;
import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

public class CustomerController extends HttpServlet {
    @Resource(name = "")
    DataSource dataSource;
    Jsonb jsonb = JsonbBuilder.create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);

    }
}
