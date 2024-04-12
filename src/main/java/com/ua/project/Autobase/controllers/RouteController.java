package com.ua.project.Autobase.controllers;

import com.ua.project.Autobase.exceptions.CannotAddRouteException;
import com.ua.project.Autobase.models.Application;
import com.ua.project.Autobase.models.Route;
import com.ua.project.Autobase.services.ApplicationService;
import com.ua.project.Autobase.services.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;

    @GetMapping(value = "/routes")
    public String mainRoutes(Model model) {
        return "routes/index";
    }

    @GetMapping(value = "/routes/get-all")
    public String getAll(Model model) {
        model.addAttribute("routes", routeService.findAll());

        return "routes/display";
    }

    @GetMapping(value = "/routes/set-routes")
    public String setRoutes(Model model) {
        try {
            routeService.setRoutes();
        }
        catch (RuntimeException e) {
            model.addAttribute("error", "An error occurred during setting routes!");
        }

        return "routes/index";
    }
}
