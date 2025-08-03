package org.fabricante;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Frm_XAMPP_Control_Panel.getInstance().setVisible(true);
            Frm_XAMPP_Control_Panel.getInstance().setLocationRelativeTo(null);
            Frm_XAMPP_Control_Panel.getInstance().setResizable(false);
        });
    }
}
