package com.fa.training.demo.entities;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class ProfileWeight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column
    private int weight;

    @Column
    private String maxLevel;

    @ManyToOne
    @JoinColumn
    private DataSource dataSource;

    @ManyToOne
    @JoinColumn
    private CompetencyComponentDetail competencyComponentDetail;

    @ManyToOne
    @JoinColumn
    private CompetencyRankingPatternDetail competencyRankingPatternDetail;

    @ManyToOne
    private CompetencyRankingProfileDetail competencyRankingProfileDetail;
}
