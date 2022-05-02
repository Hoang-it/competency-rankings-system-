package com.fa.training.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = "patternWeights")
public class CompetencyComponent{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int componentId;

    @Column
    private String componentName;

    @Column
    private String componentDescription;

    @Column
    private String idName;

    @OneToMany(mappedBy = "competencyComponent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CompetencyComponentDetail> competencyComponentDetail;

    @OneToMany(mappedBy = "competencyComponent", cascade = CascadeType.DETACH)
    private List<PatternWeight> patternWeights;


}
