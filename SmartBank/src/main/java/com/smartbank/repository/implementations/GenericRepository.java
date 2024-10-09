package com.smartbank.repository.implementations;

import com.smartbank.config.EntityManagerFactorySingleton;
import com.smartbank.repository.interfaces.IGenericRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class GenericRepository<T> implements IGenericRepository<T> {
    private Class<T> entityClass;

    public GenericRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager() {
        return EntityManagerFactorySingleton.entityManagerFactory().createEntityManager();
    }

    @Override
    public T persist(T entity) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
             em.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return entity;
    }

    @Override
    public T merge(T entity) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        T mergedEntity = null;

        try {
            transaction.begin();
            mergedEntity = em.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }

        return mergedEntity;
    }

    @Override
    public void remove(T entity) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.remove(em.merge(entity));
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    @Override
    public T findById(Long id) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        T entity = null;

        try {
            transaction.begin();
            entity = em.find(entityClass, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }

        return entity;
    }

    @Override
    public List<T> findAll() {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        List<T> entities = null;

        try {
            transaction.begin();
            entities = em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                    .getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }

        return entities;
    }
}
