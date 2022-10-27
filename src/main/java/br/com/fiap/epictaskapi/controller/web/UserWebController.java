package br.com.fiap.epictaskapi.controller.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.epictaskapi.model.User;
import br.com.fiap.epictaskapi.service.UserService;

@Controller
@RequestMapping("/user")
public class UserWebController {
    
    @Autowired
    UserService service;
    
    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("user/index").addObject("users", service.listAll());
    }

    @GetMapping("new")
    public String form(){
        return "user/form";
    }

    @PostMapping
    public String create(@Valid User user, BindingResult result){
        if (result.hasErrors()){
            System.out.println(result);
            return "user/form";
        } 
        service.save(user);
        return "redirect:/user";
    }
}
