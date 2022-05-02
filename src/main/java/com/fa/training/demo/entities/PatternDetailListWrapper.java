package com.fa.training.demo.entities;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class PatternDetailListWrapper {
    private List<CompetencyRankingPatternDetail> patternDetails;

    public List<CompetencyRankingPatternDetail> getPatternDetails() {
        return patternDetails;
    }

    public void setPatternDetails(List<CompetencyRankingPatternDetail> patternDetails) {
        this.patternDetails = patternDetails;
    }
}
