package logic;

import java.util.ArrayList;
import java.util.List;

public class Ledger {
    private List<LedgerEntry> entries = new ArrayList<>();

    public void addEntry(LedgerEntry entry) {
        entries.add(entry);
    }

    public List<LedgerEntry> getEntries() {
        return entries;
    }

    @Override
    public String toString() {
        return entries.toString();
    }
}
