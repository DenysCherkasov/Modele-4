package com.cherkasov.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

public class HibernateUtil {
    private static EntityManagerFactory managerFactory;

    public static EntityManager getEntityManager() {
        return Optional.ofNullable(managerFactory)
                .or(() -> {
                    managerFactory = Persistence
                            .createEntityManagerFactory("persistence");
                    return Optional.of(managerFactory);
                })
                .map(EntityManagerFactory::createEntityManager)
                .orElseThrow();
    }

}
