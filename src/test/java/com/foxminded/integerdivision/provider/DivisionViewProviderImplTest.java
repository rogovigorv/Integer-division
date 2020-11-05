package com.foxminded.integerdivision.provider;

import com.foxminded.integerdivision.domain.DivisionStep;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DivisionViewProviderImplTest {

    private final DivisionViewProviderImpl divisionViewProvider = new DivisionViewProviderImpl();

    @Test
    void makeDivisionVisualization() {
        final List<DivisionStep> steps = Arrays.asList(new DivisionStep(53, 48, 5),
                new DivisionStep(52, 48, 4));
        final String actual = divisionViewProvider.provideViewDivision(532, 8, steps);
        final String expected =  "_532|8\n" +
                                 " 48 |--\n" +
                                 " -- |66\n" +
                                 " _52\n" +
                                 "  48\n" +
                                 "  --\n" +
                                 "   4";

        assertEquals(expected, actual);
    }

    @Test
    void makeDivisionVisualizationWhenDividendEqualsZero() {
        final List<DivisionStep> steps = Arrays.asList(new DivisionStep(0, 123, 0));
        final String actual = divisionViewProvider.provideViewDivision(0, 123, steps);
        final String expected = "_0  |123\n" +
                                " 123|---\n" +
                                " ---|0\n" +
                                "   0";

        assertEquals(expected, actual);
    }

    @Test
    void makeDivisionVisualizationWhenDividendEqualsDivider() {
        final List<DivisionStep> steps = Arrays.asList(new DivisionStep(123, 123, 0));
        final String actual = divisionViewProvider.provideViewDivision(123, 123, steps);
        final String expected = "_123|123\n" +
                                " 123|---\n" +
                                " ---|1\n" +
                                "   0";

        assertEquals(expected, actual);
    }

    @Test
    void makeDivisionVisualizationWhenModIsTwoDigit() {
        final List<DivisionStep> steps = Arrays.asList(new DivisionStep(157, 150, 7),
                new DivisionStep(76, 60, 16),
                new DivisionStep(163, 150, 13));
        final String actual = divisionViewProvider.provideViewDivision(15763, 30, steps);
        final String expected = "_15763|30\n" +
                                " 150  |---\n" +
                                " ---  |525\n" +
                                "  _76\n" +
                                "   60\n" +
                                "   --\n" +
                                "  _163\n" +
                                "   150\n" +
                                "   ---\n" +
                                "    13";

        assertEquals(expected, actual);
    }

    @Test
    void makeDivisionVisualizationWhenDividendIs11AndDividerIs10() {
        final List<DivisionStep> steps = Arrays.asList(new DivisionStep(11, 10, 1));
        final String actual = divisionViewProvider.provideViewDivision(11, 10, steps);
        final String expected = "_11|10\n" +
                                " 10|-\n" +
                                " --|1\n" +
                                "  1";

        assertEquals(expected, actual);
    }

    @Test
    void makeDivisionVisualizationWhenDividendIs100012AndDividerIs5() {
        final List<DivisionStep> steps = Arrays.asList(new DivisionStep(10, 10, 0),
                new DivisionStep(12, 10, 2));
        final String actual = divisionViewProvider.provideViewDivision(100012, 5, steps);
        final String expected = "_100012|5\n" +
                                " 10    |-----\n" +
                                " --    |20002\n" +
                                "    _12\n" +
                                "     10\n" +
                                "     --\n" +
                                "      2";

        assertEquals(expected, actual);
    }
}
