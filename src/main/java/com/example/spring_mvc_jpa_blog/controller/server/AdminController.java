package com.example.spring_mvc_jpa_blog.controller.server;

import com.example.spring_mvc_jpa_blog.model.client.User;
import com.example.spring_mvc_jpa_blog.service.client.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    private ILoginService iLoginService;
    @GetMapping()
    public ModelAndView showHome(@PageableDefault(value = 9) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/server/home");
        Page<User> users = iLoginService.findAll(pageable);
        modelAndView.addObject("admins",users);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/server/create");
        modelAndView.addObject("admin",new User());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute("admin") User user){
        ModelAndView modelAndView = new ModelAndView("redirect:/admins");
        iLoginService.save(user);
        return modelAndView;
    }
    @GetMapping("{id}/edit")
    public ModelAndView showFormEdit(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("/server/edit");
         User admin= iLoginService.findUserById(id);
        modelAndView.addObject("admin",admin);
        return modelAndView;
    }
    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("admin") User user){
        ModelAndView modelAndView = new ModelAndView("redirect:/admins");
        iLoginService.save(user);
        return modelAndView;
    }
    @GetMapping("{id}/delete")
    public ModelAndView delete(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("redirect:/admins");
        iLoginService.delete(id);
        return modelAndView;
    }
    @GetMapping("{id}/view")
    public ModelAndView showInformationUser(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("/server/view");
        User admin = iLoginService.findUserById(id);
        modelAndView.addObject("admin", admin);
        return modelAndView;
    }
    @PostMapping("/search")
    public ModelAndView search(@RequestParam("search") String name){
        ModelAndView modelAndView = new ModelAndView("/server/search");
        Iterable<User> iterable = iLoginService.findUserByAll(name);
        modelAndView.addObject("admins",iterable);
        return modelAndView;
    }
}
