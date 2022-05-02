package com.fa.training.demo.repository;

import com.fa.training.demo.entities.CompetencyComponent;
import com.fa.training.demo.entities.CompetencyComponentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComponentDetailRepository extends JpaRepository<CompetencyComponentDetail, Integer> {
    CompetencyComponentDetail findByComponentDetailName(String name);

    List<CompetencyComponentDetail> findCompetencyComponentDetailByCompetencyComponent(CompetencyComponent component);

//    List<CompetencyComponentDetail>
}
