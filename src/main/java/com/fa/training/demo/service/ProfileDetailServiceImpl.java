package com.fa.training.demo.service;

import com.fa.training.demo.entities.CompetencyRankingProfile;
import com.fa.training.demo.entities.CompetencyRankingProfileDetail;
import com.fa.training.demo.repository.ProfileDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProfileDetailServiceImpl implements ProfileDetailService{

    @Autowired
    private ProfileDetailRepository repository;

    @Override
    public List<CompetencyRankingProfileDetail> findAll(CompetencyRankingProfile competencyRankingProfile) {
        return repository.findAllByCompetencyRankingProfile(competencyRankingProfile);
    }
}
