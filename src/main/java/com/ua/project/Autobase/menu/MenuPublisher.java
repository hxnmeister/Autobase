package com.ua.project.Autobase.menu;

import org.springframework.stereotype.Service;

@Service
public class MenuPublisher {
    public static void showMenu() {
        System.out.println("  1. Add driver;");
        System.out.println("  2. Add car;");
        System.out.println("  3. Add application;");
        System.out.println("  4. Show drivers;");
        System.out.println("  5. Show cars;");
        System.out.println("  6. Show applications;");
        System.out.println("  7. Assign application to driver;");
        System.out.println("  8. Set car on a service;");
        System.out.println("  0. Exit program;");
        System.out.print(" Enter choice: ");
    }
}
