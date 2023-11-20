package com.proyecto.clinicaodontologica.controller;
import com.proyecto.clinicaodontologica.entity.Domicilio;
import com.proyecto.clinicaodontologica.entity.Paciente;
import com.proyecto.clinicaodontologica.service.DomicilioService;
import com.proyecto.clinicaodontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<String> registrarPaciente(@RequestBody Paciente paciente) {
        try {
            // Registrar el paciente y su domicilio
            pacienteService.registrarPaciente(paciente);

            return ResponseEntity.ok("Paciente registrado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al registrar el paciente");
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente){
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorID(paciente.getId());
        if(pacienteBuscado.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("paciente actualizado");
        }else{
            return ResponseEntity.badRequest().body("paciente no encontrado");
        }
    }
    @GetMapping("/buscar/{email}")
    public ResponseEntity<Paciente> buscarPacientePorCorreo(@PathVariable String correo){
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorCorreo(correo);
        if(pacienteBuscado.isPresent()) {

            return ResponseEntity.ok(pacienteBuscado.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
