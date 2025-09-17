package logic;

import java.time.LocalDate;

public class SavingsAccount extends Account {
    private int term;
    private double interestRate;
    private LocalDate creationDate;

    public SavingsAccount(String number, double balance, Client owner, LocalDate creationDate, int term, double interestRate) {
        super(number, balance, owner);
        this.creationDate = creationDate;
        this.term = term;
        this.interestRate = interestRate;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }


    public void deposit(double amount) {
        balance += amount;
    }


    public void calculateInterest() {
        balance += balance * interestRate;
    }
}
