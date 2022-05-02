package com.fa.training.demo.service;

import com.fa.training.demo.entities.CompetencyRankingPatternDetail;

public interface PatternDetailService {
    CompetencyRankingPatternDetail save(CompetencyRankingPatternDetail competencyRankingPatternDetail);

    CompetencyRankingPatternDetail findById(int id);

    CompetencyRankingPatternDetail findByComponentDetailIdAndPatternId(int componentId, int patternId);

    void deleteById(Integer patternDetailId);

    void delete(CompetencyRankingPatternDetail competencyRankingPatternDetail);
}
