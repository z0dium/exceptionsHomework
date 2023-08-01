package tasks.task4;

import tasks.task4.exceptions.InsufficientFundsException;
import tasks.task4.exceptions.MaxBalanceExceededException;

public class BankAcount {

    private static final double BALANCE_LIMIT = 1000000;
    private final long accountNumber;
    private double amount;

    public BankAcount() {
        this.accountNumber = Bank.getNewAccountNumber();
        amount = 0.0;
    }

    public boolean deposit(Double amount) throws MaxBalanceExceededException {
        synchronized (this) {
            if (this.amount + amount > BALANCE_LIMIT) throw new MaxBalanceExceededException();
            this.amount += amount;
            return true;
        }
    }

    public boolean withdraw(Double amount) throws InsufficientFundsException {
        synchronized (this) {
            if (this.amount - amount < 0) throw new InsufficientFundsException();
            this.amount -= amount;
            return true;
        }
    }

    public long getAccountNumber() {
        return accountNumber;
    }
}
