package com.marie.resetter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class GUI {
    public static Color brighterGray = Color.GRAY.brighter();
    public GUI() {
//        create JFrame
        JFrame frame = new JFrame("Co-Op Resetter");
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.GRAY);

//        initialize settings menu
        SettingsScreen settingsScreen = new SettingsScreen();

//        create button to open the settings menu
        JButton settingsButton = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("/settings_icon.png"))));
        settingsButton.setBounds(252, 2,30,30);
        settingsButton.addActionListener(e -> toggleSettings(settingsScreen));
        settingsButton.setFocusPainted(false);
        settingsButton.setBackground(brighterGray);
        frame.add(settingsButton);

//        create button to start the server
        JButton startServerButton = new JButton("Start Server");
        startServerButton.addActionListener(e -> {
            try {
                Resetter.startServer();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        startServerButton.setBounds(2, 52, 200, 50);
        startServerButton.setFocusPainted(false);
        startServerButton.setBackground(brighterGray);
        frame.add(startServerButton);

//        create button to stop the server
        JButton stopServerButton = new JButton("Stop Server");
        stopServerButton.addActionListener(e -> Resetter.stopServer());
        stopServerButton.setBounds(2, 100, 200, 50);
        stopServerButton.setFocusPainted(false);
        stopServerButton.setBackground(brighterGray);
        frame.add(stopServerButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            try {
                Resetter.reset();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        resetButton.setBounds(2, 2, 200, 50);
        resetButton.setFocusPainted(false);
        resetButton.setBackground(brighterGray);
        frame.add(resetButton);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//  simple method to toggle the settings screen's visibility
    private void toggleSettings(SettingsScreen settingsScreen) {
        settingsScreen.setVisible(!settingsScreen.isVisible());
    }
}
