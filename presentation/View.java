package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class View extends JFrame {

    // Campos y botones
    public JTextField clientNameField = new JTextField();
    public JTextField clientLastNameField = new JTextField();
    public JTextField accountNumberField = new JTextField();
    public JTextField initialBalanceField = new JTextField();
    public JButton createAccountButton = new JButton("Crear Cuenta");
    public JButton depositButton = new JButton("Depositar");
    public JButton withdrawButton = new JButton("Retirar");
    public JButton transferButton = new JButton("Transferir");

    public JLabel balanceLabel = new JLabel("Saldo: 0.0"); // ⚡ saldo en tiempo real

    public JTable ledgerTable;
    public DefaultTableModel ledgerModel;

    public View() {
        setTitle("Banco Interactivo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLayout(new BorderLayout(10,10));

        // -----------------------------
        // PANEL FORMULARIO CLIENTE / CUENTA
        // -----------------------------
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createTitledBorder("Crear Cliente y Cuenta"));

        addField(formPanel, "Nombre:", clientNameField);
        addField(formPanel, "Apellido:", clientLastNameField);
        addField(formPanel, "Número Cuenta:", accountNumberField);
        addField(formPanel, "Saldo Inicial:", initialBalanceField);

        // Label de saldo
        balanceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(balanceLabel);
        formPanel.add(Box.createVerticalStrut(10));

        // Botón Crear Cuenta
        createAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(createAccountButton);

        add(formPanel, BorderLayout.WEST);

        // -----------------------------
        // PANEL LEDGER
        // -----------------------------
        ledgerModel = new DefaultTableModel();
        ledgerModel.addColumn("Evento");
        ledgerModel.addColumn("Cuenta");
        ledgerModel.addColumn("Monto");
        ledgerModel.addColumn("Fecha");
        ledgerTable = new JTable(ledgerModel);

        JScrollPane scrollPane = new JScrollPane(ledgerTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Ledger"));
        add(scrollPane, BorderLayout.CENTER);

        // -----------------------------
        // PANEL DE ACCIONES
        // -----------------------------
        JPanel actionPanel = new JPanel();
        actionPanel.add(depositButton);
        actionPanel.add(withdrawButton);
        actionPanel.add(transferButton);
        add(actionPanel, BorderLayout.SOUTH);

        // Hacer los campos más delgados
        Dimension fieldSize = new Dimension(100, 25);
        clientNameField.setMaximumSize(fieldSize);
        clientLastNameField.setMaximumSize(fieldSize);
        accountNumberField.setMaximumSize(fieldSize);
        initialBalanceField.setMaximumSize(fieldSize);

        setVisible(true);
    }

    private void addField(JPanel panel, String label, JTextField field) {
        JLabel lbl = new JLabel(label);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lbl);

        field.setMaximumSize(new Dimension(100, 25));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(field);

        panel.add(Box.createVerticalStrut(5));
    }
}
