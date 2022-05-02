package com.fa.training.demo.service;

import com.fa.training.demo.entities.CompetencyRankingPattern;
import com.fa.training.demo.repository.PatternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
public class PatternServiceImpl implements PatternService{

    @Autowired
    private PatternRepository patternRepository;

    @Autowired
    private EntityManager entityManager;

    public CompetencyRankingPattern save(CompetencyRankingPattern competencyRankingPattern){
        return patternRepository.save(competencyRankingPattern);
    }

    @Override
    public CompetencyRankingPattern findById(int patternId) {
        return patternRepository.getOne(patternId);
    }

    @Override
    public Page<CompetencyRankingPattern> findAll(Pageable pageable) {
        return patternRepository.findAllPattern(pageable);
    }

    @Override
    public Page<CompetencyRankingPattern> findAllBySearch(String search, Pageable pageable) {
        return patternRepository.findAllBySearch(search, pageable);
    }

    @Override
    public Page<CompetencyRankingPattern> findAllBySearchAndCondition(String condition, String search, Pageable pageable) {
        return patternRepository.findAllBySearchAndCondition(condition, search, pageable, entityManager);
    }

    @Override
    public void deletePatternById(Integer id) {
        patternRepository.deleteById(id);
    }
}
