package com.ua.project.Autobase.services;

import com.ua.project.Autobase.models.*;
import com.ua.project.Autobase.services.CRUDInterface;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RouteService extends CRUDInterface<Route> {
    Route createRoute(Application application, List<Destination> destinations);

    boolean setRoutes();

    Car findDriversCar(@Param("driverId") Long driverId);
}
