package com.fa.training.demo.service;

import com.fa.training.demo.entities.Period;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PeriodService {
    public List<Period> findAll();

    public Period save(Period period);

    public Period findById(int id);

    boolean deletePeriod(int id);

    Page<Period> findAllPeriods(Pageable pageable);

    Page<Period> getAllPeriods(Pageable pageable, String period);
}
