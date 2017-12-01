package com.highlander.service;

import com.highlander.repository.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T> {
    protected Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected BaseRepository<T> baseRepository;

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public T save(T entity) {
        return baseRepository.save(entity);
    }

    @Override
    public void delete(T entity) {
        baseRepository.delete(entity);
    }

    @Override
    public void delete(Long id) {
        baseRepository.delete(id);
    }

    @Override
    public T findOne(Long id) {
        return baseRepository.findOne(id);
    }

}
