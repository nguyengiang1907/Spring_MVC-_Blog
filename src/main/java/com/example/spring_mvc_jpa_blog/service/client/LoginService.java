package com.example.spring_mvc_jpa_blog.service.client;

import com.example.spring_mvc_jpa_blog.model.client.User;
import com.example.spring_mvc_jpa_blog.repository.client.ILoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class LoginService implements ILoginService{
    @Autowired
    private ILoginRepository iLoginRepository;
    @Override
    public Iterable<User> findAll() {
        return iLoginRepository.findAll();
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void save(User user) {
        iLoginRepository.save(user);
    }

    @Override
    public void delete(int id) {
        User user = iLoginRepository.findUserById(id);
        iLoginRepository.delete(user);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return iLoginRepository.findAll(pageable);
    }

    @Override
    public User findAccount(String email, String password) {
        return iLoginRepository.findAccount(email,password);
    }

    @Override
    public Optional<User> findEmail(String email) {
        return iLoginRepository.findEmail(email);
    }

    @Override
    public User findUserById(int id) {
        User user = iLoginRepository.findUserById(id);
        return user;
    }

    @Override
    public Iterable<User> findUserByAll(String name) {
        return iLoginRepository.findUserByName("%"+name+"%");
    }
}
