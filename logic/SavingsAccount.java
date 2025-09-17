package logic;

import java.time.LocalDate;

public class SavingsAccount extends Account {
    private LocalDate creationDate;
    private int termMonths;
    private double interestRate;

    public SavingsAccount(String number, double balance, Client owner, LocalDate creationDate, int termMonths, double interestRate) {
        super(number, balance, owner);
        this.creationDate = creationDate;
        this.termMonths = termMonths;
        this.interestRate = interestRate;
    }

    @Override
    public void calculateInterest() {
        balance += balance * interestRate;  // simple interés
    }
}
