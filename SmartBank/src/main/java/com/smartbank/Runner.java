package com.smartbank;

import com.smartbank.config.EntityManagerFactorySingleton;
import com.smartbank.models.entities.Status;
import com.smartbank.repository.implementations.StatusRepository;
import com.smartbank.repository.interfaces.IStatusRepository;
import jakarta.persistence.EntityManagerFactory;

public class Runner {
    public static void main(String[] args) {
//        EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.entityManagerFactory();

        StatusRepository statusRepository = new StatusRepository();
        Status status = new Status();
        status.setNom("Rejeter");
        statusRepository.persist(status);






    }
}
