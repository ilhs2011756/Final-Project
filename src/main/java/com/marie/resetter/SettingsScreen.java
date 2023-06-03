package com.marie.resetter;

import javax.swing.*;
import java.awt.*;

public class SettingsScreen extends JFrame {
//    Extension of JFrame to make it a little easier and less cluttered to initialize the settings frame
    public SettingsScreen() {
        super("Settings");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.getContentPane().setBackground(Color.GRAY.brighter());
    }
}
