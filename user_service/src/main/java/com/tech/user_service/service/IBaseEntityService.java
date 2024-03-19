package com.tech.user_service.service;

import com.tech.user_service.entity.BaseEntity;

import java.util.List;

public interface IBaseEntityService<E extends BaseEntity>{

    E save(E e);
    E findById(long id);
    List<E> findAll();
    void deleteById(long id);
}
