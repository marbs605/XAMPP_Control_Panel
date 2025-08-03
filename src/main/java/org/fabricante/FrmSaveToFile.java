package org.fabricante;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FrmSaveToFile extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField filePathTxtField;
    private static FrmSaveToFile instance;

    public static FrmSaveToFile getInstance() {
        if (instance == null) {
            instance = new FrmSaveToFile();
        }
        return instance;
    }

    private FrmSaveToFile() {
        setTitle("Create path");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 344, 169);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbl = new JLabel("Enter your XAMPP folder path here");
        lbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        lbl.setBounds(34, 11, 256, 22);
        contentPane.add(lbl);

        filePathTxtField = new JTextField();
        filePathTxtField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        filePathTxtField.setBounds(29, 44, 272, 29);
        contentPane.add(filePathTxtField);

        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> {
            String inputPath = filePathTxtField.getText().trim();
            if (isValidPath(inputPath)) {
                saveTextToFile(inputPath);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid path. Example: C:\\\\xampp");
            }
        });
        saveBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
        saveBtn.setBackground(Color.WHITE);
        saveBtn.setBounds(170, 84, 131, 29);
        contentPane.add(saveBtn);

        JButton returnBtn = new JButton("Return");
        returnBtn.addActionListener(e -> {
            Frm_XAMPP_Control_Panel.getInstance().setVisible(true);
            dispose();
        });
        returnBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
        returnBtn.setBackground(Color.WHITE);
        returnBtn.setBounds(29, 84, 131, 29);
        contentPane.add(returnBtn);
    }

    private boolean isValidPath(String path) {
        return path.matches("^[a-zA-Z]:[\\\\/](?:[^<>:\"/\\\\|?*\\n]+[\\\\/]?)*$");
    }

    private void saveTextToFile(String text) {
        File isFileExist = findExistingRecordsFile();

        if (isFileExist == null) {
            isFileExist = new File("records.txt");
        }

        File file = new File("records.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(text);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Path saved to:\n" + file.getAbsolutePath());
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(this, "Error:\n" + ioe.getMessage());
        }
    }

    private File findExistingRecordsFile() {
        String userHome = System.getProperty("user.home");
        File desktopFile = new File(userHome + File.separator + "Desktop" + File.separator + "records.txt");
        File downloadsFile = new File(userHome + File.separator + "Downloads" + File.separator + "records.txt");
        File currentDirFile = new File("records.txt");

        if (desktopFile.exists()) return desktopFile;
        if (downloadsFile.exists()) return downloadsFile;
        if (currentDirFile.exists()) return currentDirFile;

        return null;
    }

}
