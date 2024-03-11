package com.example.spring_mvc_jpa_blog.service.client;

import com.example.spring_mvc_jpa_blog.model.client.User;
import com.example.spring_mvc_jpa_blog.service.IGenerateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ILoginService extends IGenerateService<User> {
    Page<User> findAll(Pageable pageable);
    User findAccount(String email, String password);
    Optional<User> findEmail(String email);
    User findUserById(int id);
    Iterable<User> findUserByAll(String name);

}
