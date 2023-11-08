package com.proyecto.clinicaodontologica.controller;

import com.proyecto.clinicaodontologica.model.Odontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.proyecto.clinicaodontologica.service.OdontologoServiceH2;
    @RestController
    @RequestMapping("/odontologo")
    public class OdontologoController {
        //relacion de asociacion con el servicio
        private OdontologoServiceH2 odontologoService;

        @Autowired
        public OdontologoController(OdontologoServiceH2 odontologoService) {
            this.odontologoService = odontologoService;
        }
        @PostMapping
        public Odontologo registrarOdontologo(@RequestBody Odontologo odontologo){
            return odontologoService.guardarOdontologo(odontologo);
        }
    }

