package com.proyecto.clinicaodontologica;
import com.proyecto.clinicaodontologica.dao.BD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApp {

	public static void main(String[] args) {
		BD.crearTabla();

		SpringApplication.run(MainApp.class, args);
	}

}
