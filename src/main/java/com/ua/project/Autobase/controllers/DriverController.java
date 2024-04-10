package com.ua.project.Autobase.controllers;

import com.ua.project.Autobase.models.Driver;
import com.ua.project.Autobase.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @GetMapping(value = "/drivers")
    public String mainDrivers() {
        return "drivers/index";
    }

    @GetMapping(value = "/drivers/create")
    public String create() {
        return "drivers/create";
    }

    @GetMapping(value = "/drivers/get-all")
    public String getAll(Model model) {
        model.addAttribute("drivers" ,driverService.findAll());

        return "drivers/display";
    }

    @PostMapping(value = "/drivers/insert")
    public String insert(@RequestParam(name = "driverFirstName") String firstName,
                         @RequestParam(name = "driverLastName") String lastName,
                         @RequestParam(name = "driverEarnings") Double earnings,
                         @RequestParam(name = "driverExperience") Double experience,
                         Model model) {
        try {
            driverService.save(Driver
                    .builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .earnings(BigDecimal.valueOf(earnings))
                    .drivingExperience(experience)
                    .build());

            model.addAttribute("success", "Driver " + firstName + " " + lastName + " inserted!");
        }
        catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "drivers/index";
    }
}
