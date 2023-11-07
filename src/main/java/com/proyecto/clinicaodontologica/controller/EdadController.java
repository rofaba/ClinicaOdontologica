package com.proyecto.clinicaodontologica.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;

@RestController
public class EdadController {

    @GetMapping("/{dia}/{mes}/{anio}")
    public String calcularEdad(@PathVariable int dia, @PathVariable int mes, @PathVariable int anio) {
        // fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Crear la fecha de nacimiento
        LocalDate fechaNacimiento = LocalDate.of(anio, mes, dia);

        // Calcular la diferencia de años
        int edad = Period.between(fechaNacimiento, fechaActual).getYears();

        return "La edad es: " + edad + " años";
    }
}
