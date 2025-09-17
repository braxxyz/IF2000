package presentation;

import logic.*;

import java.time.LocalDate;
import java.util.List;

public class Controller {
    private View view;
    private Bank bank1, bank2;

    public Controller(View view) {
        this.view = view;
        bank1 = new Bank("Bank1");
        bank2 = new Bank("Bank2");

        // Botón crear cuenta
        view.createAccountButton.addActionListener(e -> {
            String name = view.clientNameField.getText();
            String lastName = view.clientLastNameField.getText();
            String accNum = view.accountNumberField.getText();
            double balance = Double.parseDouble(view.initialBalanceField.getText());

            Client client = new Client(name, lastName, "id", "0000", 30, "Ciudad");
            SavingsAccount sa = new SavingsAccount(accNum, balance, client, LocalDate.now(), 6, 0.02);
            bank1.addAccount(sa);
            updateLedger();
        });

        // Otros botones (deposit, withdraw, transfer) se pueden agregar igual
    }

    // Actualiza tabla ledger
    public void updateLedger() {
        view.ledgerModel.setRowCount(0); // limpiar
        List<logic.LedgerEntry> entries = bank1.getLedger().getEntries();
        for (LedgerEntry e : entries) {
            view.ledgerModel.addRow(new Object[]{
                    e.getType(),
                    e.getAccount().getNumber(),
                    e.getAmount(),
                    e.getDate()
            });
        }
    }
}
