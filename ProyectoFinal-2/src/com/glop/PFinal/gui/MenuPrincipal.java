package com.glop.PFinal.gui;

import javax.swing.*;

public class MenuPrincipal {
    final JFrame frame1;
     JPanel panel2;
     JButton salonHabanaButton;
     JButton salonKyotoButton;
     JButton salonParísButton;
     JPanel panel1;

    public MenuPrincipal() {
        frame1 = new JFrame("MenuPrincipal");
        frame1.setContentPane(panel1);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setVisible(true);

        frame1.setLocationRelativeTo(null);
    }

}
