package tasks.task4.exceptions;

public class InvalidTransactionAmountException extends RuntimeException{
    public InvalidTransactionAmountException(){
        super("Сумма перевода должна быть больше 0");
    }
}
