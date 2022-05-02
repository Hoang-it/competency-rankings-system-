package com.fa.training.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDetailsDTO {
    private String patternDetailName;
    private String sourceRequired;
    private int patternDetailId;
    private int profileDetailId;
    private int sourceId;
    private int proficientSelfPointId;
    private int proficientReviewPointId;
    private int proficientMin;
    private int proficientMax;
    private int componentDetailId;
    private float point;
    private float reviewPoint;
    private float selfPoint;
    private Double pointOfPattern;
    private Double patternWeight;
}
