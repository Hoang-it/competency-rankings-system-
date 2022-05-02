package com.fa.training.demo.service;

import com.fa.training.demo.entities.CompetencyRankingProfile;
import com.fa.training.demo.entities.CompetencyRankingProfileDetail;

import java.util.List;

public interface ProfileDetailService {

    List<CompetencyRankingProfileDetail> findAll(CompetencyRankingProfile competencyRankingProfile);
}
