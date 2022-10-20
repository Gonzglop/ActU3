package com.glop.PFinal.gui;

import com.glop.PFinal.base.Reserva;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Modelo {
    private final static String RUTA = "datosReservas.bin";
    private ArrayList<Reserva> listaReservas;

    public Modelo(){
        listaReservas = new ArrayList<>();
    }

    public ArrayList<Reserva> getListaReservas(){
        return listaReservas;
    }

    public void altaReserva(String nombre, String tlfno, String direccion, LocalDate fecha, String tipo, int numPersonas,
                            String tipoCocina, int numJornadas, boolean habitacion, int numHabitaciones, String tipoHabitacion){
        Reserva reserva = new Reserva( nombre,  tlfno,  direccion,  fecha,  tipo,  numPersonas,
                tipoCocina,  numJornadas,  habitacion,  numHabitaciones,  tipoHabitacion);

        listaReservas.add(reserva);
    }

    public Reserva buscarReserva(){
        return null;
    }

    public void eliminarReserva(Reserva reserva){
        listaReservas.remove(reserva);
    }


    public void cargarDatosFichero() throws IOException, ClassNotFoundException {
        File fichero = new File(RUTA);
        if (fichero.exists()){
            FileInputStream flujoEntrada = new FileInputStream(fichero);
            ObjectInputStream deserializador = new ObjectInputStream(flujoEntrada);
            listaReservas = (ArrayList<Reserva>) deserializador.readObject();
            deserializador.close();
        }
    }

    public void guardarDatosFichero() throws IOException {

        FileOutputStream flujoSalida = new FileOutputStream(RUTA);
        ObjectOutputStream serializador = new ObjectOutputStream((flujoSalida));
        serializador.writeObject(listaReservas);
        serializador.close();

    }
}