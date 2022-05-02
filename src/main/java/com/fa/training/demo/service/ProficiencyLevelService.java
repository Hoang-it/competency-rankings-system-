package com.fa.training.demo.service;

import com.fa.training.demo.entities.ProficiencyLevel;

import java.util.List;

public interface ProficiencyLevelService {
    List<ProficiencyLevel> findByProficiencyLevelNameAndComponentDetailId(String name, int id);

    List<ProficiencyLevel> findByComponentDetailId(int id);
}
