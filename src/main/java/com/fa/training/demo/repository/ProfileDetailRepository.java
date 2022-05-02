package com.fa.training.demo.repository;

import com.fa.training.demo.entities.CompetencyRankingProfile;
import com.fa.training.demo.entities.CompetencyRankingProfileDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileDetailRepository extends JpaRepository<CompetencyRankingProfileDetail, Integer> {
    List<CompetencyRankingProfileDetail> findAllByCompetencyRankingProfile(CompetencyRankingProfile profile);
}
