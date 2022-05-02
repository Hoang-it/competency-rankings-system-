package com.fa.training.demo.repository;

import com.fa.training.demo.entities.ProficiencyLevel;
import com.fa.training.demo.entities.ProficiencyLevelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProficientcyLevelRepository extends JpaRepository<ProficiencyLevel, Integer> {
}
