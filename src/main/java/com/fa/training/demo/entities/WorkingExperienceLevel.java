package com.fa.training.demo.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class WorkingExperienceLevel extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int workingExperienceLevelId;

    @Column
    private int workingExperienceLevelLevel;

    @Column
    private String workingExperienceLevelName;

    @Column
    private String workingExperienceLevelDescription;
}
