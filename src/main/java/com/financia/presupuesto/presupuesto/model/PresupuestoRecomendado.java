package com.financia.presupuesto.presupuesto.model;

import java.util.List;

public class PresupuestoRecomendado {
    private List<SubcategoriaPresupuesto> subcategorias;

    public PresupuestoRecomendado() {}

    public PresupuestoRecomendado(List<SubcategoriaPresupuesto> subcategorias) {
        this.subcategorias = subcategorias;
    }

    public List<SubcategoriaPresupuesto> getSubcategorias() {
        return subcategorias;
    }

    public void setSubcategorias(List<SubcategoriaPresupuesto> subcategorias) {
        this.subcategorias = subcategorias;
    }
}

