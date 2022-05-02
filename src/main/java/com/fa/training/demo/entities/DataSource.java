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
@JsonIgnoreProperties(value = "competencyRankingProfileDetails")
public class DataSource extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dataSourceId;

    @Column
    private String dataSourceName;

    @OneToMany(mappedBy = "dataSource")
    private List<ProfileWeight> profileWeights;

    @OneToMany(mappedBy = "source")
    private List<CompetencyRankingProfileDetail> competencyRankingProfileDetails;
}
