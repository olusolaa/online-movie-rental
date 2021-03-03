package com.olusola.videorental.controller;

import com.olusola.videorental.dtos.SignInDto;
import com.olusola.videorental.dtos.SignUpDto;
import com.olusola.videorental.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/")
    public String SignInGet(Model model) {
        var signInDto = new SignInDto();
        model.addAttribute("isSuccessful", true);
        model.addAttribute("signInDto", new SignInDto());
        return "sign-in";
    }

    @PostMapping(path = "/sign-in")
    public String SignInPost(@Valid @ModelAttribute("signInDto") SignInDto signInDto, Model model, HttpSession session){
        var response = userService.signIn(signInDto);
        if(response.isSuccessful()) {
            session.setAttribute("principal", response.getUser());
            if (response.getRole().equals("Admin")) {
                return "admin-home";
            } else{
                return "user-home";
            }
        }else {
            model.addAttribute("error", response.getError());
            model.addAttribute("isSuccessful", response.isSuccessful());
            return "sign-in";
        }
    }

    @GetMapping(path = "/sign-up")
    public String SignUpGet(Model model) {
        var signUpDto = new SignUpDto();
        model.addAttribute("isSuccessful", true);
        model.addAttribute("signUpDto", new SignUpDto());
        return "sign-up";
    }

    @PostMapping("/sign-up-post")
    public String registerUserPost(@Valid @ModelAttribute("signUpDto") SignUpDto signUpDto, Model model) {
        var response = userService.signUp(signUpDto);
        if(response.isSuccessful())
        {
            return "redirect:/";
        }else {
            model.addAttribute("error", response.getError());
            model.addAttribute("isSuccessful", response.isSuccessful());
            return "sign-up";
        }
    }


}
