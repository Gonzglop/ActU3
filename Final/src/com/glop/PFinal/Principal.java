package com.glop.PFinal;

import com.glop.PFinal.gui.Controlador;
import com.glop.PFinal.gui.Modelo;
import com.glop.PFinal.gui.Vista;

public class Principal {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista,modelo);

    }
}
