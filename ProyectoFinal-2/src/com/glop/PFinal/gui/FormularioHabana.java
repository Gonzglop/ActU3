package com.glop.PFinal.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.glop.PFinal.base.Reserva;

import javax.swing.*;
import java.awt.event.*;

public class FormularioHabana extends JDialog {
    JPanel contentPane;
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

    public FormularioHabana() {
        setSize(550,600);

        setContentPane(panel0);
        setModal(true);
        getRootPane().setDefaultButton(btnAceptar);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        setLocationRelativeTo(null);
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
