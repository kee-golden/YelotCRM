package com.yelot.crm.mapper;

public interface BaseMapper<E> {
//    List<E> findAll();

    E find(Long id);

    int insert(E e);

    int update(E e);

    int delete(Long id);
}