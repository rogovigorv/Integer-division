package com.foxminded.integerdivision;

import com.foxminded.integerdivision.domain.DivisionStep;
import com.foxminded.integerdivision.provider.DivisionMathProvider;
import com.foxminded.integerdivision.provider.DivisionViewProvider;
import com.foxminded.integerdivision.validator.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DivisionCalculatorTest {
    private static final int DIVIDEND = 532;
    private static final int DIVIDER = 8;
    private static final List<DivisionStep> STEPS =
        Arrays.asList(new DivisionStep(53, 48, 5),
            new DivisionStep(52, 48, 4));

    @Mock
    private Validator validator;

    @Mock
    private DivisionMathProvider divisionMathProvider;

    @Mock
    private DivisionViewProvider divisionViewProvider;

    @InjectMocks
    private DivisionCalculator divisionCalculator;

    @Test
    void makeDivisionCalculatorWhenArgumentsAreCorrect() {
        final String expected = "Some string";

        doNothing().when(validator).validate(DIVIDEND, DIVIDER);
        when(divisionMathProvider.provideDivisionMath(DIVIDEND, DIVIDER)).thenReturn(STEPS);
        when(divisionViewProvider.provideViewDivision(DIVIDEND, DIVIDER, STEPS)).thenReturn(expected);

        final String actual = divisionCalculator.calculateDivision(DIVIDEND, DIVIDER);

        assertEquals(expected, actual);

        verify(validator).validate(DIVIDEND, DIVIDER);
        verify(divisionMathProvider).provideDivisionMath(DIVIDEND, DIVIDER);
        verify(divisionViewProvider).provideViewDivision(DIVIDEND, DIVIDER, STEPS);
    }

    @Test
    void makeDivisionCalculatorWhenArgumentsAreIncorrect() {
        doThrow(IllegalArgumentException.class).when(validator)
                .validate(DIVIDEND, DIVIDER);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                divisionCalculator.calculateDivision(DIVIDEND, DIVIDER));

        verify(validator).validate(DIVIDEND, DIVIDER);
        verifyNoMoreInteractions(divisionMathProvider, divisionViewProvider);
    }
}
