package EjerciciosColecciones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EjerciciosGUI extends JFrame {

    private final JButton btnEjercicio1;
    private final JButton btnEjercicio2;
    private final JButton btnEjercicio3;
    private final JTextArea txtResultados;

    public EjerciciosGUI() {
        super("Ejercicios Exist");
        setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel(new GridLayout(1, 3));
        btnEjercicio1 = new JButton("Ejercicio 1");
        btnEjercicio2 = new JButton("Ejercicio 2");
        btnEjercicio3 = new JButton("Ejercicio 3");
        panelBotones.add(btnEjercicio1);
        panelBotones.add(btnEjercicio2);
        panelBotones.add(btnEjercicio3);
        add(panelBotones, BorderLayout.NORTH);

        txtResultados = new JTextArea(20, 60);
        txtResultados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtResultados);
        add(scrollPane, BorderLayout.CENTER);

        btnEjercicio1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtResultados.setText("");
                try {
                    Ejercicio1.main(new String[0]);
                    txtResultados.append("Ejercicio 1 ejecutado correctamente.");
                } catch (Exception ex) {
                    txtResultados.append("Error al ejecutar el ejercicio 1: " + ex.getMessage());
                }
            }
        });

        btnEjercicio2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtResultados.setText("");
                try {
                    Ejercicio2.main(new String[0]);
                } catch (IOException ex) {
                    txtResultados.append("Error al ejecutar el ejercicio 2: " + ex.getMessage());
                    return;
                }
                txtResultados.append("Ejercicio 2 ejecutado correctamente.");
            }
        });

        btnEjercicio3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtResultados.setText("");
                try {
                    Ejercicio3.main(new String[0]);
                } catch (IOException ex) {
                    txtResultados.append("Error al ejecutar el ejercicio 3: " + ex.getMessage());
                    return;
                }
                txtResultados.append("Ejercicio 3 ejecutado correctamente.");
            }
        });

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new EjerciciosGUI();
    }
}
