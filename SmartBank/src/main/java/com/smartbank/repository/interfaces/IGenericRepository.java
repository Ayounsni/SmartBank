package com.smartbank.repository.interfaces;

import java.util.List;

public interface IGenericRepository<T> {
    T persist(T entity);
    T merge(T entity);
    void remove(T entity);
    T findById(Long id);
    List<T> findAll();
}
