package tasks.task4;

import tasks.task4.exceptions.InsufficientFundsException;
import tasks.task4.exceptions.InvalidTransactionAmountException;
import tasks.task4.exceptions.MaxBalanceExceededException;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Bank {

    private final Set<BankAcount> managedAccounts;

    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    public Bank(Set<BankAcount> managedAccounts) {
        this.managedAccounts = managedAccounts;
    }

    public Bank() {
        this.managedAccounts = new HashSet<>();
    }

    public void createBankAccount(){
        managedAccounts.add(new BankAcount());
    }

    static synchronized long getNewAccountNumber(){
        return new Random().nextLong(); //Here must be logic for creation new account number
    }

    public void withdraw(BankAcount bankAcount, double amount){
        if (amount <= 0) throw new InvalidTransactionAmountException();
        executor.execute(() -> {
            synchronized (bankAcount) {
                bankAcount.withdraw(amount);
            }
        });
    }

    public void deposit(BankAcount bankAcount, double amount) {
        if (amount <= 0) throw new InvalidTransactionAmountException();
        executor.execute(() -> {
            synchronized (bankAcount) {
                bankAcount.deposit(amount);
            }
        });
    }

    public void transfer(BankAcount from, BankAcount to, double amount) throws InvalidTransactionAmountException {
        if (amount <= 0) throw new InvalidTransactionAmountException();
        executor.execute(() -> {
            BankAcount first = from;
            BankAcount second = to;
            if (first.getAccountNumber() < second.getAccountNumber()) {
                second = from;
                first = to;
            }
            synchronized (first) {
                synchronized (second) {
                    if (from.withdraw(amount) == true) {
                        if (to.deposit(amount) == false) from.deposit(amount);
                    }
                }
            }
        });
    }
}
