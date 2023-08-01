package exceptions;

public class DivisionByZeroException extends RuntimeException {
    public DivisionByZeroException() {
        super("Деление на ноль недопустимо");
    }
}
