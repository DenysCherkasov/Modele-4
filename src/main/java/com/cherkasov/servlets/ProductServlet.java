package com.cherkasov.servlets;


import com.cherkasov.model.Product;
import com.cherkasov.service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "create", value = "/start")
public class ProductServlet extends HttpServlet {
    @Override
    public void init() {
        System.out.println(getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getRequestDispatcher("create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Service service;
        Product product;
        try {
            service = Service.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            product = service.createProduct();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("product", product);
        request.getRequestDispatcher("showCreatedProduct.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        System.out.println(getServletName() + " destroyed");
    }
}
