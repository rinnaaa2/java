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
    public double lowerLimit;
    public double upperLimit;
    public double step;
    public double result;
    public boolean hasResult;
    
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
}
