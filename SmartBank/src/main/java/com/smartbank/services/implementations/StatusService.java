package com.smartbank.services.implementations;

import com.smartbank.models.entities.Status;
import com.smartbank.repository.implementations.StatusRepository;
import com.smartbank.repository.interfaces.IStatusRepository;
import com.smartbank.services.interfaces.IStatusService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
@ApplicationScoped
public class StatusService implements IStatusService {
    @Inject
    private  IStatusRepository statusRepository;

    @Override
    public Status persist(Status status) {
        return statusRepository.persist(status);
    }

    @Override
    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    public Status findById(Long id) {
        return statusRepository.findById(id);
    }
}
