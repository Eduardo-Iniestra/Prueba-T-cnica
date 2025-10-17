package com.Ejercicios.ApiREST.ejercicioMicroservicio.service;

import com.Ejercicios.ApiREST.ejercicioMicroservicio.dto.MicroservicioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class MicroservicioService {

    public MicroservicioResponse obtenerDatos(String nombre) {
        String baseUrl = "https://sisedevco.ikeasistencia.com/api-swagger/api/v1/controller/test";
        String endpoint = baseUrl + "?name=" + nombre;

        try {
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int codigoRespuesta = connection.getResponseCode();

            if (codigoRespuesta == 200 || codigoRespuesta == 302) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream())
                );
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();

                String output = sb.toString();

                String[] partes = output.split("\"");
                String fecha = "";
                String nombreExtraido = "";

                for (int i = 0; i < partes.length; i++) {
                    if (partes[i].contains("fecha")) {
                        fecha = partes[i + 2];
                    } else if (partes[i].contains("nombre")) {
                        nombreExtraido = partes[i + 2];
                    }
                }

                return new MicroservicioResponse(fecha, nombreExtraido);

            } else if (codigoRespuesta == 404) {
                System.out.println("Sin resultados.");
                return null;
            } else {
                System.out.println("Código inesperado: " + codigoRespuesta);
                return null;
            }

        } catch (IOException e) {
            System.out.println("Error al consumir el servicio: " + e.getMessage());
            return null;
        }
    }

}
