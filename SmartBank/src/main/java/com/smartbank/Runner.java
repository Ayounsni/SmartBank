package com.smartbank;

import com.smartbank.config.EntityManagerFactorySingleton;
import jakarta.persistence.EntityManagerFactory;

public class Runner {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.entityManagerFactory();

    }
}
