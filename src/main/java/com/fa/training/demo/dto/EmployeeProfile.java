package com.fa.training.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeProfile {
    private int profileId;
    private String profileName;
    private LocalDate created;
    private String preriodName;
    private String statusName;
    private String rank;
}
