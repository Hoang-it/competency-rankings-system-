package com.fa.training.demo.repository;

import com.fa.training.demo.entities.CompetencyRankingProfile;
import com.fa.training.demo.entities.CompetencyResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CompentencyResultRepository extends JpaRepository<CompetencyResult, Integer> {
    void deleteByCompetencyRankingProfile(CompetencyRankingProfile profile);
}
