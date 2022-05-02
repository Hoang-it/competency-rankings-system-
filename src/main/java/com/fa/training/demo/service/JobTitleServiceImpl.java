package com.fa.training.demo.service;

import com.fa.training.demo.entities.JobTitle;
import com.fa.training.demo.repository.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JobTitleServiceImpl implements JobTitleService{

    @Autowired
    JobTitleRepository jobTitleRepository;
    @Override
    public List<JobTitle> findAll() {
        return jobTitleRepository.findAll();
    }

    @Override
    public JobTitle save(JobTitle jobTitle) {
        return jobTitleRepository.save(jobTitle);
    }

    @Override
    public JobTitle findById(int id) {
        return jobTitleRepository.getOne(id);
    }
}
