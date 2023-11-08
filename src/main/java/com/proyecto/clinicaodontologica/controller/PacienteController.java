package com.proyecto.clinicaodontologica.controller;
import com.proyecto.clinicaodontologica.model.Paciente;
import com.proyecto.clinicaodontologica.service.PacienteServiceH2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private PacienteServiceH2 pacienteService;

    @Autowired
    public PacienteController(PacienteServiceH2 pacienteService) {

        this.pacienteService = pacienteService;
    }

    @GetMapping("/{id}")
    public Paciente buscarPacientePorID(@PathVariable("id") Integer id){
        return pacienteService.buscarPorId(id);
    }

    @PostMapping
    public Paciente guardarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    }
    @PutMapping
    public String actualizarPaciente(@RequestBody Paciente paciente){
        Paciente p= pacienteService.buscarPorId(paciente.getId());

        if(p != null){
            pacienteService.actualizarPaciente(paciente);
            return "paciente actualizado con exito";
        }else{
            return "paciente no encontrado";
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public String eliminarPaciente(@PathVariable("id") Integer id){
        Paciente p= pacienteService.buscarPorId(id);
        if(p != null){
            pacienteService.eliminarPaciente(id);
            return "paciente eliminado con exito";
        }else{
            return "paciente no encontrado";
        }
    }
    @GetMapping("/listar")
    public String listarPacientes(Model model){
        model.addAttribute("pacientes",pacienteService.obtenerTodosLosPacientes());
        return "pacientes";
    }

    @GetMapping("/buscarcorreo")
    public String buscarPacientePorCorreo(Model model, @RequestParam("email") String correo){
        Paciente paciente= pacienteService.buscarPorEmail(correo);
        model.addAttribute("nombre",paciente.getNombre());
        model.addAttribute("apellido",paciente.getApellido());
        return "index";
    }
}
