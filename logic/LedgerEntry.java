package logic;

import java.time.LocalDateTime;

public class LedgerEntry {
    private String type;       // tipo de operación: "transfer-in", "transfer-out", "deposit", "withdraw"
    private Account account;   // cuenta asociada
    private double amount;     // monto de la operación
    private LocalDateTime date; // fecha y hora de la operación

    // Constructor
    public LedgerEntry(String type, Account account, double amount) {
        this.type = type;
        this.account = account;
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    // Getters
    public String getType() {
        return type;
    }

    public Account getAccount() {
        return account;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "[" + type + " " + amount + " on " + account.getNumber() + " at " + date + "]";
    }
}
