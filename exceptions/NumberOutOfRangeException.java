package exceptions;

public class NumberOutOfRangeException extends RuntimeException{
    public NumberOutOfRangeException(String message){
        super(message);
    }
}
