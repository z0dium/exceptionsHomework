package tasks.task4.exceptions;

public class InvalidAmountException extends RuntimeException{
    public InvalidAmountException(){
        super("Сумма перевода должна быть больше 0");
    }
}
