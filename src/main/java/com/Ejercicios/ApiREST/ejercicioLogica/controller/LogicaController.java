package com.Ejercicios.ApiREST.ejercicioLogica.controller;

import com.Ejercicios.ApiREST.ejercicioLogica.dto.LogicaRequest;
import com.Ejercicios.ApiREST.ejercicioLogica.dto.LogicaResponse;
import com.Ejercicios.ApiREST.ejercicioLogica.service.LogicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ejercicio1")
public class LogicaController {

    private final LogicaService service;

    @PostMapping("/palindromo")
    public ResponseEntity<LogicaResponse> palindromo(@RequestBody LogicaRequest palabras) {
        LogicaResponse logica = service.palindromo(palabras);
        return ResponseEntity.ok(logica);
    }


}
