package com.example.spring_mvc_jpa_blog.service.client;

import com.example.spring_mvc_jpa_blog.model.client.Blog;
import com.example.spring_mvc_jpa_blog.repository.client.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.Optional;

@Service
public class BlogService implements IBlogService {
    @Autowired
    private IBlogRepository iBlogRepository;


    @Override
    public Page<Blog> findAll(Pageable pageable) {
        Page<Blog> blogs = iBlogRepository.findAll(pageable);
        for (Blog blog : blogs){
            String content = blog.getContent();
            String newContent = StringUtils.abbreviate(content,300);
            blog.setContent(newContent);
        }
        return blogs;
    }

    @Override
    public Blog findByBlog(int id) {
        Blog blog = iBlogRepository.findBLogById(id);
        return blog;
    }

    @Override
    public Page<Blog> findBlogByIdUser(int id, Pageable pageable) {
        Page<Blog> blogs = iBlogRepository.findBlogByIdUser(id,pageable);
        for (Blog blog : blogs){
            String content = blog.getContent();
            String newContent = StringUtils.abbreviate(content,300);
            blog.setContent(newContent);
        }
        return blogs;
    }

    @Override
    public Page<Blog> findBlogByName(String name, Pageable pageable) {
        Page<Blog> blogs = iBlogRepository.findBLogByName("%"+name+"%",pageable);
        for (Blog blog : blogs){
            String content = blog.getContent();
            String newContent = StringUtils.abbreviate(content,300);
            blog.setContent(newContent);
        }
        return blogs;
    }


    @Override
    public Iterable<Blog> findAll() {
        return iBlogRepository.findAll();
    }

    @Override
    public Optional<Blog> findById(int id) {
        return iBlogRepository.findById(id);
    }

    @Override
    public void save(Blog blog) {
        iBlogRepository.save(blog);
    }

    @Override
    public void delete(int id) {
        Optional<Blog> optional = iBlogRepository.findById(id);
        iBlogRepository.delete(optional.get());
    }
}
