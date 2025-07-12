package com.financia.presupuesto.presupuesto.dto;

import java.util.List;
import com.financia.presupuesto.presupuesto.model.SubcategoriaPresupuesto;

public class PresupuestoResponseDto {
    private List<SubcategoriaPresupuesto> subcategorias;

    public PresupuestoResponseDto() {}

    public PresupuestoResponseDto(List<SubcategoriaPresupuesto> subcategorias) {
        this.subcategorias = subcategorias;
    }

    public List<SubcategoriaPresupuesto> getSubcategorias() {
        return subcategorias;
    }

    public void setSubcategorias(List<SubcategoriaPresupuesto> subcategorias) {
        this.subcategorias = subcategorias;
    }
}

