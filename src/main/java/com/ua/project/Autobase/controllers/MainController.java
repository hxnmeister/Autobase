package com.ua.project.Autobase.controllers;

import com.ua.project.Autobase.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;

    @GetMapping(value = "/")
    public String homePage(Model model, Principal principal) {
        return principal == null ? "redirect:/login" : "/home";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("users", userService.findAll());

        return "auth/login";
    }

    @GetMapping(value = "/logout-success")
    public String logoutSuccess(Model model) {
        return "auth/logout_success";
    }
}
