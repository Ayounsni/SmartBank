package com.smartbank.repository.implementations;

import com.smartbank.models.entities.Credit;
import com.smartbank.models.entities.Status;
import com.smartbank.repository.interfaces.ICreditRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.List;


@ApplicationScoped
public class CreditRepository extends GenericRepository<Credit> implements ICreditRepository {

    public CreditRepository() {
        super(Credit.class);
    }
    @Override
    public List<Credit> findCreditsByStatusAndDate(Status status, LocalDate date){
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        List<Credit> credit = null;

        try {
            transaction.begin();
            String query = "SELECT cs.credit FROM CreditStatus cs WHERE cs.status = :status AND cs.credit.dateCreation = :date AND cs.actif = true";
            credit = em.createQuery(query, Credit.class)
                    .setParameter("status", status)
                    .setParameter("date", date)
                    .getResultList();
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
    public List<Credit> findCreditsByDate(LocalDate date){
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        List<Credit> credit = null;

        try {
            transaction.begin();
            String query = "SELECT c FROM Credit c WHERE c.dateCreation = :date";
            credit = em.createQuery(query, Credit.class)
                    .setParameter("date", date)
                    .getResultList();
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
    public List<Credit> findCreditsByStatus(Status status){
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        List<Credit> credit = null;

        try {
            transaction.begin();
            String query = "SELECT cs.credit FROM CreditStatus cs WHERE cs.status = :status AND cs.actif = true";
            credit = em.createQuery(query, Credit.class)
                    .setParameter("status", status)
                    .getResultList();
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
