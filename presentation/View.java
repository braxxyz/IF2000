package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class View extends JFrame {

    // Paneles y componentes
    public JTextField clientNameField = new JTextField(10);
    public JTextField clientLastNameField = new JTextField(10);
    public JTextField accountNumberField = new JTextField(10);
    public JTextField initialBalanceField = new JTextField(10);
    public JButton createAccountButton = new JButton("Crear Cuenta");
    public JButton depositButton = new JButton("Depositar");
    public JButton withdrawButton = new JButton("Retirar");
    public JButton transferButton = new JButton("Transferir");

    public JTable ledgerTable;
    public DefaultTableModel ledgerModel;

    public View() {
        setTitle("Banco Interactivo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Panel formulario cliente/cuenta
        JPanel formPanel = new JPanel(new GridLayout(6,2,5,5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Crear Cliente y Cuenta"));
        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(clientNameField);
        formPanel.add(new JLabel("Apellido:"));
        formPanel.add(clientLastNameField);
        formPanel.add(new JLabel("Número Cuenta:"));
        formPanel.add(accountNumberField);
        formPanel.add(new JLabel("Saldo Inicial:"));
        formPanel.add(initialBalanceField);
        formPanel.add(createAccountButton);

        add(formPanel, BorderLayout.WEST);

        // Panel ledger
        ledgerModel = new DefaultTableModel();
        ledgerModel.addColumn("Evento");
        ledgerModel.addColumn("Cuenta");
        ledgerModel.addColumn("Monto");
        ledgerModel.addColumn("Fecha");
        ledgerTable = new JTable(ledgerModel);

        JScrollPane scrollPane = new JScrollPane(ledgerTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Ledger"));
        add(scrollPane, BorderLayout.CENTER);

        // Panel de acciones (Depósito, Retiro, Transferencia)
        JPanel actionPanel = new JPanel();
        actionPanel.add(depositButton);
        actionPanel.add(withdrawButton);
        actionPanel.add(transferButton);
        add(actionPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
