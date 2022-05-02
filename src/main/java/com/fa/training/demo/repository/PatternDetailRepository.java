package com.fa.training.demo.repository;

import com.fa.training.demo.entities.CompetencyRankingPatternDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatternDetailRepository extends JpaRepository<CompetencyRankingPatternDetail, Integer> {

    CompetencyRankingPatternDetail findByCompetencyComponentDetailComponentDetailIdAndCompetencyRankingPatternCompetencyRankingPatternId(int componentId, int patternId);

}
