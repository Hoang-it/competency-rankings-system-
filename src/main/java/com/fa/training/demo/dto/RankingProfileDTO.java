package com.fa.training.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RankingProfileDTO {

    private Integer rankingProfileId;
    private String jobTitle;
    private String period;
    private String fullName;
    private String submittedRank;
    private String status;

}
