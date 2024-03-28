package com.ua.project.Autobase.menu;

import com.ua.project.Autobase.AppStarter;
import com.ua.project.Autobase.dao.applicationDAO.ApplicationDao;
import com.ua.project.Autobase.dao.carDAO.CarDao;
import com.ua.project.Autobase.dao.cargo_typeDAO.CargoTypeDao;
import com.ua.project.Autobase.dao.driverDAO.DriverDao;
import com.ua.project.Autobase.model.Application;
import com.ua.project.Autobase.model.Car;
import com.ua.project.Autobase.model.CargoType;
import com.ua.project.Autobase.model.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class MenuExecutor {
    @Autowired
    private CarDao carDao;

    @Autowired
    private CargoTypeDao cargoTypeDao;

    @Autowired
    private DriverDao driverDao;

    @Autowired
    private ApplicationDao applicationDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);

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
                    case 0:
                        System.exit(0);
                    default:
                        throw new InputMismatchException();
                }
            }
            catch (InputMismatchException e) {
                LOGGER.warn(" Invalid number input!");
            }
            catch (RuntimeException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    public void menuItem1Execute() {
        displayRawListInfo(" All drivers:", "=", driverDao.findAll());
    }

    public void menuItem2Execute() {
        displayRawListInfo(" All cars:", "=", carDao.findAll());
    }

    public void menuItem3Execute() {
        displayRawListInfo(" All applications:", "=", applicationDao.findAll());
    }

    public void menuItem4Execute() {
        Scanner scanner = new Scanner(System.in);
        Driver driver = new Driver();

        System.out.print(" Enter first name: ");
        driver.setFirstName(scanner.nextLine());
        System.out.print(" Enter last name: ");
        driver.setLastName(scanner.nextLine());

        driver.setEarnings(getCorrectBigDecimalInput(" Enter earnings: ", scanner));
        driver.setDrivingExperience(getCorrectDoubleInput(" Enter driving experience: ", scanner));

        if(driverDao.save(driver) > 0) {
            System.out.println(" Driver " + driver.getLastName() + " " + driver.getFirstName() + " successfully saved!");
        }
        else {
            System.out.println(" Cannot execute request with such data!");
            System.out.println(driver);
        }
    }

    public void menuItem5Execute() {
        Scanner scanner = new Scanner(System.in);
        Car car = new Car();

        System.out.print(" Enter manufacturer: ");
        car.setManufacturer(scanner.nextLine());
        System.out.print(" Enter make: ");
        car.setModel(scanner.nextLine());

        car.setCondition(getPositiveInteger(" Enter car condition: ", scanner));
        car.setLoadCapacity(getCorrectDoubleInput(" Enter load capacity: ", scanner));

        if(carDao.save(car) > 0) {
            System.out.println(" Car " + car.getModel() + " successfully added!");
        }
        else {
            System.out.println(" Cannot add this car!");
            System.out.println(car);
        }
    }

    public void menuItem6Execute() {
        Scanner scanner = new Scanner(System.in);
        Application application = new Application();
        List<CargoType> cargoTypes = cargoTypeDao.findAll();

        application.setWeight(getCorrectDoubleInput(" Enter cargo weight: ", scanner));
        System.out.println("\n Select cargo type:");
        cargoTypes.forEach(System.out::println);
        application.setCargoTypeId(getValidId(cargoTypes.stream().map(CargoType::getId).collect(Collectors.toList()), " Enter cargo type ID to add: "));

        if (applicationDao.save(application) > 0) {
            System.out.println(" Application successfully saved!");
        }
        else {
            System.out.println(" Cannot execute request with such data!");
            System.out.println(application);
        }
    }

    private BigDecimal getCorrectBigDecimalInput(String inputMessage, Scanner scanner) {
        while (true) {
            try {
                System.out.print(inputMessage);
                return scanner.nextBigDecimal();
            }
            catch (InputMismatchException e) {
                LOGGER.warn(" Incorrect value!");
                scanner.nextLine();
            }
            catch (RuntimeException e) {
                LOGGER.warn(e.getMessage());
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
                LOGGER.warn(" Incorrect value!");
                scanner.nextLine();
            }
            catch (RuntimeException e) {
                LOGGER.warn(e.getMessage());
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
                LOGGER.warn(" Incorrect value!");
                scanner.nextLine();
            }
            catch (RuntimeException e) {
                LOGGER.warn(e.getMessage());
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
