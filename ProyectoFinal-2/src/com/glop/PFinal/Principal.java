package com.glop.PFinal;

import com.glop.PFinal.gui.Controlador;
import com.glop.PFinal.gui.Modelo;
import com.glop.PFinal.gui.MenuPrincipal;

public class Principal {
    public static void main(String[] args) {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(menuPrincipal,modelo);
    }
}
