package com.example.spring_mvc_jpa_blog.controller.client;

import com.example.spring_mvc_jpa_blog.model.client.User;
import com.example.spring_mvc_jpa_blog.service.client.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static jdk.internal.org.jline.utils.Colors.s;

@Controller
@RequestMapping("/users")
public class LoginController {
    @Autowired
    private ILoginService iLoginService;
    @GetMapping()
    public ModelAndView showFormLogin(){
        ModelAndView modelAndView = new ModelAndView("/client/login");
        modelAndView.addObject("user",new User());
        return modelAndView;
    }
    @GetMapping("/signUp")
    public ModelAndView showFormSignUp(){
        ModelAndView modelAndView = new ModelAndView("/client/signUp");
        modelAndView.addObject("user",new User());
        return modelAndView;
    }
    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, BindingResult bindingResult, HttpSession session){
        if (bindingResult.hasFieldErrors()){
            return "/client/login";
        }
        User optional = iLoginService.findAccount(user.getEmail(), user.getPassword());
        if (user.getEmail().equals("admin@gmail.com") && user.getPassword().equals("admin123")){
            return "redirect:/admins/";
        }else {
            if (optional != null){
                session.setAttribute("user",optional);
                return "redirect:/blogs";
            }else {
                return "redirect:/users";
            }
        }
    }
    @PostMapping("/signUp")
    public String signUp(@ModelAttribute("user") User user, BindingResult bindingResult){
        new User().validate(user,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "/client/signUp";
        }
        Optional<User> optional = iLoginService.findEmail(user.getEmail());
        if (!optional.isPresent()){
            if (user.getPassword().equals(user.getRePassword())){
                iLoginService.save(user);
                return "redirect:/users";
            }else {
                return "redirect:/users/signUp";
            }
        }else {
            return "redirect:/users/signUp";
        }
    }
}
