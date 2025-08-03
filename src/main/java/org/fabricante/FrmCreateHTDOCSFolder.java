package org.fabricante;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FrmCreateHTDOCSFolder extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField createHtdocsFolderTxtfield;
    private String xamppPath;
    private static FrmCreateHTDOCSFolder instance;

    public static FrmCreateHTDOCSFolder getInstance() {
        if (instance == null) {
            instance = new FrmCreateHTDOCSFolder();
        }
        return instance;
    }

    private FrmCreateHTDOCSFolder() {
        setTitle("Create htdocs folder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 345, 168);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbl = new JLabel("Create your HTDOCS Folder");
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        lbl.setBounds(36, 10, 256, 22);
        contentPane.add(lbl);

        createHtdocsFolderTxtfield = new JTextField();
        createHtdocsFolderTxtfield.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        createHtdocsFolderTxtfield.setBounds(32, 43, 272, 29);
        contentPane.add(createHtdocsFolderTxtfield);

        JButton returnBtn = new JButton("Return");
        returnBtn.addActionListener(e -> {
            Frm_XAMPP_Control_Panel.getInstance().setVisible(true);
            Frm_XAMPP_Control_Panel.getInstance().setLocationRelativeTo(null);
            Frm_XAMPP_Control_Panel.getInstance().setResizable(false);
            dispose();
        });
        returnBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
        returnBtn.setBackground(Color.WHITE);
        returnBtn.setBounds(32, 83, 131, 29);
        contentPane.add(returnBtn);

        JButton createBtn = new JButton("Create");
        createBtn.addActionListener(e -> {
            String folderName = createHtdocsFolderTxtfield.getText().trim();
            if (folderName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a folder name.");
                return;
            }

            File newFolder = new File(xamppPath + "\\htdocs\\" + folderName);
            if (newFolder.exists()) {
                JOptionPane.showMessageDialog(null, "Folder already exists.");
            } else {
                if (newFolder.mkdirs()) {
                    JOptionPane.showMessageDialog(null, "Folder created at:\n" + newFolder.getAbsolutePath());
                    Frm_XAMPP_Control_Panel.getInstance().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to create folder.");
                }
            }
        });
        createBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
        createBtn.setBackground(Color.WHITE);
        createBtn.setBounds(177, 83, 131, 29);
        contentPane.add(createBtn);

        xamppPath = loadXAMPPPath();
    }

    private String loadXAMPPPath() {
        File file = new File("records.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String path = reader.readLine();
                if (path != null && !path.trim().isEmpty()) {
                    return path.trim();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error reading path:\n" + e.getMessage());
            }
        }
        JOptionPane.showMessageDialog(this, "No saved XAMPP path found. Returning to main GUI.");
        Frm_XAMPP_Control_Panel.getInstance().setVisible(true);
        dispose();
        return "";
    }
}
