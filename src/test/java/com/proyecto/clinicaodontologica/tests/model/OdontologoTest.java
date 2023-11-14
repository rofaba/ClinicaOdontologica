package com.proyecto.clinicaodontologica.tests.model;

import com.proyecto.clinicaodontologica.model.Odontologo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OdontologoTest {
    public OdontologoTest() {
    }

    @Test
    void odontologoConstructor_ConParametros_DeberiaCrearOdontologoCorrectamente() {
        Odontologo odontologo = new Odontologo(1, "12345", "John", "Doe");
        assertEquals(1, odontologo.getId());
        assertEquals("12345", odontologo.getMatricula());
        assertEquals("John", odontologo.getNombre());
        assertEquals("Doe", odontologo.getApellido());
    }

    @Test
    void odontologoConstructor_SinParametros_DeberiaCrearOdontologoCorrectamente() {
        Odontologo odontologo = new Odontologo();
        assertNotNull(odontologo);
    }
}

