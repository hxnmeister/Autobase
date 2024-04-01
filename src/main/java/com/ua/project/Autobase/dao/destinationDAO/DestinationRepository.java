package com.ua.project.Autobase.dao.destinationDAO;

import com.ua.project.Autobase.models.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DestinationRepository extends JpaRepository<Destination, Long> {
}
