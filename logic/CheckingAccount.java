package logic;

public class CheckingAccount extends Account {
    private double interestRate;

    public CheckingAccount(String number, double balance, Client owner, double interestRate) {
        super(number, balance, owner);
        this.interestRate = interestRate;
    }

    @Override
    public void calculateInterest() {
        double interest = balance * interestRate / 12;
        balance += interest;
    }

    public double getInterestRate() { return interestRate; }

    @Override
    public String toString() {
        return super.toString() + " [CheckingAccount, InterestRate: " + interestRate + "]";
    }
}
