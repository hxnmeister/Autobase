package com.ua.project.Autobase.dao.cargo_typeDAO;

import com.ua.project.Autobase.models.CargoType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CargoTypeRepository extends JpaRepository<CargoType, Long> {
    CargoType findCargoTypeById(long id);
}
