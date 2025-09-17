package logic;

import java.time.LocalDate;

public class SavingsAccount extends Account {
    private LocalDate startDate;
    private int termMonths;
    private double interestRate;

    public SavingsAccount(String number, double balance, Client owner,
                          LocalDate startDate, int termMonths, double interestRate) {
        super(number, balance, owner);
        this.startDate = startDate;
        this.termMonths = termMonths;
        this.interestRate = interestRate;
    }

    @Override
    public void calculateInterest() {
        double interest = termMonths * balance * interestRate;
        balance += interest;
    }

    public LocalDate getStartDate() { return startDate; }
    public int getTermMonths() { return termMonths; }
    public double getInterestRate() { return interestRate; }

    @Override
    public String toString() {
        return super.toString() + " [SavingsAccount, InterestRate: " + interestRate + "]";
    }
}
