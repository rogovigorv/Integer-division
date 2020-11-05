package com.foxminded.integerdivision.provider;

import com.foxminded.integerdivision.domain.DivisionStep;
import java.util.List;

public interface DivisionViewProvider {
    String provideViewDivision(int dividend, int divider, List<DivisionStep> steps);
}
