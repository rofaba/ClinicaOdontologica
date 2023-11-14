package com.proyecto.clinicaodontologica.tests.dao;
import com.proyecto.clinicaodontologica.dao.PacienteDAOH2;
import com.proyecto.clinicaodontologica.model.Domicilio;
import com.proyecto.clinicaodontologica.model.Paciente;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PacienteDAOH2Test {
    public PacienteDAOH2Test() {
    }

    @Test
    void testGuardar() {
        PacienteDAOH2 pacienteDAO = new PacienteDAOH2();
        Paciente paciente = new Paciente("Nombre Test", "Apellido Test", "Cedula Test",
                LocalDate.now(), new Domicilio("Calle Test", 123, "Localidad Test", "Provincia Test"),
                "correo@test.com");

        Paciente pacienteGuardado = pacienteDAO.guardar(paciente);

        assertNotNull(pacienteGuardado.getId());
    }

}
