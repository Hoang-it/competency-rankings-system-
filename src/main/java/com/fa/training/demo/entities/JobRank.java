package com.fa.training.demo.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class JobRank extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobRankId;

    @Column
    private String jobRankName;

    @OneToMany(mappedBy = "jobRank")
    private List<RankingRequirementPattern> rankingRequirementPatternList;

    @OneToMany(mappedBy = "jobRank")
    private List<CompetencyResult> resultList;
}
