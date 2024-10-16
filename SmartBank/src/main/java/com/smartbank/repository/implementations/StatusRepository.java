package com.smartbank.repository.implementations;

import com.smartbank.models.entities.Status;
import com.smartbank.repository.interfaces.IStatusRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StatusRepository extends GenericRepository<Status> implements IStatusRepository {

    public StatusRepository() {
        super(Status.class);
    }
}
