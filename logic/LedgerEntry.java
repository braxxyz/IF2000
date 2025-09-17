package logic;

import java.time.LocalDateTime;

public class LedgerEntry {
    private static int nextId = 1;
    private int id;
    private String eventType;
    private LocalDateTime dateTime;
    private Account account;
    private double amount;

    public LedgerEntry(String eventType, Account account, double amount) {
        this.id = nextId++;
        this.eventType = eventType;
        this.dateTime = LocalDateTime.now();
        this.account = account;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Event #" + id + " " + eventType + " " + amount + " on " + account.getNumber() + " at " + dateTime;
    }
}
