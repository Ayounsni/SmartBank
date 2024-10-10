package com.smartbank.services.interfaces;

import com.smartbank.models.entities.Status;

import java.util.List;

public interface IStatusService {
    Status persist(Status status);
    List<Status> findAll();
    Status findById(Long id);
}
