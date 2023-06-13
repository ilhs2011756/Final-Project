package com.marie.resetter;

import javax.swing.*;
import static java.awt.Color.GRAY;

public class SettingsScreen extends JFrame {
//    Extension of JFrame to make it a little easier and less cluttered to initialize the settings frame
    public SettingsScreen() {
        super("Settings");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setLayout(null);
        this.getContentPane().setBackground(GUI.brighterGray);

        JButton doneButton = new JButton("Done");
        doneButton.addActionListener(e -> this.close());
        doneButton.setBounds(150, 248, 100, 50);
        doneButton.setFocusPainted(false);
        doneButton.setBackground(GRAY);
        this.add(doneButton);

//        option to set the world path
        JLabel worldLabel = new JLabel("World:");
        worldLabel.setBounds(2, 2, 40, 10);
        this.add(worldLabel);
        JTextField worldField = new JTextField(Resetter.config.getWorld().toString());
        worldField.setBounds(42, 2, 200, 13);
        worldField.setBorder(BorderFactory.createEmptyBorder());
        this.add(worldField);

//        option to set server jar path
        JLabel jarLabel = new JLabel("Server Jar:");
        jarLabel.setBounds(2, 30, 70, 15);
        this.add(jarLabel);
        JTextField jarField = new JTextField(Resetter.config.getServerJar().toString());
        jarField.setBounds(72, 30, 200, 13);
        jarField.setBorder(BorderFactory.createEmptyBorder());
        this.add(jarField);

//        option to change useSeed
        JRadioButton useSeedButton = new JRadioButton("Use Seed");
        useSeedButton.setBounds(5, 5, 400, 100);
        this.add(useSeedButton);
    }

    private void close() {
        Resetter.config.save();
        this.setVisible(false);
    }
}
