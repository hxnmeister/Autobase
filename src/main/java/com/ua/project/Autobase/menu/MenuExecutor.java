package com.ua.project.Autobase.menu;

import com.ua.project.Autobase.exceptions.CannotAddRouteException;
import com.ua.project.Autobase.models.*;
import com.ua.project.Autobase.services.ApplicationService;
import com.ua.project.Autobase.services.CarService;
import com.ua.project.Autobase.services.CargoTypeService;
import com.ua.project.Autobase.services.DriverService;
import com.ua.project.Autobase.services.RouteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class MenuExecutor {
    private final CarService carService;
    private final RouteService routeService;
    private final DriverService driverService;
    private final CargoTypeService cargoTypeService;
    private final ApplicationService applicationService;

    public void startMenu() {
        final Scanner SCANNER = new Scanner(System.in);

        while (true) {
            try {
                MenuPublisher.showMenu();
                final int CHOICE = SCANNER.nextInt();
                SCANNER.nextLine();

                switch (CHOICE) {
                    case 1:
                        menuItem1Execute();
                        break;
                    case 2:
                        menuItem2Execute();
                        break;
                    case 3:
                        menuItem3Execute();
                        break;
                    case 4:
                        menuItem4Execute();
                        break;
                    case 5:
                        menuItem5Execute();
                        break;
                    case 6:
                        menuItem6Execute();
                        break;
                    case 7:
                        menuItem7Execute();
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        throw new InputMismatchException();
                }
            }
            catch (InputMismatchException e) {
                log.warn(" Invalid number input!");
            }
            catch (RuntimeException e) {
                log.warn(e.getMessage());
            }
        }
    }

    public void menuItem1Execute() {
        Scanner scanner = new Scanner(System.in);
        Driver driver = new Driver();

        System.out.print(" Enter first name: ");
        driver.setFirstName(scanner.nextLine());
        System.out.print(" Enter last name: ");
        driver.setLastName(scanner.nextLine());

        driver.setEarnings(getCorrectBigDecimalInput(" Enter earnings: ", scanner));
        driver.setDrivingExperience(getCorrectDoubleInput(" Enter driving experience: ", scanner));

        if(driverService.save(driver).getId() != null) {
            System.out.println(" Driver " + driver.getLastName() + " " + driver.getFirstName() + " successfully saved!");
        }
        else {
            log.warn(" Cannot execute request with such data!");
            log.warn(driver);
        }
    }

    public void menuItem2Execute() {
        Scanner scanner = new Scanner(System.in);
        Car car = new Car();

        System.out.print(" Enter manufacturer: ");
        car.setManufacturer(scanner.nextLine());
        System.out.print(" Enter make: ");
        car.setModel(scanner.nextLine());

        car.setCondition(getPositiveInteger(" Enter car condition: ", scanner));
        car.setLoadCapacity(getCorrectDoubleInput(" Enter load capacity: ", scanner));

        if(carService.save(car).getId() != null) {
            System.out.println(" Car " + car.getModel() + " successfully added!");
        }
        else {
            log.warn(" Cannot add this car!");
            log.warn(car);
        }
    }

    public void menuItem3Execute() {
        long id;
        Scanner scanner = new Scanner(System.in);
        Application application = new Application();
        List<CargoType> cargoTypes = cargoTypeService.findAll();

        application.setWeight(getCorrectDoubleInput(" Enter cargo weight: ", scanner));
        System.out.println("\n Select cargo type:");
        cargoTypes.forEach(System.out::println);
        id = getValidId(cargoTypes.stream().map(CargoType::getId).collect(Collectors.toList()), " Enter cargo type ID to add: ");
        application.setCargoType(cargoTypeService.findCargoTypeById(id));

        if (applicationService.save(application).getId() != null) {
            System.out.println(" Application successfully saved!");
        }
        else {
            System.out.println(" Cannot execute request with such data!");
            System.out.println(application);
        }
    }

    public void menuItem4Execute() {
        displayRawListInfo(" All drivers:", "=", driverService.findAll());
    }

    public void menuItem5Execute() {
        displayRawListInfo(" All cars:", "=", carService.findAll());
    }

    public void menuItem6Execute() {
        displayRawListInfo(" All applications:", "=", applicationService.findAll());
    }

    public void menuItem7Execute() {
        List<Car> cars = carService.findAll();
        List<Route> routes = routeService.findAll();
        List<Driver> drivers = driverService.findAll();
        List<Application> applications = applicationService.findAll();

        for (Application application : applications) {
            try {
                Route route = getRoute(application, drivers, cars, routes);

                if(!routes.contains(route)) {
                    routeService.save(route);
                    routes.add(route);
                }
            }
            catch (CannotAddRouteException e) {
                log.info(e.getMessage());
            }
        }
    }

    private static Route getRoute(Application application, List<Driver> drivers, List<Car> cars, List<Route> routes) {
        Route route = new Route();

        for (Driver driver : drivers) {
            if (driver.getDrivingExperience() >= application.getCargoType().getRequiredExperience() && !isDriverOnRoute(routes, driver)) {
                route.setDriver(driver);
                break;
            }
        }

        for (Car car : cars) {
            if(car.getLoadCapacity() >= application.getWeight() && !isCarOnRoute(routes, car)) {
                route.setCar(car);
                break;
            }
        }

        if (route.getDriver() == null || route.getCar() == null) {
            throw new CannotAddRouteException(" Cannot add Application to Route: " + application);
        }

        route.setApplication(application);
        return route;
    }

    private static boolean isDriverOnRoute(List<Route> routes, Driver driver) {
        for (Route route : routes) {
            if (route.getDriver().getId().equals(driver.getId())) {
                return true;
            }
        }

        return false;
    }

    private static boolean isCarOnRoute(List<Route> routes, Car car) {
        for (Route route : routes) {
            if (route.getCar().getId().equals(car.getId())) {
                return true;
            }
        }

        return false;
    }

    private BigDecimal getCorrectBigDecimalInput(String inputMessage, Scanner scanner) {
        while (true) {
            try {
                System.out.print(inputMessage);
                return scanner.nextBigDecimal();
            }
            catch (InputMismatchException e) {
                log.warn(" Incorrect value!");
                scanner.nextLine();
            }
            catch (RuntimeException e) {
                log.warn(e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private double getCorrectDoubleInput(String inputMessage, Scanner scanner) {
        while (true) {
            try {
                System.out.print(inputMessage);
                return scanner.nextDouble();
            }
            catch (InputMismatchException e) {
                log.warn(" Incorrect value!");
                scanner.nextLine();
            }
            catch (RuntimeException e) {
                log.warn(e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private int getPositiveInteger(String inputMessage, Scanner scanner) {
        int number;

        while (true) {
            try {
                System.out.print(inputMessage);
                number = scanner.nextInt();

                if (number < 1) {
                    throw new InputMismatchException();
                }

                return number;
            }
            catch (InputMismatchException e) {
                log.warn(" Incorrect value!");
                scanner.nextLine();
            }
            catch (RuntimeException e) {
                log.warn(e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private static long getValidId(List<Long> longList, String inputMessage) {
        Scanner scanner = new Scanner(System.in);
        long id;

        while (true) {
            try {
                System.out.print(inputMessage);
                id = scanner.nextLong();
                scanner.nextLine();

                if (longList.contains(id)) {
                    return id;
                }
                else {
                    throw new RuntimeException(" You entered incorrect ID!");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Please enter a correct number!");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void displayRawListInfo(String message, String separator, List<?> list) {
        System.out.println();
        System.out.println(separator.repeat(25));
        System.out.println(message);
        list.forEach(System.out::println);
        System.out.println(separator.repeat(25));
    }
}
