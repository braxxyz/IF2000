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

    // Depositar dinero
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    // Retirar dinero
    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;  // ⚡ aquí se descuenta el saldo de la cuenta
            return true;
        }
        return false;
    }

    // Método abstracto para calcular interés (si es que aplica)
    public abstract void calculateInterest();
}

