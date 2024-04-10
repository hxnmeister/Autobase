package com.ua.project.Autobase.controllers;

import com.ua.project.Autobase.models.Car;
import com.ua.project.Autobase.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping(value = "/cars")
    public String mainCars(Model model) {
        return "cars/index";
    }

    @GetMapping(value = "/cars/create")
    public String create(Model model) {
        return "cars/create";
    }

    @GetMapping(value = "/cars/get-all")
    public String getAll(Model model) {
        model.addAttribute("cars", carService.findAll());

        return "cars/display";
    }

    @PostMapping(value = "/cars/insert")
    public String insert(@RequestParam(name = "carModel") String carModel,
                         @RequestParam(name = "carManufacturer") String manufacturer,
                         @RequestParam(name = "carCondition") Integer condition,
                         @RequestParam(name = "carLoadCapacity") Double loadCapacity,
                         @RequestParam(name = "carOnService") Integer isOnService,
                         Model model) {
        try {
            carService.save(Car
                    .builder()
                    .model(carModel)
                    .manufacturer(manufacturer)
                    .condition(condition)
                    .loadCapacity(loadCapacity)
                    .isOnService(isOnService)
                    .build());

            model.addAttribute("success", "Car " + carModel + " inserted!");
        }
        catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "/cars/index";
    }
}
