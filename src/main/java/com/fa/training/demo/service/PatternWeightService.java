package com.fa.training.demo.service;
import com.fa.training.demo.entities.*;

import java.util.List;

public interface PatternWeightService {
    PatternWeight save(PatternWeight patternWeight);

    List<PatternWeight> findByPatternId(int id);

    PatternWeight findByPatternIdAndComponentId(int patternId, int componentId);
}
