package com.fa.training.demo.repository;

import com.fa.training.demo.entities.CompetencyComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComponentRepository extends JpaRepository<CompetencyComponent, Integer> {
    Optional<CompetencyComponent> findByComponentName(String name);


}
