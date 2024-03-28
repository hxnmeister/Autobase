package com.ua.project.Autobase.menu;

import org.springframework.stereotype.Service;

@Service
public class MenuPublisher {
    public static void showMenu() {
        System.out.println("  1. Show drivers;");
        System.out.println("  2. Show cars;");
        System.out.println("  3. Show applications;");
        System.out.println("  4. Add driver;");
        System.out.println("  5. Add car;");
        System.out.println("  6. Add application;");
        System.out.println("  0. Exit program;");
        System.out.print(" Enter choice: ");
    }
}
