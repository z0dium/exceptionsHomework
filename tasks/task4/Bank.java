package tasks.task4;

import tasks.task4.exceptions.InsufficientFundsException;
import tasks.task4.exceptions.InvalidAmountException;
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
        try {
            checkAmount(amount);
        } catch (InvalidAmountException ex){
            //Сообщить о некорректности суммы операции
        }

        executor.execute(() -> {
            synchronized (bankAcount) {
                try{
                    bankAcount.withdraw(amount);
                } catch (InsufficientFundsException ex){
                    // Сообщить о невозможности вывода средств в связи с недостатком средст на счете.
                }
            }
        });
    }

    public void deposit(BankAcount bankAcount, double amount) {
        try {
            checkAmount(amount);
        } catch (InvalidAmountException ex){
            //Сообщить о некорректности суммы операции
        }
        executor.execute(() -> {
            synchronized (bankAcount) {
                try {
                    bankAcount.deposit(amount);
                }catch (MaxBalanceExceededException ex){
                    //Сообщить о невозможности провести операцию в связи с превышением максимальной суммы счета.
                }
            }
        });
    }

    public void transfer(BankAcount from, BankAcount to, double amount) throws InvalidAmountException {
        try {
            checkAmount(amount);
        } catch (InvalidAmountException ex){
            //Сообщить о некорректности суммы операции
        }
        executor.execute(() -> {
            BankAcount first = from;
            BankAcount second = to;
            if (first.getAccountNumber() < second.getAccountNumber()) {
                second = from;
                first = to;
            }
            synchronized (first) {
                synchronized (second) {
                    try{
                        from.withdraw(amount);
                        try{
                            to.deposit(amount);
                        } catch (MaxBalanceExceededException ex){
                            //Сообщить о невозможности провести операцию в связи с превышением максимальной суммы счета.
                            from.deposit(amount);
                        }
                    } catch (InsufficientFundsException ex){
                        // Сообщить о невозможности вывода средств в связи с недостатком средст на счете.
                    }
                }
            }
        });
    }

    private void checkAmount(double amount){
        if (amount <= 0) throw new InvalidAmountException();
    }
}
