package com.ua.project.Autobase.dao.routeDAO;

import com.ua.project.Autobase.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RouteRepository extends JpaRepository<Route, Long> {
}