package com.smartbank.repository.implementations;

import com.smartbank.models.entities.Status;
import com.smartbank.repository.interfaces.IGenericRepository;

public class StatusRepository extends GenericRepository<Status> implements IGenericRepository<Status> {

    public StatusRepository() {
        super(Status.class);
    }
}
