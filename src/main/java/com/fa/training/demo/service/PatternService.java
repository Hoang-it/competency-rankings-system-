package com.fa.training.demo.service;

import com.fa.training.demo.entities.CompetencyRankingPattern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

public interface PatternService {

    CompetencyRankingPattern save(CompetencyRankingPattern competencyRankingPattern);

    CompetencyRankingPattern findById(int patternId);

    Page<CompetencyRankingPattern> findAll(Pageable pageable);

    Page<CompetencyRankingPattern> findAllBySearch(String search, Pageable pageable);

    Page<CompetencyRankingPattern> findAllBySearchAndCondition(String condition, String search, Pageable pageable);

    void deletePatternById(Integer id);
}
