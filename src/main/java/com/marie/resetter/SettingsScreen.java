package com.marie.resetter;

import javax.swing.*;
import javax.swing.text.TextAction;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.File;

import static com.marie.resetter.GUI.brighterGray;
import static com.marie.resetter.Resetter.config;

import static java.awt.Color.GRAY;

public class SettingsScreen extends JFrame {
//    Extension of JFrame to make it a little easier and less cluttered to initialize the settings frame
    public SettingsScreen() {
        super("Settings");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setLayout(null);
        this.getContentPane().setBackground(brighterGray);

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
        JTextField worldField = new JTextField(config.getWorld().toString());
        worldField.setBounds(42, 2, 200, 13);
        worldField.setBorder(BorderFactory.createEmptyBorder());
        System.out.println(worldField.getText());
        worldField.addActionListener(e -> {
//            config.setWorld(new File((JTextField)e.getSource());
        });
        worldField.addPropertyChangeListener();
        this.add(worldField);

//        option to set server jar path
        JLabel jarLabel = new JLabel("Server Jar:");
        jarLabel.setBounds(2, 30, 70, 15);
        this.add(jarLabel);
        JTextField jarField = new JTextField(config.getServerJar().toString());
        jarField.setBounds(72, 30, 200, 13);
        jarField.setBorder(BorderFactory.createEmptyBorder());
        this.add(jarField);

//        option to change the seed if useSeed is true
        JLabel seedLabel = new JLabel("Seed:");
        seedLabel.setBounds(2, 110, 40, 15);
        this.add(seedLabel);
        JTextField seedField = new JTextField(config.getSeed());
        seedField.setBounds(43, 110, 200, 15);
        seedField.setBorder(BorderFactory.createEmptyBorder());
        seedField.setFocusable(config.isUseSeed());
        this.add(seedField);

//        option to change useSeed
        JCheckBox useSeedButton = new JCheckBox("Use Seed", config.isUseSeed());
        useSeedButton.setBounds(2, 70, 85, 15);
        useSeedButton.addItemListener(e -> {
            config.setUseSeed(e.getStateChange() == ItemEvent.SELECTED);
            seedField.setFocusable(e.getStateChange() == ItemEvent.SELECTED);
        });
        useSeedButton.setFocusPainted(false);
        useSeedButton.setBackground(brighterGray);
        this.add(useSeedButton);

//        option to change minRam
        JLabel minRamLabel = new JLabel("Min. Ram:");
        minRamLabel.setBounds(2, 150, 60, 15);
        this.add(minRamLabel);
        JTextField minRamField = new JTextField(String.valueOf(config.getMinRam()));
        minRamField.setBounds(65, 150, 200, 15);
        minRamField.setBorder(BorderFactory.createEmptyBorder());
        this.add(minRamField);

//        option to change maxRam
        JLabel maxRamLabel = new JLabel("Max. Ram:");
        maxRamLabel.setBounds(2, 190, 65, 15);
        this.add(maxRamLabel);
        JTextField maxRamField = new JTextField(String.valueOf(config.getMaxRam()));
        maxRamField.setBounds(70, 190, 200, 15);
        maxRamField.setBorder(BorderFactory.createEmptyBorder());
        this.add(maxRamField);
    }

    private void close() {
        config.save();
        this.setVisible(false);
    }
}
