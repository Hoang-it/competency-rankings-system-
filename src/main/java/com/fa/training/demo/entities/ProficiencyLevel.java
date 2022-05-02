package com.fa.training.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"competencyComponentDetail"})
public class ProficiencyLevel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int proficiencyLevelId;

    @Column
    private String proficiencyLevelName;

    @Column
    private int proficiencyLevel;

    @Column
    private Double proficiencyLevelWeight;

    @ManyToOne
    @JoinColumn
    private CompetencyComponentDetail competencyComponentDetail;

    @OneToMany(mappedBy = "proficiencyLevel")
    private List<CompetencyReviewRanking> competencyReviewRankings;

    @OneToMany(mappedBy = "proficiencyLevel")
    private List<RankingRequirementPattern> rankingRequirementPatterns;
    
}
