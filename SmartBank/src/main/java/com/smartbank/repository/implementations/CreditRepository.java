package com.smartbank.repository.implementations;

import com.smartbank.config.EntityManagerFactorySingleton;
import com.smartbank.models.entities.Credit;
import com.smartbank.repository.interfaces.ICreditRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;


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
    @Override
    public List<Credit> findAllCredits() {
        EntityManager em = EntityManagerFactorySingleton.entityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        List<Credit> credits = null;

        try {
            transaction.begin();
            credits = em.createQuery("SELECT c FROM Credit c",Credit.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }

        return credits;
    }

}
