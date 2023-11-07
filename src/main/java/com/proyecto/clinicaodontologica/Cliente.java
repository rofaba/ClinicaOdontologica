package com.proyecto.clinicaodontologica;
import com.proyecto.clinicaodontologica.dao.BD;
import com.proyecto.clinicaodontologica.model.Odontologo;
import com.proyecto.clinicaodontologica.service.OdontologoServiceH2;

import java.util.ArrayList;

public class Cliente {
    public static <List> void main(String[] args) {
        BD.crearTabla();
        // Crear un odontólogo
        Odontologo odontologo1 = new Odontologo(11111, "Mario", "Martinez");
        Odontologo odontologo2 = new Odontologo(22222, "Blanca", "Rojas");

        // Crear services
        OdontologoServiceH2 serviceH2 = new OdontologoServiceH2();


        // Guardar odontólogos
        Odontologo odontologoGuardadoH2 = serviceH2.guardarOdontologo(odontologo1);

        //Listar todos los odontólogos
        ArrayList<Odontologo> odontologosH2 = (ArrayList<Odontologo>) serviceH2.buscarTodosOdontologos();

        // Imprimir los odontólogos
        System.out.println("Odontólogos (H2):");
        for (Odontologo o : odontologosH2) {
            System.out.println(o.getNumeroMatricula() + " - " + o.getNombre() + " " + o.getApellido());
        }
    }
}
