package com.Ejercicios.ApiREST.ejercicioLogica.service;

import com.Ejercicios.ApiREST.ejercicioLogica.dto.LogicaRequest;
import com.Ejercicios.ApiREST.ejercicioLogica.dto.LogicaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LogicaService {

    public LogicaResponse palindromo(LogicaRequest request) {
        String palabras = request.palindromo();

        int resultado;

        if (isPalindromo(palabras)) {
            resultado = 1;
        }else {
            resultado = 0;
        }

        return new LogicaResponse
                (lengthCadena(palabras), resultado, lengthCaracteresEspeciales(palabras));
    }

    public int lengthCadena(String cadena) {
        if (cadena == null) return 0;
        return cadena.length();
    }

    public boolean isPalindromo(String palabras) {
        if (palabras == null) return false;

        String cadenaBuena = palabras.toLowerCase().replace(" ", "");
        String reverso  = "";

        for (int i = cadenaBuena.length()-1; i >= 0 ; --i) {
            reverso = reverso + cadenaBuena.charAt(i);
        }

        if (cadenaBuena.equals(reverso)) return true;
        else return false;
    }

    public int lengthCaracteresEspeciales(String cadena) {
        if (cadena == null) return 0;

        int contador = 0;

        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);

            if (!((caracter >= 'a' && caracter <= 'z') || (caracter >= 'A' && caracter <= 'Z')
                    || (caracter >= '0' && caracter <= '9') || caracter == ' ')) {
                contador++;
            }
        }
        return contador;
    }

}
