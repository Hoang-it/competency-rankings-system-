package com.fa.training.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"competencyRankingPattern"})
public class CompetencyRankingPatternDetail extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int competencyPatternDetailId;

    @Column
    private int orderNumber;

    @Column
    private Double weightDetail;

    @Column
    private Double pointOfPatternDetail;

    @Column
    private String maxLevel;

    @Column
    private String optional;

    @ManyToOne
    private CompetencyRankingPattern competencyRankingPattern;

    @ManyToOne
    private CompetencyComponentDetail competencyComponentDetail;



}
