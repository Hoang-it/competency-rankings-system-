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
@JsonIgnoreProperties(value = {"employees", "competencyRankingPatterns"})
public class JobTitle extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobTitleId;

    @Column
    private String jobTitleName;

    @OneToMany(mappedBy = "jobTitle")
    private List<Employee> employees;

    @OneToMany(mappedBy = "jobTitle")
    private List<CompetencyRankingPattern> competencyRankingPatterns;
}
