package logic;

public abstract class Account {
    protected String number;
    protected double balance;
    protected Client owner;

    public Account(String number, double balance, Client owner) {
        this.number = number;
        this.balance = balance;
        this.owner = owner;
    }

    public String getNumber() { return number; }
    public double getBalance() { return balance; }
    public Client getOwner() { return owner; }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public abstract void calculateInterest();

    @Override
    public String toString() {
        return "Account " + number + " Balance: " + balance + " Owner: " + owner.getName();
    }
}

