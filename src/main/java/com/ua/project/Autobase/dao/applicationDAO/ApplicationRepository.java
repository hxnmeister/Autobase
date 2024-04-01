package com.ua.project.Autobase.dao.applicationDAO;

import com.ua.project.Autobase.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
