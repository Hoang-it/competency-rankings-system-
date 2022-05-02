package com.fa.training.demo.service;

import com.fa.training.demo.entities.CompetencyRankingPatternDetail;
import com.fa.training.demo.repository.PatternDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatternDetailServiceImpl implements PatternDetailService{

    @Autowired
    private PatternDetailRepository patternDetailRepository;
    @Override
    public CompetencyRankingPatternDetail save(CompetencyRankingPatternDetail competencyRankingPatternDetail) {
        return patternDetailRepository.save(competencyRankingPatternDetail);
    }

    @Override
    public CompetencyRankingPatternDetail findById(int id) {
        return patternDetailRepository.getOne(id);
    }

    @Override
    public CompetencyRankingPatternDetail findByComponentDetailIdAndPatternId(int componentId, int patternId) {
        return patternDetailRepository.findByCompetencyComponentDetailComponentDetailIdAndCompetencyRankingPatternCompetencyRankingPatternId(componentId,patternId);
    }

    @Override
    public void deleteById(Integer patternDetailId) {

    }

    @Override
    public void delete(CompetencyRankingPatternDetail competencyRankingPatternDetail){
        patternDetailRepository.delete(competencyRankingPatternDetail);
    }


}
