package com.ua.project.Autobase.services;

import com.ua.project.Autobase.models.Application;
import com.ua.project.Autobase.models.Car;
import com.ua.project.Autobase.models.Driver;
import com.ua.project.Autobase.models.Route;
import com.ua.project.Autobase.services.CRUDInterface;
import org.springframework.data.repository.query.Param;

public interface RouteService extends CRUDInterface<Route> {
    Route createRoute(Application application);

    boolean setRoutes();

    Car findDriversCar(@Param("driverId") Long driverId);
}
