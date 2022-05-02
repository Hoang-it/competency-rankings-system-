package com.fa.training.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SoftSkillProficiencyLevel extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int softSkillProficiencyLevelId;

    @Column
    private int softSkillProficiencyLevel;

    @Column
    private String softSkillProficiencyLevelName;

    @Column
    private String softSkillProficiencyLevelDescription;
}
