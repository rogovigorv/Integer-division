package com.foxminded.integerdivision.provider;

import com.foxminded.integerdivision.domain.DivisionStep;
import java.util.List;

public interface DivisionMathProvider {
    List<DivisionStep> provideDivisionMath (int dividend, int divider);
}
