package com.fa.training.demo.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CompetencyReviewRanking extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int competencyReviewRankingId;

    @Column
    private String reviewRankingNote;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    @JoinColumn
    private ProficiencyLevel proficiencyLevel;

    @ManyToOne
    private CompetencyRankingProfileDetail competencyRankingProfileDetail;
}
