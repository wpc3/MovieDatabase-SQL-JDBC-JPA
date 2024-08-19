package io.zipcoder.persistenceapp.Services;

import java.util.List;

public interface ServiceInterFace <T> {
    T findByID(int id);
    T create(T t);
    T update(T t);
    List<T> findAll();
    void deleteID(int id);
}
