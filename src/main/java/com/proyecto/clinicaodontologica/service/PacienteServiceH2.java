package com.proyecto.clinicaodontologica.service;
import com.proyecto.clinicaodontologica.dao.PacienteDAOH2;
import com.proyecto.clinicaodontologica.dao.iDao;
import com.proyecto.clinicaodontologica.model.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceH2 {
    private iDao<Paciente> pacienteiDao;

    public PacienteServiceH2() {
        pacienteiDao= new PacienteDAOH2();
    }
    public Paciente guardarPaciente(Paciente paciente){
        return pacienteiDao.guardar(paciente);
    }
    public Paciente buscarPorId(Integer id){
        return pacienteiDao.buscar(id);
    }
    public void actualizarPaciente(Paciente paciente){
        pacienteiDao.actualizar(paciente);
    }
    public void eliminarPaciente(Integer id){
        pacienteiDao.eliminar(id);
    }
    public List<Paciente> obtenerTodosLosPacientes(){
        return pacienteiDao.buscarTodos();
    }
    public Paciente buscarPorEmail(String correo){
        return pacienteiDao.buscarPorString(correo);
    }
}