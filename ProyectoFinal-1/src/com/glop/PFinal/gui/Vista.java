package com.glop.PFinal.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.glop.PFinal.base.Reserva;

import javax.swing.*;

public class Vista extends JDialog{
    final JFrame frame;
    JPanel panel1;
    JTextField txtNombre;
    JTextField txtTlfno;
    JTextField txtDireccion;
    JRadioButton rbtnBanquete;
    JRadioButton rbtnJornada;
    JRadioButton rbtnCongreso;
    JSpinner txtNumPersonas;
    JComboBox cbTipoCocina;
    JButton btnCancelar;
    JButton btnAceptar;
    JSpinner spinNumJornadas;
    JCheckBox checkHabitaciones;
    JTextField txtNumHabitaciones;
    JComboBox cbTipoHabitacion;
    JList list1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel5;
    JPanel panel4;
    JPanel panel1_1;
    DatePicker dpFecha;
    JScrollPane listReserva;
    JPanel panel0;
    JLabel nJornadasLbl;
    JLabel nHabitaciones;
    JLabel tipoHabitacion;
    ButtonGroup btnGrupoTipo;
//modelo
    DefaultListModel<Reserva> dlm;
    SpinnerNumberModel sm;


    public Vista() {
        frame = new JFrame("Vista");
        frame.setContentPane(panel0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);
        iniciarJList();
    }

    private void iniciarJList(){
        dlm = new DefaultListModel();
        list1.setModel(dlm);
    }

    void createUIComponents() {
        SpinnerNumberModel smj = new SpinnerNumberModel(0, 0, 30, 1);
        spinNumJornadas = new JSpinner(smj);

        sm = new SpinnerNumberModel(0, 0, 100, 1);
        txtNumPersonas = new JSpinner(sm);
    }
}
