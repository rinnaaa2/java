/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.l2frame;

/**
 *
 * @author Марина
 */
public class ValidData extends Exception {
    public static final double MIN_VALUE = 0.000001;
    public static final double MAX_VALUE = 1000000;
    
    public ValidData(String message) {
        super(message);
    }
    
    private static double parseAndValidate(String value, String fieldName) throws ValidData {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidData("Поле '" + fieldName + "' не заполнено!");
        }
        
        try {
            String normalized = value.trim().replace(',', '.');
            double result = Double.parseDouble(normalized);
            if (Double.isInfinite(result) || Double.isNaN(result)) {
                throw new ValidData("Поле '" + fieldName + "' содержит недопустимое значение");
            }
            return result;
        } catch (NumberFormatException e) {
            throw new ValidData("Поле '" + fieldName + "' = '" + value + 
                "' не является корректным числом!\n" +
                "Используйте точку или запятую (например: 0.5 или 0,5)");
        }
    }

    public static double[] validateAndParse(String lowerText, String upperText, String stepText) 
            throws ValidData {
        
        double lowerLimit = parseAndValidate(lowerText, "Нижний предел");
        double upperLimit = parseAndValidate(upperText, "Верхний предел");
        double step = parseAndValidate(stepText, "Шаг");
        
        // Используем существующий метод validate для проверки
        validate(lowerLimit, upperLimit, step);
        
        return new double[]{lowerLimit, upperLimit, step};
    }
    
    public static void validate(double lowerLimit, double upperLimit, double step) 
            throws ValidData {
        
        if (lowerLimit < MIN_VALUE || lowerLimit > MAX_VALUE) {
            throw new ValidData(
                "Нижний предел (" + lowerLimit + ") вне диапазона [" + MIN_VALUE + ", " + MAX_VALUE + "]"
            );
        }
        
        if (upperLimit < MIN_VALUE || upperLimit > MAX_VALUE) {
            throw new ValidData(
                "Верхний предел (" + upperLimit + ") вне диапазона [" + MIN_VALUE + ", " + MAX_VALUE + "]"
            );
        }
        
        if (step < MIN_VALUE || step > MAX_VALUE) {
            throw new ValidData(
                "Шаг (" + step + ") вне диапазона [" + MIN_VALUE + ", " + MAX_VALUE + "]"
            );
        }
        
        if (lowerLimit >= upperLimit) {
            throw new ValidData(
                "Нижний предел (" + lowerLimit + ") должен быть меньше верхнего (" + upperLimit + ")"
            );
        }
    }
    
    public static void validate(RecIntegral rec) throws ValidData {
        if (rec == null) {
            throw new ValidData("Объект RecIntegral равен null");
        }
        validate(rec.getLowerLimit(), rec.getUpperLimit(), rec.getStep());
    }
}