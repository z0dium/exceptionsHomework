package tasks;

import exceptions.DivisionByZeroException;

import java.util.Scanner;

public class Task2 {
    Scanner scanner = new Scanner(System.in);

    public void start(){
        System.out.println("Задача №2");
        System.out.print("Введите первое число: ");
        double number1 = scanner.nextDouble();
        System.out.print("Введите второе число: ");
        double number2 = scanner.nextDouble();

        if (number2 == 0) throw new DivisionByZeroException();
        else System.out.println(number1/number2);
    }

}
