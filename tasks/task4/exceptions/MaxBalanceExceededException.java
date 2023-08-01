package tasks.task4.exceptions;

public class MaxBalanceExceededException extends RuntimeException{
    public MaxBalanceExceededException(){
        super("Превышение максимально допустимого баланса");
    }
}
