package com.fa.training.demo.service;

import com.fa.training.demo.entities.CompetencyRankingProfile;
import com.fa.training.demo.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService{

    @Autowired
    private ProfileRepository repository;

    @Override
    public List<CompetencyRankingProfile> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<CompetencyRankingProfile> getAll4Paging(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public CompetencyRankingProfile getOne(Integer id) {
        return repository.getOne(id);
    }
}
