package com.racconbor.reengineering;

/** 
 * Represents a human BMI calculator which accepts 
 * weight and height. 
 * 
 * @author Pavlo Shcherbatyi
 * @author https://github.com/RaccoonBoryvitter
 * @version 2.0
 * @since 1.0
*/
public class HumanBmi {
    private double weight;
    private double height;

    /**
    * Creates a calculator object based on weight
    * and height values. 
    *
    * @param weight the value of weight in kilograms.
    * @param height the value of height in meters.
    */
    public HumanBmi(double weight, double height) {
        this.weight = weight;
        this.height = height;
    }
    
    /**
    * Returns the value of weight. 
    *
    * @return      the value of weight in kilograms (KG).
    */
    public double getWeight() {
        return this.weight;
    }

    /**
    * Sets a value of weight. 
    *
    * @param value the value of weight in kilograms (KG).
    */
    public void setWeight(double value) {
        this.weight = value;
    }

    /**
    * Returns the value of height in meters. 
    *
    * @return      the value of height in meters (M).
    */
    public double getHeight() {
        return this.height;
    }

    /**
    * Sets a value of weight. 
    *
    * @param value the value of height in meters (M).
    */
    public void setHeight(double value) {
        this.height = value;
    }

    /**
    * Calculates and returns a BMI value. 
    *
    * @return      the BMI value in kilograms per square meter (kg/m^2).
    */
    public double getBmi() {
        return this.weight / (this.height * this.height);
    }

    /**
    * Returns a string representation of BMI value. 
    *
    * @return      the BMI value in string.
    */
    public String getResult() {
        return BmiLevel
            .fromBmiValue(this.getBmi())
            .toString();
    }
}
