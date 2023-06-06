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
        doneButton.addActionListener(e -> this.setVisible(false));
        doneButton.setBounds(150, 248, 100, 50);
        doneButton.setFocusPainted(false);
        doneButton.setBackground(GRAY);
        this.add(doneButton);


    }
}
