package com.smartbank.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactorySingleton {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;

    static {

            ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("SmartBank");

    }

    public static EntityManagerFactory entityManagerFactory() {
        return ENTITY_MANAGER_FACTORY;
    }
}
