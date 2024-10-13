package com.smartbank.repository.implementations;

import com.smartbank.models.entities.Credit;
import com.smartbank.models.entities.CreditStatus;
import com.smartbank.models.entities.Status;
import com.smartbank.repository.interfaces.ICreditStatusRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class CreditStatusRepository extends GenericRepository<CreditStatus> implements ICreditStatusRepository {

    public CreditStatusRepository() {
        super(CreditStatus.class);
    }
    @Override
    public CreditStatus findActiveCreditStatus(Credit credit){
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        CreditStatus creditStatus = null;

        try {
            transaction.begin();
            String query = "SELECT cs FROM CreditStatus cs WHERE cs.credit = :credit AND cs.actif = true";
            creditStatus = em.createQuery(query, CreditStatus.class)
                    .setParameter("credit", credit)
                    .getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return creditStatus;
    }


}
