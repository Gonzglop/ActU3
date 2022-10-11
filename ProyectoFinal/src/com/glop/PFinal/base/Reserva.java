package com.glop.PFinal.base;

import java.time.LocalDate;

public class Reserva {
    private static int cont = 0;
    private int id;
    private String nombre;
    private String tlfno;
    private String direccion;
    private LocalDate fecha;
    private String tipo;
    private int numPersonas;
    private String  tipoCocina;

    // solo tipo = CONGRESO
    private int numJornadas;
    private boolean habitacion;

    //solo habitacion = TRUE
    private int numHabitaciones;
    private String tipoHabitacion;

    public Reserva(String nombre, String tlfno, String direccion, LocalDate fecha, String tipo, int numPersonas,
                   String tipoCocina, int numJornadas, boolean habitacion, int numHabitaciones, String tipoHabitacion) {
        this.id = cont++;
        this.nombre = nombre;
        this.tlfno = tlfno;
        this.direccion = direccion;
        this.fecha = fecha;
        this.tipo = tipo;
        this.numPersonas = numPersonas;
        this.tipoCocina = tipoCocina;
        this.numJornadas = numJornadas;
        this.habitacion = habitacion;
        this.numHabitaciones = numHabitaciones;
        this.tipoHabitacion = tipoHabitacion;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTlfno() {
        return tlfno;
    }

    public void setTlfno(String tlfno) {
        this.tlfno = tlfno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    public String getTipoCocina() {
        return tipoCocina;
    }

    public void setTipoCocina(String tipoCocina) {
        this.tipoCocina = tipoCocina;
    }

    public int getNumJornadas() {
        return numJornadas;
    }

    public void setNumJornadas(int numJornadas) {
        this.numJornadas = numJornadas;
    }

    public boolean isHabitacion() {
        return habitacion;
    }

    public void setHabitacion(boolean habitacion) {
        this.habitacion = habitacion;
    }

    public int getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    @Override
    public String toString() {
        return "Reserva: \n\n" +
                "\tID: " + id + '\n' +
                "\tNombre: " + nombre + '\n' +
                "\tTeléfono: " + tlfno + '\n' +
                "\tDirección: " + direccion + '\n' +
                "\tFecha: " + fecha +'\n' +
                "\tTipo: " + tipo + '\n' +
                "\tNº de personas: " + numPersonas +'\n' +
                "\tTipo de cocina: " + tipoCocina + '\n' +
                "\tNº de jornadas: " + numJornadas +'\n' +
                "\tHabitaciones: " + habitacion +'\n' +
                "\tNº de habitaciones: " + numHabitaciones +'\n' +
                "\tTipo de habitación: " + tipoHabitacion + '\n';
    }
}
