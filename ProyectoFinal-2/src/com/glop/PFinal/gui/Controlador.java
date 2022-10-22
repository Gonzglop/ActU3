package com.glop.PFinal.gui;

import com.glop.PFinal.base.Reserva;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Objects;

public class Controlador implements ActionListener, WindowListener {

    private MenuPrincipal menuPrincipal;
    private Modelo modelo;
    private FormularioHabana formularioHabana;

    public Controlador(MenuPrincipal menuPrincipal, Modelo modelo) {
        this.modelo = modelo;
        this.menuPrincipal = menuPrincipal;
        try {
            modelo.cargarDatosFichero();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        anadirActionListeners(this);

        //refrescarListaReservas();
    }


    public void anadirActionListeners(ActionListener listener) {
        menuPrincipal.salonHabanaButton.addActionListener(listener);
    }

    private void añadirActionListenersFormHabana(ActionListener listener) {
        formularioHabana.btnAceptar.addActionListener(listener);
        formularioHabana.btnCancelar.addActionListener(listener);
        formularioHabana.rbtnCongreso.addActionListener(listener);
        formularioHabana.rbtnBanquete.addActionListener(listener);
        formularioHabana.rbtnJornada.addActionListener(listener);
        formularioHabana.checkHabitaciones.addActionListener(listener);
        formularioHabana.setVisible(true);
    }

    private void anadirWindowListeners(WindowListener listener) {
        formularioHabana.addWindowListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();

        switch (comando) {
            case "Aceptar":
                String cadenaErrores = "";
                cadenaErrores = compruebaCamposVacios(cadenaErrores);

                if (Objects.equals(cadenaErrores, "")) {

                    realizaReserva();
                    refrescarListaReservas();

                    JOptionPane.showMessageDialog(null, modelo.getListaReservas(), "¡Reserva realizada con éxito!", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, cadenaErrores, "No se ha podido realizar la reserva", JOptionPane.WARNING_MESSAGE);
                }
                break;

            case "Cancelar":
                formularioHabana.dispose();
                break;
            case "Congreso":
                activaPanel2();
                formularioHabana.sm.setValue(0);
                formularioHabana.sm.setMaximum(50);
                break;
            case "Banquete":
                desactivaPanel2();
                formularioHabana.sm.setMaximum(100);
                break;
            case "Jornada":
                desactivaPanel2();
                formularioHabana.sm.setValue(0);
                formularioHabana.sm.setMaximum(50);
                break;
            case "Habitaciones":
                activDesactivPanel3();
                break;

            case "SalonHabana":
                formularioHabana = new FormularioHabana();
                añadirActionListenersFormHabana(this);
                anadirWindowListeners(this);
                break;
        }
    }

    private void realizaReserva() {
        modelo.altaReserva(
                formularioHabana.txtNombre.getText(),
                formularioHabana.txtTlfno.getText(),
                formularioHabana.txtDireccion.getText(),
                formularioHabana.dpFecha.getDate(),
                obtieneRadioBtn(),
                (Integer) formularioHabana.txtNumPersonas.getValue(),
                String.valueOf(formularioHabana.cbTipoCocina.getSelectedItem()),
                (Integer) formularioHabana.spinNumJornadas.getValue(),
                formularioHabana.checkHabitaciones.isSelected(),
                Integer.parseInt(formularioHabana.txtNumHabitaciones.getText()),
                String.valueOf(formularioHabana.cbTipoHabitacion.getSelectedItem())
        );

    }

    private String compruebaCamposVacios(String cadenaErrores) {
        if (Objects.equals(formularioHabana.txtNombre.getText(), "")) {
            cadenaErrores += "No has introducido el nombre\n";
        }
        if (Objects.equals(formularioHabana.txtTlfno.getText(), "")) {
            cadenaErrores += "No has introducido el teléfono\n";
        }
        if (Objects.equals(formularioHabana.txtDireccion.getText(), "")) {
            cadenaErrores += "No has introducido la dirección\n";
        }
        if (formularioHabana.dpFecha.getDate() == null) {
            cadenaErrores += "No has introducido la fecha\n";
        }
        if (formularioHabana.txtNumPersonas.getValue() == "" | (int) formularioHabana.txtNumPersonas.getValue() <= 0) {
            cadenaErrores += "No has introducido el nº de personas\n";
        }

        if (String.valueOf(formularioHabana.cbTipoCocina.getSelectedItem()) == "Seleccione el tipo de cocina") {
            cadenaErrores += "No has seleccionado el tipo de cocina\n";
        }

        if (formularioHabana.rbtnCongreso.isSelected()) {
            if ((int) formularioHabana.spinNumJornadas.getValue() <= 0) {
                cadenaErrores += "No has introducido el nº de jornadas\n";
            }
            if (formularioHabana.checkHabitaciones.isSelected()) {
                if (Integer.parseInt(formularioHabana.txtNumHabitaciones.getText()) <= 0 | Objects.equals(formularioHabana.txtNumHabitaciones.getText(), "")) {
                    cadenaErrores += "No has introducido el nº de habitaciones\n";
                }
                if (String.valueOf(formularioHabana.cbTipoHabitacion.getSelectedItem()) == "Seleccione...") {
                    cadenaErrores += "No has seleccionado el tipo de habitación\n";
                }
            }
        }
        return cadenaErrores;
    }

    private void activDesactivPanel3() {
        if (formularioHabana.checkHabitaciones.isSelected()) {
            formularioHabana.nHabitaciones.setEnabled(true);
            formularioHabana.txtNumHabitaciones.setEnabled(true);
            formularioHabana.tipoHabitacion.setEnabled(true);
            formularioHabana.cbTipoHabitacion.setEnabled(true);
        } else {
            formularioHabana.nHabitaciones.setEnabled(false);
            formularioHabana.txtNumHabitaciones.setEnabled(false);
            formularioHabana.tipoHabitacion.setEnabled(false);
            formularioHabana.cbTipoHabitacion.setEnabled(false);
        }
    }


    private void refrescarListaReservas() {
        formularioHabana.dlm.clear();
        for (Reserva reserva : modelo.getListaReservas()) {
            formularioHabana.dlm.addElement(reserva);
        }
    }

    private String obtieneRadioBtn() {
        String rBtnValor = null;
        if (formularioHabana.rbtnJornada.isSelected()) {
            rBtnValor = "Jornada";
        } else if (formularioHabana.rbtnBanquete.isSelected()) {
            rBtnValor = "Banquete";
        } else {
            rBtnValor = "Congreso";
        }
        return rBtnValor;
    }


    private void activaPanel2() {
        if (formularioHabana.rbtnCongreso.isSelected()) {
            formularioHabana.spinNumJornadas.setEnabled(true);
            formularioHabana.checkHabitaciones.setEnabled(true);
            formularioHabana.nJornadasLbl.setEnabled(true);
        }
    }

    private void desactivaPanel2() {
        if (!formularioHabana.rbtnCongreso.isSelected()) {
            formularioHabana.spinNumJornadas.setEnabled(false);
            formularioHabana.checkHabitaciones.setEnabled(false);
            formularioHabana.nJornadasLbl.setEnabled(false);
            formularioHabana.nHabitaciones.setEnabled(false);
            formularioHabana.txtNumHabitaciones.setEnabled(false);
            formularioHabana.tipoHabitacion.setEnabled(false);
            formularioHabana.cbTipoHabitacion.setEnabled(false);
            formularioHabana.checkHabitaciones.setSelected(false);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            modelo.guardarDatosFichero();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}