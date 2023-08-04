package tasks.Calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Calculator {

    private final Double first;
    private final Double second;

    public Calculator() throws InvalidInputException{
        System.out.print("Введите первое число: ");
        first = getNumber();
        System.out.print("Введите второе число: ");
        second = getNumber();
    }

    private double getNumber(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            Double result = Double.parseDouble(reader.readLine());
            if (result.isInfinite() || result.isNaN()) throw new InvalidInputException("Введенное значение не позволяет произвести расчет");
            return result;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Введенное значение не является числом");
        } catch (IOException e){
            throw new InvalidInputException("Ошибка ввода занчения");
        }
    }

    double sum(){
        if ((first < 0 && second < 0) && (first < -Double.MAX_VALUE - second)) throw new InvalidInputException("Сумма чисел выходит за рамки значения Double");
        else if ((first > 0 && second > 0) && (first > Double.MAX_VALUE - second)) throw new InvalidInputException("Сумма чисел выходит за рамки значения Double");
        return first + second;
    }

    double substraction(){
        if ((first < 0 && second > 0) && (first < -Double.MAX_VALUE + second)) throw new InvalidInputException("Разность чисел выходит за рамки значения Double");
        else if ((first > 0 && second < 0) && (first > Double.MAX_VALUE + second)) throw new InvalidInputException("Разность чисел выходит за рамки значения Double");
        return first - second;
    }

    double multiply(){
        if ((Math.abs(first) > Double.MAX_VALUE/Math.abs(second))) throw new InvalidInputException("Произведение чисел выходит за рамки значения Double");
        return first * second;
    }

    double devision(){
        if (second == 0) throw new InvalidInputException("Недопустимая операция: деление на 0");
        return first / second;
    }

    double power(){
        if (first == 0 && second == 0) throw new InvalidInputException("Недопустимая операция 0 в степени 0");
        double result = Math.pow(first,second);
        if (result == Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY) throw new InvalidInputException("Результат возведения в степень выходит за рамки значения Double");
        return result;
    }

    public static void main(String[] args) {

        Calculator calculator = new Calculator(); // не перехыватываю исключения, так как при неправильном вводе дальнейшие действия не имеют смысла.

        try{System.out.println("Сумма: " + calculator.sum());}catch (InvalidInputException ex){System.out.println(ex.getMessage());}
        try{System.out.println("Разность: " + calculator.substraction());}catch (InvalidInputException ex){System.out.println(ex.getMessage());}
        try{System.out.println("Произведение: " + calculator.multiply());}catch (InvalidInputException ex){System.out.println(ex.getMessage());}
        try{System.out.println("Частное: " + calculator.devision());}catch (InvalidInputException ex){System.out.println(ex.getMessage());}
        try{System.out.println("Возведение в степень: " + calculator.power());}catch (InvalidInputException ex){System.out.println(ex.getMessage());}

    }
}
