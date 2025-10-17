package com.Ejercicios.ApiREST.ejercicioLogica.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogicaServiceTest {

    private LogicaService logicaService;

    @BeforeEach
    void setUp() {
        logicaService = new LogicaService();
    }

    @Test
    void lengthCadena() {
        assertEquals(5, logicaService.lengthCadena("hola!"));
    }

    @Test
    void isPalindromo() {
        //assertEquals(true, logicaService.isPalindromo("anita lava la tina"));
        assertEquals(true, logicaService.isPalindromo("yo Hago yoga hoy"));
    }

    @Test
    void lengthCaracteresEspeciales() {
        //assertEquals(1, logicaService.lengthCaracteresEspeciales("hola!"));
        assertEquals(5, logicaService.lengthCaracteresEspeciales("#$%&!"));
    }
}