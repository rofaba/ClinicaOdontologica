package com.proyecto.clinicaodontologica.tests.entity;
import com.proyecto.clinicaodontologica.entity.Domicilio;
import com.proyecto.clinicaodontologica.entity.Paciente;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PacienteTest {
    public PacienteTest() {
    }

    @Test
    void testConstructorConArgumentos() {
        Domicilio domicilio = new Domicilio("Calle Test", 123, "Localidad Test", "Provincia Test");
        Paciente paciente = new Paciente("Nombre Test", "Apellido Test", "Cedula",
                LocalDate.now(), domicilio, "correo@test.com");


        assertEquals("Nombre Test", paciente.getNombre());
        assertEquals("Apellido Test", paciente.getApellido());
        assertEquals("Cedula", paciente.getCedula());
        assertEquals(LocalDate.now(), paciente.getFechaIngreso());
        assertEquals(domicilio, paciente.getDomicilio());
        assertEquals("correo@test.com", paciente.getEmail());
    }

}
