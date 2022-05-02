package com.fa.training.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrganizationContributionLevel extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int organizationContributionLevelId;

    @Column
    private int organizationContributionLevelLevel;

    @Column
    private String organizationContributionLevelName;

    @Column
    private String organizationContributionLevelDescription;
}
