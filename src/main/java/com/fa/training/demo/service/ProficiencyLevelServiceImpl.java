package com.fa.training.demo.service;

import com.fa.training.demo.entities.ProficiencyLevel;
import com.fa.training.demo.repository.ProficiencyLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProficiencyLevelServiceImpl implements  ProficiencyLevelService{
    @Autowired
    private ProficiencyLevelRepository proficiencyLevelRepository;

    @Override
    public List<ProficiencyLevel> findByProficiencyLevelNameAndComponentDetailId(String name, int id) {
        return proficiencyLevelRepository.findByProficiencyLevelNameAndCompetencyComponentDetailComponentDetailId(name, id);
    }

    @Override
    public List<ProficiencyLevel> findByComponentDetailId(int id) {
        return proficiencyLevelRepository.findByCompetencyComponentDetailComponentDetailId(id);
    }
}
