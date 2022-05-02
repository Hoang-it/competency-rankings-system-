package com.fa.training.demo.repository;

import com.fa.training.demo.entities.ProjectKPILevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectKPILevelRepository extends JpaRepository<ProjectKPILevel, Integer> {
}
