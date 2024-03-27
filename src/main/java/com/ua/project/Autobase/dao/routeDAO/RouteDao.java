package com.ua.project.Autobase.dao.routeDAO;

import com.ua.project.Autobase.dao.crudInterface.CRUDInterface;
import com.ua.project.Autobase.model.Route;

import java.util.List;

public interface RouteDao {
    /**
     *
     * @param route
     * @return after exception returns -1, otherwise returns code from Statement
     */
    int save(Route route);

    /**
     *
     * @param routes
     * @return after exception returns empty int array, otherwise returns array of codes from Statement
     */
    int[] saveMany(List<Route> routes);

    /**
     *
     * @return if there is no data in DB returns empty List otherwise returns array from Routes
     */
    List<Route> findAll();

    /**
     *
     * @param route
     * @return after exception returns -1, otherwise returns code from Statement
     */
    int delete(Route route);

    /**
     *
     * @return after exception returns -1, otherwise returns code from Statement
     */
    int deleteAll();
}
