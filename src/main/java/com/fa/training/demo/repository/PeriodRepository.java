package com.fa.training.demo.repository;

import com.fa.training.demo.entities.Period;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeriodRepository extends JpaRepository <Period, Integer>{
    Optional<Period> findByPeriodName(String periodName);

    @Query("SELECT p FROM Period p")
    Page<Period> findAllPeriods(Pageable pageable);

//    @Query("SELECT p FROM Period p WHERE ")
    Page<Period> findAllByPeriodNameContaining(Pageable pageable, String periodName);
}
