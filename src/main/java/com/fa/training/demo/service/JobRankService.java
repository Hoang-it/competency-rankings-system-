package com.fa.training.demo.service;

import com.fa.training.demo.entities.JobRank;

public interface JobRankService {

    JobRank findJobRank (Integer id);

    void save(JobRank jobRank);
}
