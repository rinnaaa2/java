package com.mycompany.lab6_dopzad_tcp;

import java.io.Serializable;

public class RecIntegral implements Serializable, Runnable {
    private static final long serialVersionUID = 1L;
    
    private static final double MIN_VAL = 0.000001;
    private static final double MAX_VAL = 1000000.0;
    
    private double lowerLimit;
    private double upperLimit;
    private double step;
    private Double result;
    private transient double res;
    
    public RecIntegral(double lowerLimit, double upperLimit, double step) {
        if (step <= 0) {
            throw new IllegalArgumentException("Шаг должен быть положительным");
        }
        if (lowerLimit >= upperLimit) {
            throw new IllegalArgumentException("Нижний предел должен быть меньше верхнего");
        }
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.step = step;
        this.result = null;
    }
    
    // Конструктор с результатом
    public RecIntegral(double lowerLimit, double upperLimit, double step, double result) {
        this(lowerLimit, upperLimit, step);
        this.result = result;
    }
    
    public RecIntegral(String lowerStr, String upperStr, String stepStr) throws ValidData {
        double lowerLimit = parseDouble(lowerStr, "Нижний предел");
        double upperLimit = parseDouble(upperStr, "Верхний предел");
        double step = parseDouble(stepStr, "Шаг");
        
        if (lowerLimit < MIN_VAL || lowerLimit > MAX_VAL) {
            throw new ValidData("Нижний предел вне диапазона [" + MIN_VAL + ", " + MAX_VAL + "]");
        }
        if (upperLimit < MIN_VAL || upperLimit > MAX_VAL) {
            throw new ValidData("Верхний предел вне диапазона [" + MIN_VAL + ", " + MAX_VAL + "]");
        }
        if (step < MIN_VAL || step > MAX_VAL) {
            throw new ValidData("Шаг вне диапазона [" + MIN_VAL + ", " + MAX_VAL + "]");
        }
        if (lowerLimit >= upperLimit) {
            throw new ValidData("Нижний предел должен быть меньше верхнего!");
        }
        
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.step = step;
        this.result = null;
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

    // Геттеры
    public double getLowerLimit() { return lowerLimit; }
    public double getUpperLimit() { return upperLimit; }
    public double getStep() { return step; }
    public Double getResult() { return result; }
    public boolean hasResult() { return result != null; }

    // Сеттеры
    public void setLowerLimit(double lowerLimit) { this.lowerLimit = lowerLimit; }
    public void setUpperLimit(double upperLimit) { this.upperLimit = upperLimit; }
    public void setStep(double step) { this.step = step; }
    
    public void setResult(double result) {
        this.result = result;
    }
    
    public void clearResult() {
        this.result = null;
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
            
            if (x >= upperLimit) {
                break;
            }
        }
        this.res = sum;
        this.result = sum;
    }
    
    public double getRes() {
        return res;
    }
}