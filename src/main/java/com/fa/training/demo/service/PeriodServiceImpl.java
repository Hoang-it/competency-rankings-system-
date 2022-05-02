package com.fa.training.demo.service;

import com.fa.training.demo.entities.Period;
import com.fa.training.demo.repository.PeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PeriodServiceImpl implements PeriodService {

    @Autowired
    private PeriodRepository periodRepository;

    @Override
    public List<Period> findAll() {
        return periodRepository.findAll();
    }

    @Override
    public Period save(Period period) {
        return periodRepository.save(period);
    }

    @Override
    public Period findById(int id) {
        return periodRepository.getOne(id);
    }

    @Override
    public boolean deletePeriod(int id) {
        boolean checkRemoval = findAll().removeIf(period -> (period.getPeriodId() == id));
        return checkRemoval;
    }

    @Override
    public Page<Period> findAllPeriods(Pageable pageable) {
        return periodRepository.findAllPeriods(pageable);
    }

    @Override
    public Page<Period> getAllPeriods(Pageable pageable, String period) {
        return periodRepository.findAllByPeriodNameContaining(pageable, period);
    }
}
