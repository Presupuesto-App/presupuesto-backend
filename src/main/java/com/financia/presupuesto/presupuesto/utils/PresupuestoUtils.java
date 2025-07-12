package com.financia.presupuesto.presupuesto.utils;

import com.financia.presupuesto.presupuesto.model.SubcategoriaPresupuesto;
import com.financia.presupuesto.presupuesto.dto.PresupuestoRequestDto;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PresupuestoUtils {

    public static void aplicarAjustes(List<SubcategoriaPresupuesto> subcategorias, PresupuestoRequestDto request) {
        Map<String, SubcategoriaPresupuesto> subcategoriaMap = new HashMap<>();
        for (SubcategoriaPresupuesto sub : subcategorias) {
            subcategoriaMap.put(sub.getNombre(), sub);
        }

        // Regla 1: Tipo de vivienda
        aplicarReglasVivienda(subcategoriaMap, request);

        // Regla 2: Vive solo o comparte gastos
        aplicarReglasConvivencia(subcategoriaMap, request);

        // Regla 3: Tiene hijos
        if (request.isTieneHijos()) {
            ajustarPorcentaje(subcategoriaMap, "Alimentación", 5);
            ajustarPorcentaje(subcategoriaMap, "Vivienda", 3);
            ajustarPorcentaje(subcategoriaMap, "Moda", -3);
            ajustarPorcentaje(subcategoriaMap, "Entretenimiento", -5);
        }

        // Regla 4: Usa carro propio
        if (request.isUsaCarro()) {
            ajustarPorcentaje(subcategoriaMap, "Transporte", 5);
            ajustarPorcentaje(subcategoriaMap, "Moda", -3);
            ajustarPorcentaje(subcategoriaMap, "Ahorro para metas", -2);
        }

        // Regla 5: Frecuencia de comidas fuera
        aplicarReglasComidaFuera(subcategoriaMap, request);

        // Regla 6: Meta de ahorro importante
        if (request.isTieneMetaAhorro()) {
            ajustarPorcentaje(subcategoriaMap, "Ahorro para metas", 5);
            ajustarPorcentaje(subcategoriaMap, "Entretenimiento", -5);
        }

        // Regla 7: Ahorro para emergencias (nueva regla agregada)
        aplicarReglasAhorroEmergencia(subcategoriaMap, request);

        // Regla 8: Tiene deudas activas
        if (request.isTieneDeudas()) {
            // Agregar subcategoría "Pago de deuda"
            subcategorias.add(new SubcategoriaPresupuesto("Pago de deuda", 10, 0));
            ajustarPorcentaje(subcategoriaMap, "Fondo de emergencia", -5);
            ajustarPorcentaje(subcategoriaMap, "Entretenimiento", -5);
        }
    }

    private static void aplicarReglasVivienda(Map<String, SubcategoriaPresupuesto> subcategoriaMap, PresupuestoRequestDto request) {
        if ("propia sin hipoteca".equalsIgnoreCase(request.getTipoVivienda())) {
            // Vivienda baja a 0%, redistribuir: +5% Ahorro metas, +5% Fondo emergencia, +5% Entretenimiento
            subcategoriaMap.get("Vivienda").setPorcentaje(0);
            ajustarPorcentaje(subcategoriaMap, "Ahorro para metas", 5);
            ajustarPorcentaje(subcategoriaMap, "Fondo de emergencia", 5);
            ajustarPorcentaje(subcategoriaMap, "Entretenimiento", 5);
        } else if ("propia con hipoteca".equalsIgnoreCase(request.getTipoVivienda())) {
            // Mantener el 15% base para vivienda con hipoteca
            // No hacer ajustes adicionales
        }
        // Para "alquilada" se maneja en aplicarReglasConvivencia
    }

    private static void aplicarReglasConvivencia(Map<String, SubcategoriaPresupuesto> subcategoriaMap, PresupuestoRequestDto request) {
        if ("alquilada".equalsIgnoreCase(request.getTipoVivienda())) {
            if (request.isViveSolo()) {
                // Vivienda sube a 18%
                subcategoriaMap.get("Vivienda").setPorcentaje(18);
            } else {
                // Comparte gastos: Vivienda baja a 10%
                subcategoriaMap.get("Vivienda").setPorcentaje(10);
                ajustarPorcentaje(subcategoriaMap, "Ahorro para metas", 3);
            }
        } else if (request.isViveSolo() && !"propia sin hipoteca".equalsIgnoreCase(request.getTipoVivienda())) {
            ajustarPorcentaje(subcategoriaMap, "Vivienda", 3);
        } else if (!request.isViveSolo()) {
            ajustarPorcentaje(subcategoriaMap, "Vivienda", -3);
            ajustarPorcentaje(subcategoriaMap, "Ahorro para metas", 3);
        }
    }

    private static void aplicarReglasComidaFuera(Map<String, SubcategoriaPresupuesto> subcategoriaMap, PresupuestoRequestDto request) {
        if ("alta".equalsIgnoreCase(request.getComidasFueraFrecuencia())) {
            ajustarPorcentaje(subcategoriaMap, "Alimentación", 5);
            ajustarPorcentaje(subcategoriaMap, "Fondo de emergencia", -5);
        } else if ("baja".equalsIgnoreCase(request.getComidasFueraFrecuencia())) {
            ajustarPorcentaje(subcategoriaMap, "Alimentación", -5);
            ajustarPorcentaje(subcategoriaMap, "Fondo de emergencia", 5);
        }
    }

    private static void aplicarReglasAhorroEmergencia(Map<String, SubcategoriaPresupuesto> subcategoriaMap, PresupuestoRequestDto request) {
        // Regla: ¿Estás ahorrando actualmente para emergencias? No → Fondo emergencia +5%, Deseos -5%
        // Nota: Esta regla requiere un campo adicional en el DTO. Por ahora, usaremos una lógica alternativa:
        // Si no tiene meta de ahorro importante, probablemente no esté ahorrando para emergencias
        if (!request.isTieneMetaAhorro()) {
            ajustarPorcentaje(subcategoriaMap, "Fondo de emergencia", 5);
            // Reducir de "Deseos" (Moda + Entretenimiento)
            ajustarPorcentaje(subcategoriaMap, "Moda", -2.5);
            ajustarPorcentaje(subcategoriaMap, "Entretenimiento", -2.5);
        }
    }

    private static void ajustarPorcentaje(Map<String, SubcategoriaPresupuesto> subcategoriaMap, String nombre, double ajuste) {
        SubcategoriaPresupuesto subcategoria = subcategoriaMap.get(nombre);
        if (subcategoria != null) {
            double nuevoPorcentaje = subcategoria.getPorcentaje() + ajuste;
            // Evitar porcentajes negativos
            subcategoria.setPorcentaje(Math.max(0, nuevoPorcentaje));
        }
    }

    public static void normalizarPorcentajes(List<SubcategoriaPresupuesto> subcategorias, double sueldo) {
        // Calcular total actual de porcentajes
        double totalPorcentaje = subcategorias.stream()
                .mapToDouble(SubcategoriaPresupuesto::getPorcentaje)
                .sum();

        // Si no suma 100%, normalizar proporcionalmente
        if (totalPorcentaje != 100.0) {
            double factor = 100.0 / totalPorcentaje;
            for (SubcategoriaPresupuesto sub : subcategorias) {
                double nuevoPorcentaje = sub.getPorcentaje() * factor;
                sub.setPorcentaje(nuevoPorcentaje);
                sub.setMonto(sueldo * (nuevoPorcentaje / 100.0));
            }
        } else {
            // Solo recalcular montos
            for (SubcategoriaPresupuesto sub : subcategorias) {
                sub.setMonto(sueldo * (sub.getPorcentaje() / 100.0));
            }
        }
    }
}
