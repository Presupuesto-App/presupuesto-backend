package com.financia.presupuesto.presupuesto.service;

import com.financia.presupuesto.presupuesto.dto.PresupuestoRequestDto;
import com.financia.presupuesto.presupuesto.dto.PresupuestoResponseDto;
import com.financia.presupuesto.presupuesto.model.SubcategoriaPresupuesto;
import com.financia.presupuesto.presupuesto.utils.PresupuestoUtils;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecomendadorService {

    public PresupuestoResponseDto recomendar(PresupuestoRequestDto request) {
        double sueldo = request.getSueldoMensual();

        // 1. Crear subcategorías base del modelo 50/30/20
        List<SubcategoriaPresupuesto> subcategorias = crearSubcategoriasBase(sueldo);

        // 2. Aplicar todas las reglas de negocio según las respuestas del usuario
        PresupuestoUtils.aplicarAjustes(subcategorias, request);

        // 3. Normalizar porcentajes para que sumen 100% y calcular montos finales
        PresupuestoUtils.normalizarPorcentajes(subcategorias, sueldo);

        return new PresupuestoResponseDto(subcategorias);
    }

    private List<SubcategoriaPresupuesto> crearSubcategoriasBase(double sueldo) {
        List<SubcategoriaPresupuesto> subcategorias = new ArrayList<>();

        // Necesidades (50%)
        subcategorias.add(new SubcategoriaPresupuesto("Vivienda", 15, sueldo * 0.15));
        subcategorias.add(new SubcategoriaPresupuesto("Servicios", 5, sueldo * 0.05));
        subcategorias.add(new SubcategoriaPresupuesto("Transporte", 10, sueldo * 0.10));
        subcategorias.add(new SubcategoriaPresupuesto("Alimentación", 20, sueldo * 0.20));

        // Deseos (30%)
        subcategorias.add(new SubcategoriaPresupuesto("Moda", 12, sueldo * 0.12));
        subcategorias.add(new SubcategoriaPresupuesto("Entretenimiento", 18, sueldo * 0.18));

        // Ahorro (20%)
        subcategorias.add(new SubcategoriaPresupuesto("Fondo de emergencia", 10, sueldo * 0.10));
        subcategorias.add(new SubcategoriaPresupuesto("Ahorro para metas", 10, sueldo * 0.10));

        return subcategorias;
    }
}
