package com.fa.training.demo.repository;

import com.fa.training.demo.entities.ResponsibilityProficiencyLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsiveLevelRepository extends JpaRepository<ResponsibilityProficiencyLevel, Integer> {
}
