package com.foxminded.integerdivision.validator;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidatorTest {
    private static final int DIVIDEND_GREATER_THAN_ZERO = 1;
    private static final int DIVIDEND_LESS_THAN_ZERO = -1;
    private static final int DIVIDER_GREATER_THAN_ZERO = 1;
    private static final int DIVIDER_EQUALS_ZERO = 0;
    private static final int DIVIDER_LESS_THAN_ZERO = -1;

    private final Validator validator = new ValidatorImpl();

    @Test
    void validateWhenDividerIsEqualsZero() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                validator.validate(DIVIDEND_GREATER_THAN_ZERO, DIVIDER_EQUALS_ZERO));

        assertThat(String.format("Division by [%s].", DIVIDER_EQUALS_ZERO),
                equalTo(exception.getMessage()));
    }

    @Test
    void validateWhenDividendIsLessThanZero() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                validator.validate(DIVIDEND_LESS_THAN_ZERO, DIVIDER_GREATER_THAN_ZERO));

        assertThat(String.format("The dividend is less than 0, value [%s].",
                DIVIDEND_LESS_THAN_ZERO), equalTo(exception.getMessage()));
    }

    @Test
    void validateWhenDividerIsLessThanZero() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                validator.validate(DIVIDEND_GREATER_THAN_ZERO, DIVIDER_LESS_THAN_ZERO));

        assertThat(String.format("The divider is less than 0, value [%s].",
                DIVIDER_LESS_THAN_ZERO), equalTo(exception.getMessage()));
    }

    @Test
    void validateWhenValidatorDoesNotThrowAnException() {

        assertDoesNotThrow( () -> validator.validate(DIVIDEND_GREATER_THAN_ZERO, DIVIDER_GREATER_THAN_ZERO));
    }
}
