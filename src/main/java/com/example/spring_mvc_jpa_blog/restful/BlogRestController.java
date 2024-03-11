package com.example.spring_mvc_jpa_blog.restful;

import com.example.spring_mvc_jpa_blog.model.client.Blog;
import com.example.spring_mvc_jpa_blog.service.client.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/blogs")
public class BlogRestController {
    @Autowired
    private IBlogService iBlogService;
    @GetMapping
    public ResponseEntity<Iterable<Blog>> findAllBlogs(){
        List<Blog> blogs = (List<Blog>) iBlogService.findAll();
        if (blogs.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blogs,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Blog> findById(@PathVariable Integer id){
        Optional<Blog> blogOptional = iBlogService.findById(id);
        if (!blogOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogOptional.get(),HttpStatus.OK);
    }
}
