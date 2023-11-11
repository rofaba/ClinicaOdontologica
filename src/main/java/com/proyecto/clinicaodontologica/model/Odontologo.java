package com.proyecto.clinicaodontologica.model;


import java.util.Objects;

public class Odontologo {
    private Integer id;
    private String matricula;
    private String nombre;
    private String apellido;

    public Odontologo() {
    }

    public Odontologo(Integer id, String matricula, String nombre, String apellido) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Odontologo(String matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Odontologo{" +
                "id=" + id +
                ", matricula='" + matricula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Odontologo odontologo = (Odontologo) obj;
        return id == odontologo.id &&
                matricula.equals(odontologo.matricula) &&
                nombre.equals(odontologo.nombre) &&
                apellido.equals(odontologo.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matricula, nombre, apellido);
    }


}

