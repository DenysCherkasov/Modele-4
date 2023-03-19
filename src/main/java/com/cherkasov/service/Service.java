package com.cherkasov.service;

import com.cherkasov.dto.StatsDTO;
import com.cherkasov.factory.ProductFactory;
import com.cherkasov.model.Product;
import com.cherkasov.repository.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Service {
    private final Repository repository;
    private static Service instance;

    private Service(final Repository repository) {
        this.repository = repository;
    }

    public static Service getInstance() throws SQLException {
        if (instance == null) {
            instance = new Service(Repository.getInstance());
        }
        return instance;
    }

    public Product createProduct() throws InterruptedException {
        ProductFactory factory = new ProductFactory();
        Product product = factory.createProduct();
        repository.save(product);
        return product;
    }

    public Optional<Product> getById(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Invalid id");
        } else {
            return repository.getById(id);
        }
    }

    public StatsDTO getStatistic() {
        return repository.getStatistic();
    }

    public List<String> getAllId() {
        return repository.getAllId();
    }



    }
