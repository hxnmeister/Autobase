package com.ua.project.Autobase.repositories;

import com.ua.project.Autobase.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    String SELECT_FREE_APPLICATIONS = """
        SELECT a.*
        FROM applications a
        LEFT JOIN completed_routes cr ON a.id = cr.application_id
        WHERE cr.application_id IS NULL
    """;

    @Query(value = SELECT_FREE_APPLICATIONS, nativeQuery = true)
    List<Application> getFreeApplications();
}
