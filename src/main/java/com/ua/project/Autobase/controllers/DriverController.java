package com.ua.project.Autobase.controllers;

import com.ua.project.Autobase.dto.DriverDto;
import com.ua.project.Autobase.exceptions.RequiredParamIsEmptyException;
import com.ua.project.Autobase.exceptions.RequiredParamIsEmptyOrLessException;
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
                         @RequestParam(name = "driverEarnings") BigDecimal earnings,
                         @RequestParam(name = "driverExperience") Double experience,
                         Model model) {
        try {
            checkInsertApplicationData(DriverDto
                    .builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .earnings(earnings)
                    .drivingExperience(experience)
                    .build());

            driverService.save(Driver
                    .builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .earnings(earnings)
                    .drivingExperience(experience)
                    .build());

            model.addAttribute("success", "Driver " + firstName + " " + lastName + " inserted!");
            return "drivers/index";
        }
        catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "drivers/create";
        }
    }

    private static void checkInsertApplicationData(DriverDto driverDto) {
        final BigDecimal MIN_EARNINGS = new BigDecimal(50);
        final double MIN_DRIVING_EXPERIENCE = 1;

        if (driverDto.getFirstName() == null || driverDto.getFirstName().trim().isEmpty()) {
            throw new RequiredParamIsEmptyException("First name");
        }
        else if (driverDto.getLastName() == null || driverDto.getLastName().trim().isEmpty()) {
            throw new RequiredParamIsEmptyException("Last name");
        }
        else if (driverDto.getEarnings() == null || driverDto.getEarnings().compareTo(MIN_EARNINGS) < 0) {
            throw new RequiredParamIsEmptyOrLessException("Earnings", String.valueOf(MIN_EARNINGS));
        }
        else if (driverDto.getDrivingExperience() == null || driverDto.getDrivingExperience() < MIN_DRIVING_EXPERIENCE) {
            throw new RequiredParamIsEmptyOrLessException("Driving experience", String.valueOf(MIN_DRIVING_EXPERIENCE));
        }
    }
}
