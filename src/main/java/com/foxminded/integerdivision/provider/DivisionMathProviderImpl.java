package com.foxminded.integerdivision.provider;

import com.foxminded.integerdivision.domain.DivisionStep;
import java.util.ArrayList;
import java.util.List;

public class DivisionMathProviderImpl implements DivisionMathProvider {
    private static final String EMPTY_CHAR_DELIMITER = "";

    @Override
    public List<DivisionStep> provideDivisionMath(int dividend, int divider) {
        ArrayList<DivisionStep> divisionSteps = new ArrayList<>();
        String[] separatedDividend = separator(dividend);
        StringBuilder remainder = new StringBuilder();

        for (int i = 0; i < separatedDividend.length; i++) {
            remainder.append(separatedDividend[i]);
            int remainderNumber = Integer.parseInt(remainder.toString());
            int multiplyResult = 0;
            int mod = 0;

                if (remainderNumber >= divider) {
                    mod = remainderNumber % divider;
                    multiplyResult = remainderNumber / divider * divider;
                    remainder.replace(0, remainder.length(), Integer.toString(mod));
                    divisionSteps.add(new DivisionStep(remainderNumber, multiplyResult, mod));
                }
                if (remainderNumber < divider && i == separatedDividend.length - 1) {
                    divisionSteps.add(new DivisionStep(remainderNumber, multiplyResult, mod));
                }
            }

        return divisionSteps;
    }

    private String[] separator(int dividend) {
        return String.valueOf(dividend).split(EMPTY_CHAR_DELIMITER);
    }
}
