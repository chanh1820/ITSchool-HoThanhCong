package com.nmc.itschool.repository;


import java.util.Optional;
import java.util.UUID;

public interface BaseRepository<E, K> {

    public Optional<E> findEntityById(UUID id);

//    public List<E> findByCriteria(TypedQuery<E> typedQuery, BaseSCO baseSCO);

}
