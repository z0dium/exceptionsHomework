package tasks.task4.exceptions;

public class InsufficientFundsException extends RuntimeException{
    public InsufficientFundsException(){
        super("Недостаточно средств на счете");
    }
}
