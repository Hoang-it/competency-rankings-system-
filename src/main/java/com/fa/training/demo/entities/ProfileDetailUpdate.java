package com.fa.training.demo.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDetailUpdate {
    private int patternId;
    private float oldPoint;
    private int profileDetailId;
    private float newPoint;
    private int source;
    private int proficientId;
}
