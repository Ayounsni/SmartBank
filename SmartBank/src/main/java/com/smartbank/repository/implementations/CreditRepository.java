package com.smartbank.repository.implementations;

import com.smartbank.config.EntityManagerFactorySingleton;
import com.smartbank.models.entities.Credit;
import com.smartbank.repository.interfaces.ICreditRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;


public class CreditRepository implements ICreditRepository {

    @Override
    public Credit add(Credit credit) {
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
        return credit;
    }

}
