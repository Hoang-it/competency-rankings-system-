package com.fa.training.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class RankingRequirementPattern extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rankingRequirementPatternId;

    @Column
    private int maxProficiencyLevelId;

    @ManyToOne
    private CompetencyRankingPatternDetail competencyRankingPatternDetail;

    @ManyToOne
    private JobRank jobRank;

    @ManyToOne
    private ProficiencyLevel proficiencyLevel;
}
