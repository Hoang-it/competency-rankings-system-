package com.fa.training.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PersonalContributionLevel extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personalContributionLevelId;

    @Column
    private int personalContributionLevelLevel;

    @Column
    private String personalContributionLevelName;

    @Column
    private String personalContributionLevelDescription;
}
