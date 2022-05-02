package com.fa.training.demo.repository;

import com.fa.training.demo.entities.CompetencyRankingProfile;
import com.fa.training.demo.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<CompetencyRankingProfile, Integer> {
    List<CompetencyRankingProfile> findAllByEmployee(Employee employee);
}
