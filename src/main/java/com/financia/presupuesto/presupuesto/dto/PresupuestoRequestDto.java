package com.financia.presupuesto.presupuesto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Pattern;

public class PresupuestoRequestDto {
    @NotNull(message = "El sueldo mensual es obligatorio")
    @Positive(message = "El sueldo mensual debe ser mayor a 0")
    private double sueldoMensual;

    @NotBlank(message = "El tipo de vivienda es obligatorio")
    @Pattern(regexp = "propia sin hipoteca|propia con hipoteca|alquilada",
             message = "Tipo de vivienda debe ser: propia sin hipoteca, propia con hipoteca, o alquilada")
    private String tipoVivienda;

    @NotNull(message = "Debe especificar si vive solo")
    private boolean viveSolo;

    @NotNull(message = "Debe especificar si tiene hijos")
    private boolean tieneHijos;

    @NotNull(message = "Debe especificar si usa carro")
    private boolean usaCarro;

    @NotBlank(message = "La frecuencia de comidas fuera es obligatoria")
    @Pattern(regexp = "alta|media|baja",
             message = "Frecuencia de comidas fuera debe ser: alta, media o baja")
    private String comidasFueraFrecuencia;

    @NotNull(message = "Debe especificar si tiene meta de ahorro")
    private boolean tieneMetaAhorro;

    @NotNull(message = "Debe especificar si tiene deudas")
    private boolean tieneDeudas;

    public PresupuestoRequestDto() {}

    // Getters y setters
    public double getSueldoMensual() { return sueldoMensual; }
    public void setSueldoMensual(double sueldoMensual) { this.sueldoMensual = sueldoMensual; }
    public String getTipoVivienda() { return tipoVivienda; }
    public void setTipoVivienda(String tipoVivienda) { this.tipoVivienda = tipoVivienda; }
    public boolean isViveSolo() { return viveSolo; }
    public void setViveSolo(boolean viveSolo) { this.viveSolo = viveSolo; }
    public boolean isTieneHijos() { return tieneHijos; }
    public void setTieneHijos(boolean tieneHijos) { this.tieneHijos = tieneHijos; }
    public boolean isUsaCarro() { return usaCarro; }
    public void setUsaCarro(boolean usaCarro) { this.usaCarro = usaCarro; }
    public String getComidasFueraFrecuencia() { return comidasFueraFrecuencia; }
    public void setComidasFueraFrecuencia(String comidasFueraFrecuencia) { this.comidasFueraFrecuencia = comidasFueraFrecuencia; }
    public boolean isTieneMetaAhorro() { return tieneMetaAhorro; }
    public void setTieneMetaAhorro(boolean tieneMetaAhorro) { this.tieneMetaAhorro = tieneMetaAhorro; }
    public boolean isTieneDeudas() { return tieneDeudas; }
    public void setTieneDeudas(boolean tieneDeudas) { this.tieneDeudas = tieneDeudas; }
}
