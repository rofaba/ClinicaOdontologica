package com.proyecto.clinicaodontologica.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {
    private static final Logger logger= Logger.getLogger(BD.class);
    private static final String SQL_DROP_CREATE="DROP TABLE IF EXISTS PACIENTES; CREATE TABLE PACIENTES " +
            "(ID INT AUTO_INCREMENT PRIMARY KEY, NOMBRE VARCHAR(30) NOT NULL, APELLIDO VARCHAR(20) NOT NULL, " +
            "CEDULA VARCHAR(10) NOT NULL, FECHA_INGRESO DATE NOT NULL, DOMICILIO_ID INT NOT NULL, EMAIL VARCHAR(20) NOT NULL); " +
            "DROP TABLE IF EXISTS DOMICILIOS; CREATE TABLE DOMICILIOS (ID INT AUTO_INCREMENT PRIMARY KEY, CALLE VARCHAR(100) NOT NULL, " +
            "NUMERO INT NOT NULL, LOCALIDAD VARCHAR(100) NOT NULL, PROVINCIA VARCHAR(100) NOT NULL); " +
            "DROP TABLE IF EXISTS ODONTOLOGOS; CREATE TABLE ODONTOLOGOS (ID INT AUTO_INCREMENT PRIMARY KEY, NUMEROMATRICULA INT NOT NULL, " +
            "NOMBRE VARCHAR(30) NOT NULL, APELLIDO VARCHAR(30) NOT NULL);" ;

    private static final String SQL_PRUEBA = "INSERT INTO PACIENTES (NOMBRE, APELLIDO, CEDULA, FECHA_INGRESO, DOMICILIO_ID, EMAIL) VALUES " +
            "('Homero', 'Simpson', 'C4043243', '1956-05-12', 1, 'hombrex@gmail.com'), " +
            "('Montgomery', 'Burns', '00145', '2023-10-15', 2, 'mrburns@gmail.com'), " +
            "('Moe', 'Szyslac', '34567', '2023-10-20', 3, 'alonemoe@gmail.com'); " +
            "INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES " +
            "('Siempre viva', '742', 'Sprinfield', 'USA'), " +
            "('Rich Av.', '1000', 'Cordoba', 'Cordoba'), " +
            "('Bar de Moe', '33', 'Caba', 'Bs As')";

    public static void crearTabla(){
        Connection connection= null;
        try{
            connection= getConnection();

            Statement statement= connection.createStatement();
            statement.execute(SQL_DROP_CREATE);
            statement.execute(SQL_PRUEBA);
            logger.info("datos cargados con exito");

        }catch (Exception e){
            logger.warn(e.getMessage());
        }finally {
            try{
                connection.close();
            }catch (SQLException ex){
                logger.error(ex.getMessage());
            }
        }

    }
    public static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/OdontoDataBase","admin","admin");
    }
}
