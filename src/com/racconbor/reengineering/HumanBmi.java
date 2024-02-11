package com.racconbor.reengineering;

public class HumanBmi {
    private double weight;
    private double height;

    public HumanBmi(double weight, double height) {
        this.weight = weight;
        this.height = height;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double value) {
        this.weight = value;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double value) {
        this.height = value;
    }

    public double getBMI() {
        return this.weight / (this.height * this.height);
    }

    @Override
    public String toString() {
        var bmi = this.getBMI();
        
        if (bmi >= 18.5 && bmi < 25) {
            return "Normal";
        }
        if (bmi >= 25 && bmi < 30) {
            return "Warning!";
        }
        if (bmi >= 30) {
            return "Fat";
        }
        if (bmi >= 0 && bmi < 18.5) {
            return "Skinny";
        }

        return "Invalid";
    }
}
