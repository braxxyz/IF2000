package presentation;

import logic.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class Controller {
    private View view;
    private Bank bank1, bank2;

    public Controller(View view) {
        this.view = view;
        bank1 = new Bank("Bank1");
        bank2 = new Bank("Bank2");

        // -----------------------------
        // BOTÓN CREAR CUENTA
        // -----------------------------
        view.createAccountButton.addActionListener(e -> {
            try {
                String name = view.clientNameField.getText();
                String lastName = view.clientLastNameField.getText();
                String accNum = view.accountNumberField.getText();
                double balance = Double.parseDouble(view.initialBalanceField.getText());

                Client client = new Client(name, lastName, "id-" + accNum, "0000", 30, "Ciudad");

                String[] options = {"SavingsAccount", "CheckingAccount"};
                int type = JOptionPane.showOptionDialog(view,
                        "Selecciona tipo de cuenta:",
                        "Tipo de cuenta",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                Account account;
                if (type == 0) {
                    account = new SavingsAccount(accNum, balance, client, LocalDate.now(), 6, 0.02);
                } else {
                    account = new CheckingAccount(accNum, balance, client, 0.03);
                }

                String[] banks = {"Bank1", "Bank2"};
                int bankChoice = JOptionPane.showOptionDialog(view,
                        "Selecciona el banco:",
                        "Banco",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        banks,
                        banks[0]);

                Bank selectedBank = bankChoice == 0 ? bank1 : bank2;
                selectedBank.addAccount(account);

                // Agregar entrada al ledger
                selectedBank.getLedger().addEntry(new LedgerEntry("Cuenta creada", account, balance));

                JOptionPane.showMessageDialog(view, "Cuenta creada exitosamente!");
                updateLedger();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error al crear cuenta: " + ex.getMessage());
            }
        });

        // -----------------------------
        // BOTÓN DEPOSITAR
        // -----------------------------
        view.depositButton.addActionListener(e -> {
            try {
                String accNum = JOptionPane.showInputDialog(view, "Número de cuenta:");
                double amount = Double.parseDouble(JOptionPane.showInputDialog(view, "Monto a depositar:"));

                Account acc = findAccountInBanks(accNum);
                if (acc != null) {
                    acc.deposit(amount);
                    Bank bank = bank1.findAccount(accNum) != null ? bank1 : bank2;
                    bank.getLedger().addEntry(new LedgerEntry("deposit", acc, amount));

                    // Mostrar factura
                    showInvoice("Depósito", acc, amount);

                    updateLedger();
                } else {
                    JOptionPane.showMessageDialog(view, "Cuenta no encontrada.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
            }
        });

        // -----------------------------
        // BOTÓN RETIRAR
        // -----------------------------
        view.withdrawButton.addActionListener(e -> {
            try {
                String accNum = JOptionPane.showInputDialog(view, "Número de cuenta:");
                double amount = Double.parseDouble(JOptionPane.showInputDialog(view, "Monto a retirar:"));

                Account acc = findAccountInBanks(accNum);
                if (acc != null) {
                    if (acc.withdraw(amount)) {
                        Bank bank = bank1.findAccount(accNum) != null ? bank1 : bank2;
                        bank.getLedger().addEntry(new LedgerEntry("withdraw", acc, amount));

                        // Mostrar factura
                        showInvoice("Retiro", acc, amount);

                        updateLedger();
                    } else {
                        JOptionPane.showMessageDialog(view, "Saldo insuficiente.");
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Cuenta no encontrada.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
            }
        });

        // -----------------------------
        // BOTÓN TRANSFERIR
        // -----------------------------
        view.transferButton.addActionListener(e -> {
            try {
                String fromAccNum = JOptionPane.showInputDialog(view, "Cuenta origen:");
                String toAccNum = JOptionPane.showInputDialog(view, "Cuenta destino:");
                double amount = Double.parseDouble(JOptionPane.showInputDialog(view, "Monto a transferir:"));

                Account fromAcc = findAccountInBanks(fromAccNum);
                Account toAcc = findAccountInBanks(toAccNum);

                Bank fromBank = bank1.findAccount(fromAccNum) != null ? bank1 : bank2;
                Bank toBank = bank1.findAccount(toAccNum) != null ? bank1 : bank2;

                if (fromAcc != null && toAcc != null) {
                    boolean success = SINPE.transfer(fromBank, fromAccNum, toBank, toAccNum, amount);
                    if (success) {
                        // Mostrar factura de transferencia
                        showInvoice("Transferencia", fromAcc, toAcc, amount);
                    } else {
                        JOptionPane.showMessageDialog(view, "Transferencia fallida.");
                    }
                    updateLedger();
                } else {
                    JOptionPane.showMessageDialog(view, "Alguna de las cuentas no existe.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
            }
        });
    }

    // -----------------------------
    // METODOS AUXILIARES
    // -----------------------------
    private Account findAccountInBanks(String accNum) {
        Account acc = bank1.findAccount(accNum);
        if (acc == null) acc = bank2.findAccount(accNum);
        return acc;
    }

    public void updateLedger() {
        view.ledgerModel.setRowCount(0);
        List<LedgerEntry> entries = bank1.getLedger().getEntries();
        for (LedgerEntry e : entries) {
            view.ledgerModel.addRow(new Object[]{e.getType(), e.getAccount().getNumber(), e.getAmount(), e.getDate()});
        }

        entries = bank2.getLedger().getEntries();
        for (LedgerEntry e : entries) {
            view.ledgerModel.addRow(new Object[]{e.getType(), e.getAccount().getNumber(), e.getAmount(), e.getDate()});
        }
    }

    // -----------------------------
    // FACTURAS
    // -----------------------------
    private void showInvoice(String action, Account acc, double amount) {
        String msg = action + " realizada:\n" +
                     "Cuenta: " + acc.getNumber() + "\n" +
                     "Cliente: " + acc.getOwner().getName() + " " + acc.getOwner().getLastName() + "\n" +
                     "Monto: " + amount + "\n" +
                     "Saldo actual: " + acc.getBalance();
        JOptionPane.showMessageDialog(view, msg, "Factura", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showInvoice(String action, Account fromAcc, Account toAcc, double amount) {
        String msg = action + " realizada:\n" +
                     "Cuenta origen: " + fromAcc.getNumber() + "\n" +
                     "Cliente origen: " + fromAcc.getOwner().getName() + " " + fromAcc.getOwner().getLastName() + "\n" +
                     "Cuenta destino: " + toAcc.getNumber() + "\n" +
                     "Cliente destino: " + toAcc.getOwner().getName() + " " + toAcc.getOwner().getLastName() + "\n" +
                     "Monto: " + amount;
        JOptionPane.showMessageDialog(view, msg, "Factura de Transferencia", JOptionPane.INFORMATION_MESSAGE);
    }
}