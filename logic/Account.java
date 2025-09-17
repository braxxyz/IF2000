package logic;

public abstract class Account {
    protected String number; // ID de la cuenta
    protected double balance;
    protected Client owner;

    public Account(String number, double balance, Client owner) {
        this.number = number;
        this.balance = balance;
        this.owner = owner;
    }

    public String getNumber() {
        return number; // ← Esto es clave para que Bank.findAccount funcione
    }

    public double getBalance() {
        return balance;
    }

    public Client getOwner() {
        return owner;
    }

    public abstract boolean withdraw(double amount);
    public abstract void deposit(double amount);
    public abstract void calculateInterest();
}
