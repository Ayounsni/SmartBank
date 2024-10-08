package com.smartbank.services.interfaces;

import com.smartbank.models.entities.Credit;

import java.util.List;

public interface ICreditService {
    Credit add(Credit credit);
    List<Credit> findAllCredits();
}
