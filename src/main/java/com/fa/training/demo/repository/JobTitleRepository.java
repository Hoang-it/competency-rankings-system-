package com.fa.training.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fa.training.demo.entities.JobTitle;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle,Integer> {

}
