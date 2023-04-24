package com.cherkasov.repository;

import com.cherkasov.dto.StatsDTO;
import com.cherkasov.model.Product;
import com.cherkasov.utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class Repository {
    private static Repository instance;

    private Repository() {
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public void save(Product product) {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Optional<Product> getById(String id) {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        return Optional.ofNullable(entityManager.find(Product.class, id));
    }

    public StatsDTO getStatistic() {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        return entityManager.createQuery(
                        "SELECT new com.cherkasov.dto.StatsDTO " +
                                "(COUNT (d.id), SUM (d.brokenMicrocircuits), " +
                                "SUM (d.producedFuel), SUM (d.usedFuel)) " +
                                "FROM Product d", StatsDTO.class)
                .getSingleResult();
    }

    public List<String> getAllId() {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        return entityManager.createQuery(
                        "SELECT  d.id FROM Product d", String.class)
                .getResultList();
    }

}
