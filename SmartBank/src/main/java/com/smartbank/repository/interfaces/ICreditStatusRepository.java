package com.smartbank.repository.interfaces;


import com.smartbank.models.entities.Credit;
import com.smartbank.models.entities.CreditStatus;

public interface ICreditStatusRepository extends IGenericRepository<CreditStatus> {
    CreditStatus findActiveCreditStatus(Credit credit);
}
