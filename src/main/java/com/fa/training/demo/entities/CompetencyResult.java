package com.fa.training.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CompetencyResult extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int competencyResultId;

    @Column
    private int indexLevelId;

    @Column
    private int approvedRankId;

    @ManyToOne
    private Employee employee;

    @OneToOne
    private CompetencyRankingProfile competencyRankingProfile;

    @ManyToOne
    private JobRank jobRank;
}
