package com.proyecto.clinicaodontologica.tests.entity;
import com.proyecto.clinicaodontologica.entity.Domicilio;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DomicilioTest {
    public DomicilioTest() {
    }

    @Test    void testConstructorConArgumentos() {
        Domicilio domicilio = new Domicilio("Calle Test", 123, "Localidad Test", "Provincia Test");

        assertEquals("Calle Test", domicilio.getCalle());
        assertEquals(123, domicilio.getNumero());
        assertEquals("Localidad Test", domicilio.getLocalidad());
        assertEquals("Provincia Test", domicilio.getProvincia());
    }
}
