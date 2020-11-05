package com.foxminded.integerdivision.domain;

import java.util.Objects;

public class DivisionStep {
    private final int remainderNumber;
    private final int multiplyResult;
    private final int mod;

    public DivisionStep(int remainderNumber, int multiplyResult, int mod) {
        this.remainderNumber = remainderNumber;
        this.multiplyResult = multiplyResult;
        this.mod = mod;
    }

    public int getRemainderNumber() {
        return this.remainderNumber;
    }

    public int getMultiplyResult() {
        return this.multiplyResult;
    }

    public int getMod() {
        return this.mod;
    }

    @Override
    public boolean equals(Object otherDivisionStep) {
        if (this == otherDivisionStep) {
            return true;
        }
        if (otherDivisionStep == null || getClass() != otherDivisionStep.getClass()) {
            return false;
        }
        DivisionStep divisionStep = (DivisionStep) otherDivisionStep;
        return remainderNumber == divisionStep.remainderNumber &&
                multiplyResult == divisionStep.multiplyResult &&
                mod == divisionStep.mod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(remainderNumber, multiplyResult, mod);
    }

    @Override
    public String toString() {
        return "Minuend: " + remainderNumber + " Closest divider: "
                + multiplyResult + " Difference: " + mod;
    }
}
