package com.fa.training.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class RoleProficiencyLevel extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleProficiencyLevelId;

    @Column
    private int roleProficiencyLevel;

    @Column
    private String roleProficiencyLevelName;

    @Column
    private String roleProficiencyLevelDescription;

}
