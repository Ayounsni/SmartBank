package com.smartbank.repository.interfaces;

import com.smartbank.models.entities.Credit;

import java.util.List;

public interface ICreditRepository {
    Credit add(Credit credit);
    List<Credit> findAllCredits();
}
