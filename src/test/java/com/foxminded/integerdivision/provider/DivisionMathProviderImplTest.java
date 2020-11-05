package com.foxminded.integerdivision.provider;

import com.foxminded.integerdivision.domain.DivisionStep;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DivisionMathProviderImplTest {

    private final DivisionMathProviderImpl divisionMathProvider = new DivisionMathProviderImpl();

    @Test
    void makeProvideDivisionMathReturnsListOfDivisionSteps() {
        final List<DivisionStep> actual = divisionMathProvider.provideDivisionMath(532, 8);
        final List<DivisionStep> expected = Arrays.asList(new DivisionStep(53, 48, 5),
                new DivisionStep(52, 48, 4));

        assertEquals(expected, actual);
    }

    @Test
    void makeProvideDivisionMathWhenDividendIs0AndDividerIs10() {
        final List<DivisionStep> actual = divisionMathProvider.provideDivisionMath(0, 10);
        final List<DivisionStep> expected = Arrays.asList(new DivisionStep(0, 0, 0));

        assertEquals(expected, actual);
    }

    @Test
    void makeProvideDivisionMathWhenDividendIs11AndDividerIs10() {
        final List<DivisionStep> actual = divisionMathProvider.provideDivisionMath(11, 10);
        final List<DivisionStep> expected = Arrays.asList(new DivisionStep(11, 10, 1));

        assertEquals(expected, actual);
    }

    @Test
    void makeProvideDivisionMathWhenDividendIs100012AndDividerIs5() {
        final List<DivisionStep> actual = divisionMathProvider.provideDivisionMath(100012, 5);
        final List<DivisionStep> expected = Arrays.asList(new DivisionStep(10, 10, 0),
                new DivisionStep(12, 10, 2));

        assertEquals(expected, actual);
    }
}
