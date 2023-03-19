package com.cherkasov.servlets;

import com.cherkasov.dto.StatsDTO;
import com.cherkasov.model.Product;
import com.cherkasov.service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "ShowProducts", value = "/stats")
public class ShowProducts extends HttpServlet {
    @Override
    public void init() {
        System.out.println(getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Service service;
        try {
            service = Service.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        final String id = request.getParameter("id");
        if (id != null) {
            Optional<Product> productById = service.getById(id);
            if (productById.isPresent()) {
                Product product = productById.get();
                request.setAttribute("product", product);
            }
            request.getRequestDispatcher("/showStatsById.jsp").forward(request, response);
        } else {
            List<String> allId = service.getAllId();
            request.setAttribute("allId", allId);
            StatsDTO stats = service.getStatistic();
            request.setAttribute("producedFuel", stats.getProducedFuel());
            request.setAttribute("usedFuel", stats.getUsedFuel());
            request.setAttribute("brokenMicrocircuits", stats.getBrokenMicrocircuits());
            request.setAttribute("countProducts", stats.getCountProducts());
            request.getRequestDispatcher("/showAllStats.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println(getServletName() + " destroyed");
    }
}
