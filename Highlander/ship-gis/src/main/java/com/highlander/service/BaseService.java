package com.highlander.service;

import java.util.List;

public interface BaseService<T> {


    public List<T> findAll();

    public T save(T entity);

    public void delete(T entity);

    public void delete(Long id);

    public T findOne(Long id);

}
