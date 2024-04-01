package com.ua.project.Autobase.dao.driverDAO;

import com.ua.project.Autobase.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
