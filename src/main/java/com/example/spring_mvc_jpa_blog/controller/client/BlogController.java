package com.example.spring_mvc_jpa_blog.controller.client;

import com.example.spring_mvc_jpa_blog.model.client.Blog;
import com.example.spring_mvc_jpa_blog.model.client.BlogForm;
import com.example.spring_mvc_jpa_blog.model.client.User;
import com.example.spring_mvc_jpa_blog.service.client.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@SessionAttributes("user")
@RequestMapping("/blogs")
public class BlogController {
    @Value("${file-upload}")
    private String fileUpload;
    @Autowired
    private IBlogService iBlogService;
    @GetMapping()
    public ModelAndView showBlog(@PageableDefault(value = 5) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/client/home");
        Page<Blog> listBlog = iBlogService.findAll(pageable);
        modelAndView.addObject("blogs",listBlog);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreate(){
        ModelAndView modelAndView = new ModelAndView("/client/create");
        modelAndView.addObject("blog",new BlogForm());
        return modelAndView;
    }
    @PostMapping("/save")
    public ModelAndView saveBlog(@ModelAttribute("blog") BlogForm blogForm , HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("redirect:/blogs");
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String time = localDateTime.format(dateTimeFormatter);
        blogForm.setDateSubmitted(time);
        MultipartFile multipartFile = blogForm.getPicture();
        String nameFile = multipartFile.getOriginalFilename();
        FileCopyUtils.copy(blogForm.getPicture().getBytes(),new File(fileUpload+nameFile));
        Blog blog;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        blog = new Blog(blogForm.getTitle(),blogForm.getContent(),blogForm.getDateSubmitted(),user.getName(),nameFile,user);
        iBlogService.save(blog);
        return modelAndView;
    }
    @GetMapping("/{id}/edit")
    public ModelAndView showEdit(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("/client/edit");
        Optional<Blog> blog = iBlogService.findById(id);
//        BlogForm blogForm = null;
//        blogForm = new BlogForm(blog.getId(),blog.getTitle(),blog.getContent(),blog.getDateSubmitted(),blog.getAuthor(),blog.getPicture());
        modelAndView.addObject("blog",blog);
        return modelAndView;
    }
    @PostMapping("update")
    public ModelAndView updateBlog(@ModelAttribute("blog") Blog blog){
        ModelAndView modelAndView = new ModelAndView("redirect:/blogs");
        iBlogService.save(blog);
        return modelAndView;
    }
    @GetMapping("/{id}/delete")
    public ModelAndView deleteBlog(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("redirect:/blogs");
        iBlogService.delete(id);
        return modelAndView;
    }
    @GetMapping("{id}/show")
    public ModelAndView showBLogs(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("/client/show");
        Blog blog = iBlogService.findByBlog(id);
        modelAndView.addObject("blog",blog);
        return modelAndView;
    }
    @GetMapping("{id}/showUser")
    public ModelAndView showBlogUser(@PathVariable("id") int id , Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/client/showBlogUser");
        Page<Blog> blogs = iBlogService.findBlogByIdUser(id, pageable);
        modelAndView.addObject("blogs",blogs);
        return modelAndView;
    }
    @GetMapping("/search")
    public ModelAndView search(@RequestParam("search")String search,Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/client/search");
        Page<Blog> blogs = iBlogService.findBlogByName(search,pageable);
        modelAndView.addObject("blogs",blogs);
        return modelAndView;
    }
}
