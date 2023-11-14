package com.proyecto.clinicaodontologica.tests.dao;

import com.proyecto.clinicaodontologica.dao.OdontologoDAOH2;
import com.proyecto.clinicaodontologica.model.Odontologo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OdontologoDAOH2Test {

    @Test
    void odontologoDAO_GuardarOdontologo_DeberiaRetornarOdontologoConId() {
        OdontologoDAOH2 odontologoDAO = new OdontologoDAOH2();
        Odontologo odontologo = new Odontologo("12345", "John", "Doe");
        Odontologo resultado = odontologoDAO.guardar(odontologo);
        assertNotNull(resultado.getId());
    }

    public OdontologoDAOH2Test() {
    }

    @Test
    void odontologoDAO_BuscarOdontologoPorId_Existente_DeberiaRetornarOdontologo() {
        OdontologoDAOH2 odontologoDAO = new OdontologoDAOH2();
        Odontologo odontologo = new Odontologo("12345", "John", "Doe");
        Odontologo resultadoGuardado = odontologoDAO.guardar(odontologo);
        Odontologo resultadoBusqueda = odontologoDAO.buscar(resultadoGuardado.getId());
        assertEquals(resultadoGuardado, resultadoBusqueda);
    }

    @Test
    void odontologoDAO_BuscarOdontologoPorId_NoExistente_DeberiaRetornarNull() {
        OdontologoDAOH2 odontologoDAO = new OdontologoDAOH2();
        Odontologo resultado = odontologoDAO.buscar(-1);
        assertNull(resultado);
    }

    @Test
    void odontologoDAO_EliminarOdontologo_DeberiaEliminarOdontologo() {
        OdontologoDAOH2 odontologoDAO = new OdontologoDAOH2();
        Odontologo odontologo = new Odontologo("12345", "John", "Doe");
        Odontologo resultadoGuardado = odontologoDAO.guardar(odontologo);
        odontologoDAO.eliminar(resultadoGuardado.getId());
        Odontologo resultadoBusqueda = odontologoDAO.buscar(resultadoGuardado.getId());
        assertNull(resultadoBusqueda);
    }

    @Test
    void odontologoDAO_BuscarTodosOdontologos_DeberiaRetornarListaNoVacia() {
        OdontologoDAOH2 odontologoDAO = new OdontologoDAOH2();
        List<Odontologo> odontologos = odontologoDAO.buscarTodos();
        assertNotNull(odontologos);
        assertFalse(odontologos.isEmpty());
    }
}
