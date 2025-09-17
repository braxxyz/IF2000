package logic;

public class CheckingAccount extends Account {
    private double interestRate;

    public CheckingAccount(String number, double balance, Client owner, double interestRate) {
        super(number, balance, owner);
        this.interestRate = interestRate;
    }

    @Override
    public void calculateInterest() {
        balance += balance * interestRate;  // simple interés
    }
}

