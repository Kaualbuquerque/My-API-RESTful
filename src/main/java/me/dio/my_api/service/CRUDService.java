package me.dio.my_api.service;

import java.util.List;

public interface CRUDService<ID, T> {
    List<T> findAll();

    T findById(ID id);

    T create(T entity);

    T update(ID id, T entity);

    void delete(ID id);
}
