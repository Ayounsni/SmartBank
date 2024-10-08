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
    public Credit add(Credit credit) {
        Set<ConstraintViolation<Credit>> violations = validator.validate(credit);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Les données du crédit sont invalides : " + violations.toString());
        }

        creditRepository.add(credit);
        return credit;
    }

    @Override
    public List<Credit> findAllCredits() {
        return creditRepository.findAllCredits();
    }
}
