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
@JsonIgnoreProperties(value = {"competencyComponent", "patternDetails"})
public class CompetencyComponentDetail{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int componentDetailId;

    @Column
    private String componentDetailName;

    @Column
    private String componentDetailDescription;

    @ManyToOne
    @JoinColumn(name = "component_id"
            , foreignKey = @ForeignKey(name = "FK_ComponentDetails_Component"))
    private CompetencyComponent competencyComponent;

    @OneToMany(mappedBy = "competencyComponentDetail")
    private List<ProficiencyLevel> proficiencyLevels;

    @OneToMany(mappedBy = "competencyComponentDetail", cascade = CascadeType.REMOVE)
    private List<CompetencyRankingPatternDetail> patternDetails;

}
