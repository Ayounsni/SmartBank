package com.smartbank.repository.interfaces;

import com.smartbank.config.EntityManagerFactorySingleton;
import com.smartbank.models.entities.Credit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;


public class CreditRepository {

    public void add(Credit credit) {
        EntityManager em = EntityManagerFactorySingleton.entityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(credit);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

}
