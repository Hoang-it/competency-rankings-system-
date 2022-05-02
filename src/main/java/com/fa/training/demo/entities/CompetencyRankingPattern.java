package com.fa.training.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"profiles"})
public class CompetencyRankingPattern extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int competencyRankingPatternId;

    @Column(name = "pattern_name")
    private String patternName;

    @Column
    private Double pointOfPattern;

    @OneToMany(mappedBy = "competencyRankingPattern", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<CompetencyRankingPatternDetail> competencyRankingPatternDetail;

    @OneToMany(mappedBy = "competencyRankingPattern", cascade = CascadeType.REMOVE)
    private List<PatternWeight> patternWeights;

    @OneToMany(mappedBy = "pattern", cascade = CascadeType.REMOVE)
    private  List<CompetencyRankingProfile> profiles;

    @ManyToOne
    @JoinColumn
    private Period period;

    @ManyToOne
    @JoinColumn
    private JobTitle jobTitle;

    @ManyToOne
    private StatusType statusType;
}
