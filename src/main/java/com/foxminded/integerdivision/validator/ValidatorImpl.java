package com.foxminded.integerdivision.validator;

public class ValidatorImpl implements Validator {

    @Override
    public void validate(int dividend, int divider) {
        if (divider == 0) {
            throw new IllegalArgumentException(String.format("Division by [%s].", divider));
        }
        if (dividend < 0) {
            throw new IllegalArgumentException(String.format("The dividend is less than 0, value [%s].", dividend));
        }
        if (divider < 0) {
            throw new IllegalArgumentException(String.format("The divider is less than 0, value [%s].", divider));
        }
    }
}
