package com.fa.training.demo.service;

import com.fa.training.demo.entities.CompetencyRankingProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfileService {

    List<CompetencyRankingProfile> findAll();

    Page<CompetencyRankingProfile> getAll4Paging(Pageable pageable);

    CompetencyRankingProfile getOne(Integer id);
}
