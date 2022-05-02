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
@JsonIgnoreProperties(value = {"competencyRankingProfileDetails"})
public class CompetencyRankingProfile extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rankingProfileId;

    @Column(name = "title")
    private String title;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Period period;

    @ManyToOne
    private StatusType statusType;

    @ManyToOne
    private CompetencyRankingPattern pattern;

    @OneToMany(mappedBy = "competencyRankingProfile",cascade = CascadeType.ALL)
    private List<CompetencyRankingProfileDetail> competencyRankingProfileDetails;

    @OneToOne(mappedBy = "competencyRankingProfile")
    private CompetencyResult competencyResult;
}
