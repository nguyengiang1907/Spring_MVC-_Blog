package com.example.spring_mvc_jpa_blog.repository.client;

import com.example.spring_mvc_jpa_blog.model.client.Blog;
import com.example.spring_mvc_jpa_blog.model.client.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ILoginRepository extends CrudRepository<User,Integer> {
    Page<User> findAll(Pageable pageable);
    @Query("SELECT u FROM User u  WHERE u.email = ?1 AND u.password = ?2")
    User findAccount(String email , String password);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findEmail(String email);
    @Query("SELECT u FROM  User u WHERE u.id = ?1")
    User findUserById(int id);
    @Query("SELECT  u FROM User u WHERE u.name LIKE ?1 OR u.id = ?1 OR u.email LIKE ?1")
    Iterable<User> findUserByName(String name);
}
