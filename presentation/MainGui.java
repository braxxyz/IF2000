package presentation;

import javax.swing.*;

public class MainGui {
    public static void main(String[] args) {
        // Asegurarnos de que la GUI use el estilo del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            View view = new View();       // crear la ventana
            new Controller(view);         // pasar la vista al controlador
        });
    }
}
 