package com.fa.training.demo.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ResponsibilityProficiencyLevel extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int responsibilityProficiencyLevelId;

    @Column
    private int responsibilityProficiencyLevel;

    @Column
    private String responsibilityProficiencyLevelName;

    @Column
    private String responsibilityProficiencyLevelDescription;
}
