package com.proyecto.clinicaodontologica.controller;
import com.proyecto.clinicaodontologica.model.Paciente;
import com.proyecto.clinicaodontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/paciente")
public class PacienteController {
    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {

        this.pacienteService = pacienteService;
    }
    @GetMapping
    public String buscarPacientePorCorreo(Model model, @RequestParam("email") String correo){
        Paciente paciente= pacienteService.buscarPorEmail(correo);
        model.addAttribute("nombre",paciente.getNombre());
        model.addAttribute("apellido",paciente.getApellido());
        return "index";
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

}
