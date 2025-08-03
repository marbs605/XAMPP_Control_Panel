package org.fabricante;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;

public class Frm_XAMPP_Control_Panel extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String xamppPath;
    private static Frm_XAMPP_Control_Panel instance;

    public static Frm_XAMPP_Control_Panel getInstance() {
        if (instance == null) {
            instance = new Frm_XAMPP_Control_Panel();
        }
        return instance;
    }

    private Frm_XAMPP_Control_Panel() {
        setTitle("XAMPP Control Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 537, 259);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        xamppPath = loadXAMPPPath();

        JButton startApacheBtn = new JButton("Start Apache Server");
        startApacheBtn.addActionListener(e -> runCommand("cmd /c start \"\" \"" + xamppPath + "\\apache_start.bat\""));
        startApacheBtn.setBounds(10, 10, 250, 40);
        customizeButton(startApacheBtn);
        contentPane.add(startApacheBtn);

        JButton stopApacheBtn = new JButton("Stop Apache Server");
        stopApacheBtn.addActionListener(e -> runCommand("cmd /c start \"\" \"" + xamppPath + "\\apache_stop.bat\""));
        stopApacheBtn.setBounds(275, 10, 236, 40);
        customizeButton(stopApacheBtn);
        contentPane.add(stopApacheBtn);

        JButton startMySQLBtn = new JButton("Start MySQL Database");
        startMySQLBtn.addActionListener(e -> runCommand("cmd /c start \"\" \"" + xamppPath + "\\mysql_start.bat\""));
        startMySQLBtn.setBounds(10, 61, 250, 38);
        customizeButton(startMySQLBtn);
        contentPane.add(startMySQLBtn);

        JButton stopMySQLBtn = new JButton("Stop MySQL Database");
        stopMySQLBtn.addActionListener(e -> runCommand("cmd /c start \"\" \"" + xamppPath + "\\mysql_stop.bat\""));
        stopMySQLBtn.setBounds(275, 61, 236, 40);
        customizeButton(stopMySQLBtn);
        contentPane.add(stopMySQLBtn);

        JButton openXAMPPFolderBtn = new JButton("Open XAMPP Folder");
        openXAMPPFolderBtn.addActionListener(e -> runCommand("explorer \"" + xamppPath + "\""));
        openXAMPPFolderBtn.setBounds(10, 110, 250, 40);
        customizeButton(openXAMPPFolderBtn);
        contentPane.add(openXAMPPFolderBtn);

        JButton changeXAMPPFolderBtn = new JButton("Change XAMPP Folder");
        changeXAMPPFolderBtn.addActionListener(e -> {
            FrmSaveToFile.getInstance().setVisible(true);
            FrmSaveToFile.getInstance().setLocationRelativeTo(null);
            FrmSaveToFile.getInstance().setResizable(false);
            dispose();
        });
        changeXAMPPFolderBtn.setBounds(275, 111, 236, 38);
        customizeButton(changeXAMPPFolderBtn);
        contentPane.add(changeXAMPPFolderBtn);

        JButton createFolderOnHtdocsBtn = new JButton("Create folder on htdocs");
        createFolderOnHtdocsBtn.addActionListener(e -> {
            FrmCreateHTDOCSFolder.getInstance().setVisible(true);
            FrmCreateHTDOCSFolder.getInstance().setLocationRelativeTo(null);
            FrmCreateHTDOCSFolder.getInstance().setResizable(false);
            dispose();
        });
        createFolderOnHtdocsBtn.setBounds(10, 166, 250, 40);
        customizeButton(createFolderOnHtdocsBtn);
        contentPane.add(createFolderOnHtdocsBtn);

        JButton openHtdocsOnVSCodeBtn = new JButton("Open htdocs on VSCode");
        openHtdocsOnVSCodeBtn.addActionListener(e -> runCommand("cmd /c code \"" + xamppPath + "\\htdocs\""));
        openHtdocsOnVSCodeBtn.setBounds(275, 166, 236, 40);
        customizeButton(openHtdocsOnVSCodeBtn);
        contentPane.add(openHtdocsOnVSCodeBtn);
    }

    private void customizeButton(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.BLACK);
        btn.setBackground(Color.WHITE);
    }

    private void runCommand(String command) {
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error running command:\n" + e.getMessage());
        }
    }

    private String loadXAMPPPath() {
        File file = new File("records.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine();
                if (line != null && !line.trim().isEmpty()) {
                    return line.trim();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error reading XAMPP path:\n" + e.getMessage());
            }
        }
        JOptionPane.showMessageDialog(this, "No saved XAMPP path found. Please set it.");
        FrmSaveToFile.getInstance().setVisible(true);
        FrmSaveToFile.getInstance().setLocationRelativeTo(null);
        dispose();
        return "";
    }
}
