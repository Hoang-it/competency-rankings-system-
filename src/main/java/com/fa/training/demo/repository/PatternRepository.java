package com.fa.training.demo.repository;

import com.fa.training.demo.entities.CompetencyRankingPattern;
import com.fa.training.demo.entities.JobTitle;
import com.fa.training.demo.entities.Period;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatternRepository extends JpaRepository<CompetencyRankingPattern, Integer> {
    Optional<CompetencyRankingPattern> findByPatternName(String name);
    List<CompetencyRankingPattern> findByJobTitleAndPeriod(JobTitle job, Period period);

    @Query("SELECT u FROM CompetencyRankingPattern u")
    Page<CompetencyRankingPattern> findAllPattern(Pageable pageable);

    @Query("SELECT c FROM CompetencyRankingPattern c " +
            "INNER JOIN Period p ON c.period.periodId = p.periodId " +
            "INNER JOIN StatusType s ON c.statusType.statusTypeId = s.statusTypeId " +
            "WHERE c.patternName LIKE %:search% " +
            "OR p.periodName LIKE %:search% " +
            "OR c.created LIKE %:search% " +
            "OR s.statusTypeName LIKE %:search%")
    Page<CompetencyRankingPattern> findAllBySearch(@Param("search") String search, Pageable pageable);

    default Page<CompetencyRankingPattern> findAllBySearchAndCondition(String condition,
            String search, Pageable pageable, EntityManager entityManager) {
        String hql = "SELECT c FROM CompetencyRankingPattern c " +
                    "INNER JOIN Period p ON c.period.periodId = p.periodId " +
                    "INNER JOIN StatusType s ON c.statusType.statusTypeId = s.statusTypeId " +
                    "WHERE " + condition + " LIKE '%" + search + "%'";
        TypedQuery<CompetencyRankingPattern> query = entityManager.createQuery(hql, CompetencyRankingPattern.class);
        List<CompetencyRankingPattern> list = query.getResultList();
        final int start = (int)pageable.getOffset();
        final int end = Math.min(start + pageable.getPageSize(), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    };

}

