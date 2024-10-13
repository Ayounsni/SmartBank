package com.smartbank.services.interfaces;

import com.smartbank.models.entities.Credit;
import com.smartbank.models.entities.Status;

import java.time.LocalDate;
import java.util.List;

public interface ICreditService {
    Credit persist(Credit credit);
    List<Credit> findAll();
    Double calculerMensualite(Long capital, Long dureeEnMois);
    Credit findById(Long id);
    List<Credit> findCreditsByStatusAndDate(Status status, LocalDate date);
    List<Credit> findCreditsByDate(LocalDate date);
    List<Credit> findCreditsByStatus(Status status);
}
