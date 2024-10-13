package com.smartbank.services.implementations;


import com.smartbank.models.entities.Credit;
import com.smartbank.models.entities.CreditStatus;
import com.smartbank.repository.implementations.CreditStatusRepository;
import com.smartbank.repository.interfaces.ICreditStatusRepository;
import com.smartbank.services.interfaces.ICreditStatusService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
@ApplicationScoped
public class CreditStatusService implements ICreditStatusService {
    @Inject
    private ICreditStatusRepository creditStatusRepository;

    @Override
    public CreditStatus persist(CreditStatus creditStatus) {
        return creditStatusRepository.persist(creditStatus);
    }

    @Override
    public CreditStatus findActiveCreditStatus(Credit credit){
        return creditStatusRepository.findActiveCreditStatus(credit);
    }
    @Override
    public CreditStatus merge(CreditStatus creditStatus) {
        return creditStatusRepository.merge(creditStatus);
    }
}
