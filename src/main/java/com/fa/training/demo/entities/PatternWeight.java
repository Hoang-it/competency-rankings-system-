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
@JsonIgnoreProperties(value = "competencyRankingPattern")
public class PatternWeight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patternWeightId;

    @Column(name = "weight")
    private int weight;

    @Column(name = "order_number")
    private int order;

    @Column
    private Double basePoint;

    @ManyToOne
    @JoinColumn(name = "pattern_id")
    private CompetencyRankingPattern competencyRankingPattern;

    @ManyToOne
    @JoinColumn(name = "component_id")
    private CompetencyComponent competencyComponent;

}
