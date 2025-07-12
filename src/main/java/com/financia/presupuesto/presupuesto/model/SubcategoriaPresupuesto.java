package com.financia.presupuesto.presupuesto.model;

public class SubcategoriaPresupuesto {
    private String nombre;
    private double porcentaje;
    private double monto;

    public SubcategoriaPresupuesto() {}

    public SubcategoriaPresupuesto(String nombre, double porcentaje, double monto) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.monto = monto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}

