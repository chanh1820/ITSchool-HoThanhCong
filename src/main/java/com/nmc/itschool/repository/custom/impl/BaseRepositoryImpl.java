package com.nmc.itschool.repository.custom.impl;

import com.nmc.itschool.repository.BaseRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class BaseRepositoryImpl<E, K> implements BaseRepository<E, K> {

    @PersistenceContext
    protected EntityManager entityManager;

    private final Class<E> entityClass;

    protected BaseRepositoryImpl(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

//    @Override
//    public List<E> findByCriteria(TypedQuery<E> typedQuery, BaseSCO baseSCO) {
//        int firstResult = 0;
//
//        if (baseSCO.getMaxResult() >= 0 && baseSCO.getCurrentPage() >= 0) {
//            firstResult = (baseSCO.getCurrentPage() - 1) * baseSCO.getMaxResult();
//        }
//
//        return typedQuery.setFirstResult(firstResult).setMaxResults(baseSCO.getMaxResult()).getResultList();
//    }

    @Override
    public Optional<E> findEntityById(UUID id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> query = cb.createQuery(entityClass);

        Root<E> root = query.from(entityClass);

        query.select(root).where(
                cb.and(
                        cb.equal(root.get("id"), id),
                        cb.isFalse(root.get("isDeleted"))));

        TypedQuery<E> typedQuery = entityManager.createQuery(query);

        List<E> results = typedQuery.getResultList();

        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

}
