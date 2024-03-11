package com.example.spring_mvc_jpa_blog.repository.client;


import com.example.spring_mvc_jpa_blog.model.client.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IBlogRepository extends CrudRepository<Blog,Integer> {
    Page<Blog> findAll(Pageable pageable);
    @Query("SELECT b FROM  Blog b WHERE b.id = ?1")
    Blog findBLogById(int id);
    @Query("SELECT b FROM Blog b WHERE b.user.id = ?1")
    Page<Blog> findBlogByIdUser(int id,Pageable pageable);
    @Query("SELECT b FROM Blog b WHERE b.title LIKE ?1 OR b.author LIKE ?1")
    Page<Blog> findBLogByName(String name, Pageable pageable);

}
