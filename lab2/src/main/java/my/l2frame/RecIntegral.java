/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.l2frame;

/**
 *
 * @author Марина
 */
public class RecIntegral {
    private double lowerLimit;
    private double upperLimit;
    private double step;
    private double result;
    private boolean hasResult;
    
    public RecIntegral(double lowerLimit, double upperLimit, double step) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.step = step;
        this.hasResult = false;
        this.result = 0;
    }
    
    public RecIntegral(double lowerLimit, double upperLimit, double step, double result) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.step = step;
        this.result = result;
        this.hasResult = true;
    }
    // Геттеры
    public double getLowerLimit() {
        return lowerLimit;
    }
    
    public double getUpperLimit() {
        return upperLimit;
    }
    
    public double getStep() {
        return step;
    }
    
    public double getResult() {
        return result;
    }
    
    public boolean hasResult() {
        return hasResult;
    }
    
    // Сеттеры
    public void setLowerLimit(double lowerLimit) {
        this.lowerLimit = lowerLimit;
    }
    
    public void setUpperLimit(double upperLimit) {
        this.upperLimit = upperLimit;
    }
    
    public void setStep(double step) {
        this.step = step;
    }
    
    public void setResult(double result) {
        this.result = result;
        this.hasResult = true;
    }
    
    public void clearResult() {
        this.result = 0;
        this.hasResult = false;
    }
    
    public double calculateIntegral() {
    double sum = 0.0;
    double x = lowerLimit;
    
    while (x < upperLimit) {
        double nextX = Math.min(x + step, upperLimit);//усечение шага
        double y1 = Math.cos(x * x);
        double y2 = Math.cos(nextX * nextX);
        sum += (y1 + y2) * (nextX - x) / 2.0;//площадь 
        x = nextX;
    }
    
    this.result = sum;
    this.hasResult = true;
    return sum;
    }
}
