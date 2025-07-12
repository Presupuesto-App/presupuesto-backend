package com.financia.presupuesto.presupuesto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.financia.presupuesto.presupuesto.dto.PresupuestoRequestDto;
import com.financia.presupuesto.presupuesto.dto.PresupuestoResponseDto;
import com.financia.presupuesto.presupuesto.service.RecomendadorService;

@Tag(name = "Presupuesto", description = "Operaciones de recomendación de presupuesto personalizado")
@RestController
@RequestMapping("/api/presupuesto")
public class PresupuestoController {

    @Autowired
    private RecomendadorService recomendadorService;

    @Operation(
        summary = "Recomendar presupuesto mensual personalizado",
        description = "Devuelve una recomendación de presupuesto según el perfil del usuario",
        security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @PostMapping("/recomendar")
    public ResponseEntity<PresupuestoResponseDto> recomendarPresupuesto(@Valid @RequestBody PresupuestoRequestDto request) {
        PresupuestoResponseDto response = recomendadorService.recomendar(request);
        return ResponseEntity.ok(response);
    }
}
