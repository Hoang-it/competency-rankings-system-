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
@JsonIgnoreProperties(value = "competencyRankingProfiles")
public class StatusType extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusTypeId;

    @Column
    private String statusTypeName;

    @OneToMany (mappedBy = "statusType")
    private List<CompetencyRankingProfile> competencyRankingProfiles;
}
