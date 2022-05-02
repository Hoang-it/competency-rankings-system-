package com.fa.training.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"competencyRankingProfiles", "competencyRankingPatterns"})
public class Period extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "period_id")
    private int periodId;

    @Column(name = "period_name")
    private String periodName;

    @OneToMany(mappedBy = "period")
    private Set<CompetencyRankingProfile> competencyRankingProfiles;

    @OneToMany(mappedBy = "period")
    private Set<CompetencyRankingPattern> competencyRankingPatterns;
}
