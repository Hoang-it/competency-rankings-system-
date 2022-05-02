package com.fa.training.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class LanguageProficiencyLevel extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int languageProficiencyLevelId;

    @Column
    private int languageProficiencyLevel;

    @Column
    private String languageProficiencyLevelName;

    @Column
    private String languageProficiencyLevelDescription;
}
