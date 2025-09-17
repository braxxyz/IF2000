package logic;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String name;
    private List<Account> accounts = new ArrayList<>();
    private Ledger ledger = new Ledger();

    public Bank(String name) {
        this.name = name;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account findAccount(String number) {
        for (Account acc : accounts) {
            if (acc.getNumber().equals(number)) {
                return acc;
            }
        }
        return null;
    }

    public Ledger getLedger() { return ledger; }
    public String getName() { return name; }
}
