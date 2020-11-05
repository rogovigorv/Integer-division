package com.foxminded.integerdivision;

import com.foxminded.integerdivision.provider.DivisionMathProviderImpl;
import com.foxminded.integerdivision.provider.DivisionMathProvider;
import com.foxminded.integerdivision.provider.DivisionViewProviderImpl;
import com.foxminded.integerdivision.provider.DivisionViewProvider;
import com.foxminded.integerdivision.validator.Validator;
import com.foxminded.integerdivision.validator.ValidatorImpl;
import java.util.Scanner;

public class DivisionConsoleApplication {
    public static void main(String[] args) {
        Validator validator = new ValidatorImpl();
        DivisionMathProvider divisionMathProvider = new DivisionMathProviderImpl();
        DivisionViewProvider divisionViewProvider = new DivisionViewProviderImpl();

        DivisionCalculator divisionCalculator =
                new DivisionCalculator(validator, divisionMathProvider, divisionViewProvider);
        Scanner scanner = new Scanner(System.in);

        int dividend = scanner.nextInt();
        int divider = scanner.nextInt();

        String divisionResult = divisionCalculator.calculateDivision(dividend, divider);

        System.out.println(divisionResult);
    }
}
