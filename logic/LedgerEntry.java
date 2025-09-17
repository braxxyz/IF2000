package logic;

import java.time.LocalDateTime;

public class LedgerEntry {
    private String type;
    private Account account;
    private double amount;
    private LocalDateTime date;

    public LedgerEntry(String type, Account account, double amount) {
        this.type = type;
        this.account = account;
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    public String getType() { return type; }
    public Account getAccount() { return account; }
    public double getAmount() { return amount; }
    public LocalDateTime getDate() { return date; }

    
    public String toString() {
        return "[" + type + " " + amount + " on " + account.getNumber() + " at " + date + "]";
    }
}
