package com.fa.training.demo.repository;

import com.fa.training.demo.entities.PersonalContributionLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalContributeRepository extends JpaRepository<PersonalContributionLevel, Integer> {
}
