package com.smartbank.services.implementations;

import com.smartbank.models.entities.Credit;
import com.smartbank.repository.implementations.CreditRepository;
import com.smartbank.repository.interfaces.ICreditRepository;
import com.smartbank.services.interfaces.ICreditService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.List;
import java.util.Set;

public class CreditService implements ICreditService {

    private final ICreditRepository creditRepository;
    private  Validator validator;

    public CreditService() {
        this.creditRepository = new CreditRepository();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();

    }

    @Override
    public Credit persist(Credit credit) {
        Set<ConstraintViolation<Credit>> violations = validator.validate(credit);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Les données du crédit sont invalides : " + violations.toString());
        }

        creditRepository.persist(credit);
        return credit;
    }

    @Override
    public List<Credit> findAll() {
        return creditRepository.findAll();
    }

    @Override
    public Double calculerMensualite(Long capital, Long dureeEnMois) {
        double tauxAnnuel = 0.12;
        double tauxMensuel = tauxAnnuel / 12;
        double mensualite = (capital * tauxMensuel) / (1 - Math.pow(1 + tauxMensuel, -dureeEnMois));
        return Math.round(mensualite * 100.0) / 100.0;
    }
}
