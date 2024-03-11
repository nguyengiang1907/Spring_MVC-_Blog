package com.example.spring_mvc_jpa_blog.service;

import java.util.Optional;

public interface IGenerateService<T>{
    Iterable<T> findAll();
    Optional<T> findById(int id);
    void save(T t);
    void delete(int id);
}
