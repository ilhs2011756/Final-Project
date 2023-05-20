package com.marie.resetter;

import javax.swing.*;

public class GUI {
    private JFrame frame;

    public GUI() {
        frame = new JFrame("Minecraft Co-Op Resetter");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
