package com.fa.training.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ProfileDetailAndComponentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column
    private int rankingWeight;

    @ManyToOne
    private DataSource dataSource;

    @ManyToOne
    private CompetencyComponentDetail competencyComponentDetail;

    @ManyToOne
    @JoinColumn
    private CompetencyRankingProfileDetail competencyRankingProfileDetail;
}
