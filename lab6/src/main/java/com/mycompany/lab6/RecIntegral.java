package com.mycompany.lab6;

import java.io.Serializable;

public class RecIntegral implements Serializable, Runnable {
    private static final long serialVersionUID = 1L;
    private double lowerLimit;
    private double upperLimit;
    private double step;
    private double result;
    private boolean hasResult;
    private transient double res; //результат для потока
    
    public RecIntegral(double lowerLimit, double upperLimit, double step) throws ValidData {
        if (lowerLimit < 0.000001 || lowerLimit > 1000000) {
            throw new ValidData("Нижний предел вне диапазона [0.000001, 1000000]");
        }
        if (upperLimit < 0.000001 || upperLimit > 1000000) {
            throw new ValidData("Верхний предел вне диапазона [0.000001, 1000000]");
        }
        if (step < 0.000001 || step > 1000000) {
            throw new ValidData("Шаг вне диапазона [0.000001, 1000000]");
        }
        if (lowerLimit >= upperLimit) {
            throw new ValidData("Нижний предел должен быть меньше верхнего!");
        }
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.step = step;
        this.hasResult = false;
        this.result = 0;
    }

    public RecIntegral(String lowerStr, String upperStr, String stepStr) throws ValidData {
        this(
            parseDouble(lowerStr, "Нижний предел"),
            parseDouble(upperStr, "Верхний предел"),
            parseDouble(stepStr, "Шаг")
        );
    }
    
    public RecIntegral(double lowerLimit, double upperLimit, double step, double result) throws ValidData {
        this(lowerLimit, upperLimit, step);
        this.result = result;
        this.hasResult = true;
    }
    
    private static double parseDouble(String value, String fieldName) throws ValidData {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidData("Поле '" + fieldName + "' не заполнено!");
        }
        try {
            return Double.parseDouble(value.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            throw new ValidData("Поле '" + fieldName + " " + value + "' не является числом!");
        }
    }

    public double getLowerLimit() { return lowerLimit; }
    public double getUpperLimit() { return upperLimit; }
    public double getStep() { return step; }
    public double getResult() { return result; }
    public boolean hasResult() { return hasResult; }

    public void setLowerLimit(double lowerLimit) { this.lowerLimit = lowerLimit; }
    public void setUpperLimit(double upperLimit) { this.upperLimit = upperLimit; }
    public void setStep(double step) { this.step = step; }
    
    public void setResult(double result) {
        this.result = result;
        this.hasResult = true;
    }
    
    public void clearResult() {
        this.result = 0;
        this.hasResult = false;
    }
    
    @Override
    
    public void run() {
        double sum = 0.0;
        double x = lowerLimit;
        
        while (x < upperLimit) {
            double nextX = Math.min(x + step, upperLimit);
            double y1 = Math.cos(x * x);
            double y2 = Math.cos(nextX * nextX);
            sum += (y1 + y2) * (nextX - x) / 2.0;
            x = nextX;
        }
        this.res = sum;
        //this.result = sum;
        //this.hasResult = true;
        //return sum;
    }
    public double getRes(){
        return res;
    }
}