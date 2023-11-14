package com.proyecto.clinicaodontologica.tests.service;

import com.proyecto.clinicaodontologica.model.Domicilio;
import com.proyecto.clinicaodontologica.model.Paciente;
import com.proyecto.clinicaodontologica.service.PacienteServiceH2;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PacienteServiceH2Test {
    public PacienteServiceH2Test() {
    }

    @Test
    void testGuardarPaciente() {
        PacienteServiceH2 pacienteService = new PacienteServiceH2();
        Paciente paciente = new Paciente("Nombre Test", "Apellido Test", "Test",
                LocalDate.now(), new Domicilio("Calle Test", 123, "Localidad Test", "Provincia Test"),
                "correo@test.com");

        Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);

        assertNotNull(pacienteGuardado.getId());
    }
}
