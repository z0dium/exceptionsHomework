package tasks.Calculator;

import java.util.Scanner;

public class Calculator {
    private final Scanner scanner;
    private final double first;
    private final double second;

    public Calculator() throws NumberFormatException{
        scanner = new Scanner(System.in);
        System.out.print("Введите первое число: ");
        first = Double.parseDouble(scanner.nextLine());
        System.out.print("Введите второе число: ");
        second = Double.parseDouble(scanner.nextLine());
    }

    double sum(){
        if (first > Double.MAX_VALUE - second) throw new InvalidInputException("Сумма чисел выходит за рамки значения Double");
        return first+second;
    }

    double substraction(){
        if (Double.MIN_VALUE + second > first) throw new InvalidInputException("Разность чисел выходит за рамки значения Double");
        return first - second;
    }






    public static void main(String[] args) {

        Calculator calculator = new Calculator();
    }
}
