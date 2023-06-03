package com.marie.resetter;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GUI {
    private JFrame frame;

    public GUI() {
//        create JFrame
        frame = new JFrame("Minecraft Co-Op Resetter");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.GRAY);

        SettingsScreen settingsScreen = new SettingsScreen();

        JButton settingsButton =new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("/settings_icon.png"))));
        settingsButton.setBounds(453, 2,30,30);
        settingsButton.addActionListener(e -> toggleSettings(settingsScreen));
        settingsButton.setFocusPainted(false);
        frame.add(settingsButton);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
//  simple method to toggle the settings screen's visibility
    private void toggleSettings(SettingsScreen settingsScreen) {
        settingsScreen.setVisible(!settingsScreen.isVisible());
    }
}
