package com.smartbank.services.implementations;


import com.smartbank.models.entities.CreditStatus;
import com.smartbank.repository.implementations.CreditStatusRepository;
import com.smartbank.repository.interfaces.ICreditStatusRepository;
import com.smartbank.services.interfaces.ICreditStatusService;

import java.util.List;

public class CreditStatusService implements ICreditStatusService {

    public ICreditStatusRepository creditStatusRepository;
    public CreditStatusService() {
        creditStatusRepository = new CreditStatusRepository();
    }

    @Override
    public CreditStatus persist(CreditStatus creditStatus) {
        return creditStatusRepository.persist(creditStatus);
    }

    @Override
    public List<CreditStatus> findAll() {
        return creditStatusRepository.findAll();
    }
}
