package com.fa.training.demo.repository;

import com.fa.training.demo.entities.JobRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRankRepository extends JpaRepository<JobRank, Integer> {
    JobRank findByJobRankName(String name);
}
