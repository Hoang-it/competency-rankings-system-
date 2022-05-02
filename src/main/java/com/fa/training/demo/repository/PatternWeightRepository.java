package com.fa.training.demo.repository;

import com.fa.training.demo.entities.PatternWeight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatternWeightRepository extends JpaRepository<PatternWeight, Integer> {

    List<PatternWeight> findByCompetencyRankingPatternCompetencyRankingPatternId(int id);

    PatternWeight findByCompetencyRankingPatternCompetencyRankingPatternIdAndCompetencyComponent_ComponentId(int patternId, int componentId);
}
