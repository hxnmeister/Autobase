package com.ua.project.Autobase.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AutobaseDbInitializer {
    @Value("${data.car.manufacturers}")
    private String pathToCarManufacturers;

    @Value("$data.createSqlTables")
    private String pathToCreateTables;
}
