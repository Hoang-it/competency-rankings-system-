package com.fa.training.demo.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ProjectKPILevel extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectKPILevelId;

    @Column
    private int projectKPILevelLevel;

    @Column
    private String projectKPILevelName;

    @Column
    private String projectKPILevelDescription;
}
