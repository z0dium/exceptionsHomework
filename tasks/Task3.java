package tasks;

import exceptions.DivisionByZeroException;
import exceptions.NumberOutOfRangeException;
import exceptions.NumberSumException;

import java.util.Scanner;

public class Task3 {
    Scanner scanner = new Scanner(System.in);

    public void start(){
        System.out.println("Задача №3");
        System.out.print("Введите первое число: ");
        double number1 = scanner.nextDouble();
        if (number1 > 100) throw new NumberOutOfRangeException("Первое число вне допустимого диапазона");

        System.out.print("Введите второе число: ");
        double number2 = scanner.nextDouble();
        if (number2 < 0) throw new NumberOutOfRangeException("Второе число вне допустимого диапазона");
        if (number2+number1 < 10) throw new NumberSumException("Сумма первого и второго чисел слишком мала");

        System.out.print("Введите третье число: ");
        double number3 = scanner.nextDouble();
        if (number3 == 0) throw new DivisionByZeroException();

        System.out.printf("Проверка пройдена успешно");
    }
}
