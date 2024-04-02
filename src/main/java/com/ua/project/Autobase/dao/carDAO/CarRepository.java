package com.ua.project.Autobase.dao.carDAO;

import com.ua.project.Autobase.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car, Long> {
}
