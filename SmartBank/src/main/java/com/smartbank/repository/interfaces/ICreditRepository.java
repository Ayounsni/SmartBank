package com.smartbank.repository.interfaces;

import com.smartbank.models.entities.Credit;
import com.smartbank.models.entities.Status;

import java.time.LocalDate;
import java.util.List;


public interface ICreditRepository extends IGenericRepository<Credit> {
    List<Credit> findCreditsByStatusAndDate(Status status, LocalDate date);
    List<Credit> findCreditsByDate(LocalDate date);
    List<Credit> findCreditsByStatus(Status status);
}
