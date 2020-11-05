package com.foxminded.integerdivision.provider;

import com.foxminded.integerdivision.domain.DivisionStep;
import java.util.Collections;
import java.util.List;

public class DivisionViewProviderImpl implements DivisionViewProvider {
    private static final String TAB = " ";
    private static final String HORIZONTAL_DIVIDING_LINE = "-";
    private static final String MINUS = "_";
    private static final String VERTICAL_DIVIDING_LINE = "|";
    private static final String LINE_BREAK = "\n";
    private static final String EMPTY_CHAR_DELIMITER = "";

    @Override
    public String provideViewDivision(int dividend, int divider, List<DivisionStep> steps) {
        String visualResultOfDivision;

        if (dividend == 0) {
            visualResultOfDivision = toString(divider);
        } else if (dividend == divider) {
            visualResultOfDivision = toString(dividend, divider);
        } else {
            visualResultOfDivision = toString(dividend, divider, steps);
        }

        return visualResultOfDivision;
    }

    private String toString(int divider) {
        StringBuilder result = new StringBuilder();
        StringBuilder formatter = new StringBuilder();
        int dividerLength = String.valueOf(divider).length();
        int quotient = 0;

            result.append(MINUS);
            result.append(0);
            result.append(addLine(TAB, dividerLength - 1));
            result.append(VERTICAL_DIVIDING_LINE);
            result.append(divider);
            result.append(LINE_BREAK);
            formatter.append(addLine(HORIZONTAL_DIVIDING_LINE, dividerLength));
            result.append(addMainString(divider, quotient, formatter));
            result.append(TAB);
            result.append(addLine(TAB, dividerLength - 1));
            result.append(0);

        return String.valueOf(result);
    }

    private String toString(int dividend, int divider) {
        StringBuilder result = new StringBuilder();
        StringBuilder formatter = new StringBuilder();
        int dividerLength = String.valueOf(divider).length();
        int quotient = 1;

            result.append(addHighString(dividend, divider));
            formatter.append(addLine(HORIZONTAL_DIVIDING_LINE, dividerLength));
            result.append(addMainString(divider, quotient, formatter));
            result.append(addLine(TAB, dividerLength));
            result.append(0);

        return String.valueOf(result);
    }

    private String toString(int dividend, int divider, List<DivisionStep> steps) {
        StringBuilder result = new StringBuilder();
        StringBuilder formatter = new StringBuilder();
        String currentString;
        int shifter;
        int quotientLength = String.valueOf(dividend/divider).length();
        int firstMultiplyResultLength = String.valueOf(steps.get(0).getMultiplyResult()).length();
        int lengthDifference = String.valueOf(steps.get(0).getRemainderNumber()).length() -
                String.valueOf(steps.get(0).getMultiplyResult()).length();
        int lengthDifferencePlusStep = String.valueOf(steps.get(0).getRemainderNumber()).length() + 1 -
                String.valueOf(steps.get(0).getMultiplyResult()).length();

            result.append(addHighString(dividend, divider));

            formatter.append(addLine(TAB, lengthDifference));
            currentString = TAB + formatter.toString() + steps.get(0).getMultiplyResult();
            currentStringEnd(result, formatter, currentString, dividend);
            currentString = formatter.toString() + VERTICAL_DIVIDING_LINE;

            result.append(currentString);
            result.append(addLine(HORIZONTAL_DIVIDING_LINE, quotientLength));
            result.append(LINE_BREAK);

            formatter.delete(0, formatter.toString().length());
            formatter.append(addLine(TAB, lengthDifferencePlusStep));
            formatter.append(addLine(HORIZONTAL_DIVIDING_LINE, firstMultiplyResultLength));

            currentString = formatter.toString();
            shifter = currentString.length() - 2;
            currentStringEnd(result, formatter, currentString, dividend);
            currentString = formatter.toString() + VERTICAL_DIVIDING_LINE;

            result.append(currentString);
            result.append(dividend/divider);
            result.append(LINE_BREAK);
            result.append(columnBuilding(dividend, steps, shifter));

            return String.valueOf(result);
    }

