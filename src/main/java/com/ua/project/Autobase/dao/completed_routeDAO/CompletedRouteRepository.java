package com.ua.project.Autobase.dao.completed_routeDAO;

import com.ua.project.Autobase.model.CompletedRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CompletedRouteRepository extends JpaRepository<CompletedRoute, Long> {
}
