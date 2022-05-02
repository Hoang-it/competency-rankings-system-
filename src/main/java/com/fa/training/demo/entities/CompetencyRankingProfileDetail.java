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
@JsonIgnoreProperties(value = {"profileDetailAndComponentDetails", "competencyRankingProfile"})
public class CompetencyRankingProfileDetail extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rankingProfileDetailId;

    @Column
    private int proficientSeltPointId;

    @Column
    private int proficientReviewPoinId;

    @ManyToOne
    private CompetencyRankingProfile competencyRankingProfile;

    @ManyToOne
    private CompetencyRankingPatternDetail competencyRankingPatternDetail;

    @ManyToOne
    private DataSource source;

    @OneToMany(mappedBy = "competencyRankingProfileDetail")
    private List<ProfileDetailAndComponentDetail> profileDetailAndComponentDetails;

    @Column
    private  float selfPoint;

    @Column
    private  float reviewPoint;


}
