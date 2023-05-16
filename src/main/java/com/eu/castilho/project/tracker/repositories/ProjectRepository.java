package com.eu.castilho.project.tracker.repositories;

import com.eu.castilho.project.tracker.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
