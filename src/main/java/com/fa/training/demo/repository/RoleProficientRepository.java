package com.fa.training.demo.repository;

import com.fa.training.demo.entities.RoleProficiencyLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleProficientRepository extends JpaRepository<RoleProficiencyLevel, Integer> {
}
