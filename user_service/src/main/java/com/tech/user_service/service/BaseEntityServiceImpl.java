package com.tech.user_service.service;

import com.tech.user_service.entity.BaseEntity;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Getter
public class BaseEntityServiceImpl<E extends BaseEntity, R extends JpaRepository<E, Long>> implements IBaseEntityService<E>{

    private final R repository;

    @Autowired
    public BaseEntityServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public E save(E entity) {

        return repository.save(entity);
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public E findById(long id) {
        //TODO add dedicated exception

        Optional<E> e = repository.findById(id);
        if(e.isEmpty()) {
            throw new RuntimeException("cannot find resource");
        }
        return e.get();
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

}