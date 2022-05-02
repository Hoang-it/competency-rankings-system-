package com.fa.training.demo.service;

import com.fa.training.demo.entities.JobRank;
import com.fa.training.demo.repository.JobRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JobRankServiceImpl implements JobRankService{

    @Autowired
    private JobRankRepository repository;

    @Override
    public JobRank findJobRank(Integer id) {
        return repository.getOne(id);
    }

    @Override
    public void save(JobRank jobRank) {
        repository.save(jobRank);
    }
}
