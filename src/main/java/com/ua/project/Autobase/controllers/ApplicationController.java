package com.ua.project.Autobase.controllers;

import com.ua.project.Autobase.models.Application;
import com.ua.project.Autobase.models.CargoType;
import com.ua.project.Autobase.services.ApplicationService;
import com.ua.project.Autobase.services.CargoTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;
    private final CargoTypeService cargoTypeService;

    @GetMapping(value = "/applications")
    public String mainApplications(Model model) {
        return "applications/index";
    }

    @GetMapping(value = "/applications/create")
    public String create(Model model) {
        model.addAttribute("cargoTypes", cargoTypeService.findAll());

        return "/applications/create";
    }

    @GetMapping(value = "/applications/get-all")
    public String getAll(Model model) {
        model.addAttribute("applications", applicationService.findAll());

        return "applications/display";
    }

    @PostMapping(value = "/applications/insert")
    public String insert(@RequestParam(name = "applicationWeight") Double weight,
                         @RequestParam(name = "applicationCargoId") Long cargoId,
                         Model model) {
        try {
            CargoType cargoType = cargoTypeService.findCargoTypeById(cargoId);

            applicationService.save(Application
                    .builder()
                    .weight(weight)
                    .cargoType(cargoType)
                    .build());

            model.addAttribute("success", "Application inserted!");
        }
        catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "/applications/index";
    }
}
