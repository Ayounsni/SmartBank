package com.smartbank.services.interfaces;

import com.smartbank.models.entities.Credit;
import com.smartbank.models.entities.CreditStatus;
import com.smartbank.models.entities.Status;

import java.util.List;

public interface ICreditStatusService {
    CreditStatus persist(CreditStatus creditStatus);
    CreditStatus findActiveCreditStatus(Credit credit);
    CreditStatus merge(CreditStatus creditStatus);
}