    private String columnBuilding(int dividend, List<DivisionStep> steps, int shifter) {
        StringBuilder column = new StringBuilder();
        String[] separatedDividend = separateDividend(dividend);

        for (int i = 1; i < steps.size(); i++) {
            if (steps.get(i - 1).getMod() != 0) {
                if (singleDigit(steps.get(i - 1).getRemainderNumber(), steps.get(i - 1).getMultiplyResult())) {
                    column.append(columnFormat(shifter, shifter + 1, shifter + 1, steps, i));
                }
                if (!singleDigit(steps.get(i - 1).getRemainderNumber(), steps.get(i - 1).getMultiplyResult())) {
                    column.append(columnFormat(shifter - 1, shifter, shifter, steps, i));
                }

            } else {
                for (int j = 1; j < separatedDividend.length - 1; j++) {
                    if (Integer.parseInt(separatedDividend[j + 1]) == 0) {
                        column.append(TAB);
                    }
                }
                column.append(columnFormat(shifter + 1, shifter + 4, shifter + 4, steps, i));
            }
            shifter++;
        }
        column.append(remainderDemolition(dividend, steps));
        column.append(steps.get(steps.size() - 1).getMod());

        return String.valueOf(column);
    }

    private String[] separateDividend(int dividend) {
        return String.valueOf(dividend).split(EMPTY_CHAR_DELIMITER);
    }

    private String tabRepeat(int shifter) {
        return String.join(EMPTY_CHAR_DELIMITER, Collections.nCopies(shifter, TAB));
    }

    private String stepFinish(int multiplyResult) {
        return String.join(EMPTY_CHAR_DELIMITER, Collections.nCopies(String.valueOf(multiplyResult).length(),
                HORIZONTAL_DIVIDING_LINE));
    }

    private boolean singleDigit(int remainderNumber, int multiplyResult) {
        return remainderNumber - multiplyResult < 10;
    }

    private String remainderDemolition(int dividend, List<DivisionStep> steps) {
        return String.join(EMPTY_CHAR_DELIMITER, Collections.nCopies((String.valueOf(dividend).length() + 1) -
                String.valueOf(steps.get(steps.size() - 1).getMod()).length(), TAB));
    }

    private StringBuilder addLine(String line, int times) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < times; i++) {
            result.append(line);
        }
        return result;
    }

    private StringBuilder addHighString(int dividend, int divider) {
        StringBuilder highString = new StringBuilder();
        highString.append(MINUS);
        highString.append(dividend);
        highString.append(VERTICAL_DIVIDING_LINE);
        highString.append(divider);
        highString.append(LINE_BREAK);

        return highString;
    }

    private StringBuilder addMainString(int divider, int quotient, StringBuilder formatter) {
        StringBuilder mainString = new StringBuilder();
        mainString.append(TAB);
        mainString.append(divider);
        mainString.append(VERTICAL_DIVIDING_LINE);
        mainString.append(formatter);
        mainString.append(LINE_BREAK);
        mainString.append(TAB);
        mainString.append(formatter);
        mainString.append(VERTICAL_DIVIDING_LINE);
        mainString.append(quotient);
        mainString.append(LINE_BREAK);

        return mainString;
    }

    private StringBuilder columnFormat(int firstShifter, int secondShifter, int thirdShifter,
                                       List<DivisionStep> steps, int iteration) {
        StringBuilder result = new StringBuilder();
        result.append(tabRepeat(firstShifter));
        result.append(MINUS);
        result.append(steps.get(iteration).getRemainderNumber());
        result.append(LINE_BREAK);
        result.append(tabRepeat(secondShifter));
        result.append(steps.get(iteration).getMultiplyResult());
        result.append(LINE_BREAK);
        result.append(tabRepeat(thirdShifter));
        result.append(stepFinish(steps.get(iteration).getMultiplyResult()));
        result.append(LINE_BREAK);

        return result;
    }

    private void currentStringEnd(StringBuilder result, StringBuilder formatter, String currentString, int dividend) {
        result.append(currentString);
        formatter.delete(0, formatter.toString().length());
        formatter.append(addLine(TAB, String.valueOf(dividend).length() + 1 - currentString.length()));
    }
}
