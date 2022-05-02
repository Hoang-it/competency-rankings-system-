package com.fa.training.demo.service;

import com.fa.training.demo.entities.PatternWeight;
import com.fa.training.demo.repository.PatternWeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatternWeightServiceImpl implements PatternWeightService{

    @Autowired
    PatternWeightRepository patternWeightRepository;
    @Override
    public PatternWeight save(PatternWeight patternWeight) {
        return patternWeightRepository.save(patternWeight);
    }

    @Override
    public List<PatternWeight> findByPatternId(int id) {
        return patternWeightRepository.findByCompetencyRankingPatternCompetencyRankingPatternId(id);
    }

    @Override
    public PatternWeight findByPatternIdAndComponentId(int patternId, int componentId) {
        return patternWeightRepository.findByCompetencyRankingPatternCompetencyRankingPatternIdAndCompetencyComponent_ComponentId(patternId,componentId);
    }
}
