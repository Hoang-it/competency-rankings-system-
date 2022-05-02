package com.fa.training.demo.service;

import com.fa.training.demo.entities.CompetencyComponent;

import java.util.List;

public interface ComponentService {
    CompetencyComponent findById(int id);

    List<CompetencyComponent> findAll();
}
