package logic;

public class CheckingAccount extends Account {
    private double interestRate;

    public CheckingAccount(String number, double balance, Client owner, double interestRate) {
        super(number, balance, owner);
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

