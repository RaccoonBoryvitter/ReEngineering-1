package com.racconbor.reengineering;

/** 
 * Represents a human BMI in enumerated way.
 * 
 * @author Pavlo Shcherbatyi
 * @author https://github.com/RaccoonBoryvitter
 * @version 2.0
 * @since 2.0
*/
public enum BmiLevel {
    /**
     * Represents a result when BMI value is below zero (BMI < 0).
     */
    UNKNOWN,
    /**
     * Represents a result when BMI value is above zero
     * and below 18.5 (0 <= BMI < 18.5).
     */
    UNDERWEIGHT,
    /**
     * Represents a result when BMI value is above 18.5
     * and below 25 (18.5 <= BMI < 25).
     */
    HEALTHY,
    /**
     * Represents a result when BMI value is above 25
     * and below 30 (25 <= BMI < 30).
     */
    OVERWEIGHT,
    /**
     * Represents a result when BMI value is above 30 (BMI < 30).
     */
    OBESITY;


    /**
     * Creates an enum instance based on BMI value
     * 
     * @param bmiValue the BMI value in kilograms per square meter (kg/m^2).
     * @return An instance of BmiLevel enum
     */
    public static BmiLevel fromBmiValue(double bmiValue) {
        if (bmiValue >= 18.5 && bmiValue < 25) {
            return HEALTHY;
        }
        if (bmiValue >= 25 && bmiValue < 30) {
            return OVERWEIGHT;
        }
        if (bmiValue >= 30) {
            return OBESITY;
        }
        if (bmiValue >= 0 && bmiValue < 18.5) {
            return UNDERWEIGHT;
        }

        return UNKNOWN;
    }

    @Override
    public String toString() {
        if (this == HEALTHY) {
            return "Norm";
        }

        if (this == OVERWEIGHT) {
            return "Warning!";
        }

        if (this == OBESITY) {
            return "Fat";
        }

        if (this == UNDERWEIGHT) {
            return "Skinny";
        }

        return "Invalid";
    }
}
