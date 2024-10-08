package com.smartbank.repository.implementations;

import com.smartbank.models.entities.CreditStatus;
import com.smartbank.repository.interfaces.ICreditStatusRepository;

public class CreditStatusRepository extends GenericRepository<CreditStatus> implements ICreditStatusRepository {

    public CreditStatusRepository() {
        super(CreditStatus.class);
    }
}
