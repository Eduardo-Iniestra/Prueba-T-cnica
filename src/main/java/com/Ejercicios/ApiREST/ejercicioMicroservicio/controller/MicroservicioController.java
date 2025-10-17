package com.Ejercicios.ApiREST.ejercicioMicroservicio.controller;

import com.Ejercicios.ApiREST.ejercicioMicroservicio.dto.MicroservicioResponse;
import com.Ejercicios.ApiREST.ejercicioMicroservicio.service.MicroservicioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ejercicio2")
public class MicroservicioController {

    private final MicroservicioService microservicioService;

    @Operation(
            summary = "Introduzca un nombre para encontrar coincidencias",
            description = "Realiza una búsqueda en el microservicio utilizando el parametro name"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found - sin resultados")
    })
    @GetMapping("/nombre")
    public ResponseEntity<?> obtenerDatos(
            @Parameter(description = "Nombre de la persona para buscar coincidencias", example = "Eduardo")
            @RequestParam String name
    ) {
        MicroservicioResponse respuesta = microservicioService.obtenerDatos(name);

        if (respuesta != null) {
            return ResponseEntity.ok(respuesta);
        } else {
            return ResponseEntity.status(404).body("Sin resultados");
        }
    }
}
