package com.proyecto.clinicaodontologica.controller;
import org.springframework.ui.Model;
import com.proyecto.clinicaodontologica.model.Odontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.proyecto.clinicaodontologica.service.OdontologoServiceH2;
    @RestController
    @RequestMapping("/odontologos")
    public class OdontologoController {
        private OdontologoServiceH2 odontologoService;

        @Autowired
        public OdontologoController(OdontologoServiceH2 odontologoService) {
            this.odontologoService = odontologoService;
        }
        @PostMapping
        public Odontologo registrarOdontologo(@RequestBody Odontologo odontologo){
            return odontologoService.guardarOdontologo(odontologo);
        }
        @GetMapping("/{id}")
        public Odontologo buscarOdontologoPorID(@PathVariable("id") Integer id){
            return odontologoService.buscarPorId(id);
        }


        @PutMapping
        public String actualizarOdontologo(@RequestBody Odontologo odontologo){
            Odontologo o = odontologoService.buscarPorId(odontologo.getId());
            if(o != null){
                odontologoService.actualizarOdontologo(odontologo); // Cambio aquí
                return "Odontologo actualizado";
            } else {
                return "Odontologo no encontrado";
            }
        }
        @DeleteMapping("/eliminar/{id}")
        public String eliminarOdontologo(@PathVariable("id") Integer id){
            Odontologo o= odontologoService.buscarPorId(id);
            if(o != null){
                odontologoService.eliminarOdontologo(id);
                return "odontologo eliminado con exito";
            }else{
                return "odontologo no encontrado";
            }
        }
        @GetMapping("/listar")
        public String listarOdontologos(Model model){
            model.addAttribute("odontologos",odontologoService.buscarTodosOdontologos());
            return "odontologos";
        }
        @GetMapping("/buscarMatricula")
        public String buscarOdontologoPorMatricula(Model model, @RequestParam("matricula") String matricula){
            Odontologo odontologo= odontologoService.buscarPorMatricula(matricula);
            model.addAttribute("nombre",odontologo.getNombre());
            model.addAttribute("apellido",odontologo.getApellido());
            return "odontologo";
        }

    }

