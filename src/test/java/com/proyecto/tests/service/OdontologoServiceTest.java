package com.proyecto.tests.service;

import com.proyecto.clinicaodontologica.service.OdontologoServiceH2;
import com.proyecto.clinicaodontologica.model.Odontologo;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class OdontologoServiceTest {
    public OdontologoServiceTest() {
    }

    @Test
    void odontologoService_BuscarTodosOdontologos_DeberiaRetornarListaNoVacia() {
        OdontologoServiceH2 odontologoService = new OdontologoServiceH2();
        List<Odontologo> odontologos = odontologoService.buscarTodosOdontologos();
        assertNotNull(odontologos);
        assertFalse(odontologos.isEmpty());
    }

}
