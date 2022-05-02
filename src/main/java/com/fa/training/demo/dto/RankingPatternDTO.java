package com.fa.training.demo.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RankingPatternDTO {

    private Integer patternId;
    private String patternName;
    private String period;
    private LocalDate created;
    private String status;

}
