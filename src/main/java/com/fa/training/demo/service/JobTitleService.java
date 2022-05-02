package com.fa.training.demo.service;

import com.fa.training.demo.entities.JobTitle;

import java.util.List;
import java.util.Optional;

public interface JobTitleService {
    public List<JobTitle> findAll();

    public JobTitle save(JobTitle jobTitle);

    public JobTitle findById(int id);
}
