package com.fa.training.demo.repository;

import com.fa.training.demo.entities.ProficiencyLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProficiencyLevelRepository extends JpaRepository<ProficiencyLevel, Integer> {

    List<ProficiencyLevel> findByProficiencyLevelNameAndCompetencyComponentDetailComponentDetailId(String name, int componentDetailId);

    List<ProficiencyLevel> findByCompetencyComponentDetailComponentDetailId(int id);

}
