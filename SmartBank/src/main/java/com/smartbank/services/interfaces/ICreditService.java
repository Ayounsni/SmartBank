package com.smartbank.services.interfaces;

import com.smartbank.models.entities.Credit;

import java.util.List;

public interface ICreditService {
    Credit persist(Credit credit);
    List<Credit> findAll();
    Double calculerMensualite(Long capital, Long dureeEnMois);
}
