package com.fa.training.demo.service;

import com.fa.training.demo.entities.CompetencyComponent;
import com.fa.training.demo.entities.CompetencyComponentDetail;

import java.util.List;

public interface ComponentDetailService {
    CompetencyComponentDetail findById(int id);

    List<CompetencyComponentDetail> findComponentDetailByComponent(CompetencyComponent component);

    CompetencyComponentDetail findByName(String name);

    CompetencyComponentDetail save(CompetencyComponentDetail componentDetail);
}
