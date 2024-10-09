package com.smartbank.repository.implementations;

import com.smartbank.models.entities.Credit;
import com.smartbank.repository.interfaces.ICreditRepository;



public class CreditRepository extends GenericRepository<Credit> implements ICreditRepository {

    public CreditRepository() {
        super(Credit.class);
    }
}
