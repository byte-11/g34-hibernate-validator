package uz.pdp.g34hibernatevalidator.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DatasourceManager {
    private static final EntityManagerFactory MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate-persistence");
    public static final EntityManager ENTITY_MANAGER = MANAGER_FACTORY.createEntityManager();
}
