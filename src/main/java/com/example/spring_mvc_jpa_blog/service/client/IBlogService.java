package com.example.spring_mvc_jpa_blog.service.client;

import com.example.spring_mvc_jpa_blog.model.client.Blog;
import com.example.spring_mvc_jpa_blog.service.IGenerateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBlogService extends IGenerateService<Blog> {
    Page<Blog> findAll(Pageable pageable);
    Iterable<Blog> findAll();
    Blog findByBlog(int id);
    Page<Blog> findBlogByIdUser(int id , Pageable pageable);
    Page<Blog> findBlogByName(String name, Pageable pageable);
}
