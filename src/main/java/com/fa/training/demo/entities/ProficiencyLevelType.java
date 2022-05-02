package com.fa.training.demo.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class ProficiencyLevelType extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int proficiencyLevelTypeId;

    @Column
    private String proficiencyLevelTypeName;

    @Column
    private String proficiencyLevelTypeDescription;

}
