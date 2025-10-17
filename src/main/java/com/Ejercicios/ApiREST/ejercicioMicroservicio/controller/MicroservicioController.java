package com.Ejercicios.ApiREST.ejercicioMicroservicio.controller;

import com.Ejercicios.ApiREST.ejercicioMicroservicio.dto.MicroservicioResponse;
import com.Ejercicios.ApiREST.ejercicioMicroservicio.service.MicroservicioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ejercicio2")
public class MicroservicioController {

    private final MicroservicioService microservicioService;


    @Operation(summary = "Consume un API externo y devuelve nombre y fecha si hay coincidencia")
    @GetMapping("/consumir-api")
    public ResponseEntity<?> consumirApi(
            @Parameter(description = "Nombre a buscar en la API") @RequestParam String nombre) {

        String miNombre = nombre;
        MicroservicioResponse respuesta = microservicioService.obtenerDatos(miNombre);

        if (respuesta == null) {

            return ResponseEntity
                    .status(404)
                    .body(Map.of("mensaje", "Sin resultados"));
        }

        return ResponseEntity
                .status(302)
                .body(respuesta);
    }
}
