package tasks;

import exceptions.InvalidNumberException;

import java.util.Scanner;

public class Task1 {
    Scanner scanner = new Scanner(System.in);

    public void start(){
        System.out.println("Задача №1");
        System.out.print("Введите число: ");
        int number = scanner.nextInt();
        if (number <= 0) throw new InvalidNumberException("Некорректное число");
        else System.out.println("Число корректно");
    }

}
