package com.foxminded.integerdivision;

import com.foxminded.integerdivision.domain.DivisionStep;
import com.foxminded.integerdivision.provider.DivisionMathProvider;
import com.foxminded.integerdivision.provider.DivisionViewProvider;
import com.foxminded.integerdivision.validator.Validator;
import java.util.List;

public class DivisionCalculator {
    private final Validator validator;
    private final DivisionMathProvider divisionMathProvider;
    private final DivisionViewProvider divisionViewProvider;

    public DivisionCalculator(Validator validator, DivisionMathProvider divisionMathProvider,
                              DivisionViewProvider divisionViewProvider) {
        this.validator = validator;
        this.divisionMathProvider = divisionMathProvider;
        this.divisionViewProvider = divisionViewProvider;
    }

    public String calculateDivision(int dividend, int divider) {
        validator.validate(dividend, divider);

        List<DivisionStep> steps = divisionMathProvider.provideDivisionMath(dividend, divider);

        return divisionViewProvider.provideViewDivision(dividend, divider, steps);
    }
}
