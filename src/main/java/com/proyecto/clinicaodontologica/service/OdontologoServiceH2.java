package com.proyecto.clinicaodontologica.service;
import com.proyecto.clinicaodontologica.dao.OdontologoDAOH2;
import com.proyecto.clinicaodontologica.model.Odontologo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OdontologoServiceH2 {

    private static final Logger logger= Logger.getLogger(OdontologoServiceH2.class);
    private final OdontologoDAOH2 odontologoDAO = new OdontologoDAOH2();

    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoDAO.guardar(odontologo);
    }

    public Odontologo buscarOdontologo(Integer id) {
        return odontologoDAO.buscar(id);
    }

    public void eliminarOdontologo(Integer id) {
        odontologoDAO.eliminar(id);
    }

    public void actualizarOdontologo(Odontologo odontologo) {
        odontologoDAO.actualizar(odontologo);
    }

    public List<Odontologo> buscarTodosOdontologos() {
        return odontologoDAO.buscarTodos();
    }
}
