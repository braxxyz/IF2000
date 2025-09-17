package logic;

public class SINPE {
    public static boolean transfer(Bank fromBank, String fromAccNum,
                                   Bank toBank, String toAccNum, double amount) {
        Account fromAcc = fromBank.findAccount(fromAccNum);
        Account toAcc = toBank.findAccount(toAccNum);
        if (fromAcc == null || toAcc == null) {
            System.out.println("Account not found");
            return false;
        }
        if (fromAcc.withdraw(amount)) {
            toAcc.deposit(amount);
            fromBank.getLedger().addEntry(new LedgerEntry("transfer-out", fromAcc, amount));
            toBank.getLedger().addEntry(new LedgerEntry("transfer-in", toAcc, amount));
            return true;
        }
        System.out.println("Insufficient funds");
        return false;
    }
}
