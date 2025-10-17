package com.Ejercicios.ApiREST.ejercicioMicroservicio.service;

import com.Ejercicios.ApiREST.ejercicioMicroservicio.dto.MicroservicioResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MicroservicioServiceTest {

    @Test
    void obtenerDatos() {
        MicroservicioService service = new MicroservicioService();
        MicroservicioResponse response = service.obtenerDatos("Adrian");

        assertNotNull(response, "La respuesta no debe ser nula");
        assertNotNull(response.fecha(), "Debe contener una fecha");
        assertNotNull(response.nombre(), "Debe contener un nombre");
        System.out.println("Respuesta real: " + response.fecha() + " - " + response.nombre());
    }
}