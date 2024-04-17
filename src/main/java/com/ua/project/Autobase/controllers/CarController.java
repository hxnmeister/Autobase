package com.ua.project.Autobase.controllers;

import com.ua.project.Autobase.dto.CarDto;
import com.ua.project.Autobase.exceptions.RequiredParamIsEmptyException;
import com.ua.project.Autobase.exceptions.RequiredParamIsEmptyOrLessException;
import com.ua.project.Autobase.models.Car;
import com.ua.project.Autobase.models.Driver;
import com.ua.project.Autobase.services.CarService;
import com.ua.project.Autobase.services.DriverService;
import com.ua.project.Autobase.services.RouteService;
import com.ua.project.Autobase.utils.WebUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Log4j2
@Controller
@RequiredArgsConstructor
public class CarController {
    private final RouteService routeService;
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
    public String getAll(Model model, Principal principal) {
        if (principal.getName().equalsIgnoreCase("admin") || principal.getName().toLowerCase().contains("dispatch")) {
            model.addAttribute("cars", carService.findAll());
        }
        else {
            try {
                Long driverId = Long.parseLong(principal.getName().replaceAll("[^0-9]", ""));
                Car currentDriverCar = routeService.findDriversCar(driverId);

                model.addAttribute("cars", carService.findAll());
                model.addAttribute("currentDriverCar", currentDriverCar);
            }
            catch (NumberFormatException e) {
                model.addAttribute("error", "Incorrect driver ID!");
                log.warn(e.getMessage());
                return "/cars/index";
            }
            catch (RuntimeException e) {
                model.addAttribute("error", e.getMessage());
                log.warn(e.getMessage());
                return "/cars/index";
            }
        }

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
            checkInsertCarData(CarDto
                    .builder()
                    .model(carModel)
                    .manufacturer(manufacturer)
                    .condition(condition)
                    .loadCapacity(loadCapacity)
                    .isOnService(isOnService)
                    .build());

            carService.save(Car
                    .builder()
                    .model(carModel)
                    .manufacturer(manufacturer)
                    .condition(condition)
                    .loadCapacity(loadCapacity)
                    .isOnService(isOnService)
                    .build());

            model.addAttribute("success", "Car " + carModel + " inserted!");
            return "/cars/index";
        }
        catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "/cars/create";
        }
    }

    private static void checkInsertCarData(CarDto carDto) {
        final int MIN_CONDITION = 5;
        final int MIN_LOAD_CAPACITY = 20;

        if (carDto.getModel() == null || carDto.getModel().trim().isEmpty()) {
            throw new RequiredParamIsEmptyException("Car model");
        }
        else if(carDto.getManufacturer() == null || carDto.getManufacturer().trim().isEmpty()) {
            throw new RequiredParamIsEmptyException("Manufacturer");
        }
        else if(carDto.getCondition() == null || carDto.getCondition() < MIN_CONDITION) {
            throw new RequiredParamIsEmptyOrLessException("Condition", String.valueOf(MIN_CONDITION));
        }
        else if(carDto.getLoadCapacity() == null || carDto.getLoadCapacity() < MIN_LOAD_CAPACITY) {
            throw new RequiredParamIsEmptyOrLessException("Load capacity", String.valueOf(MIN_LOAD_CAPACITY));
        }
        else if(carDto.getIsOnService() == null) {
            throw new RequiredParamIsEmptyException("Is on service");
        }
    }
}
