package com.proyecto.clinicaodontologica.dao;
import com.proyecto.clinicaodontologica.model.Odontologo;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements iDao<Odontologo>{
        private static final Logger logger= Logger.getLogger(OdontologoDAOH2.class);
        private static  final String SQL_INSERT="INSERT INTO ODONTOLOGOS (NUMEROMATRICULA, NOMBRE, APELLIDO) VALUES(?,?,?)";
        @Override
        public Odontologo guardar(Odontologo odontologo) {
            logger.info("guardando nuevo odontologo");
            Connection connection= null;
            try{
                connection= BD.getConnection();
                PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                psInsert.setInt(1, odontologo.getNumeroMatricula());
                psInsert.setString(2, odontologo.getNombre());
                psInsert.setString(3, odontologo.getApellido());
                psInsert.execute();
                ResultSet clave= psInsert.getGeneratedKeys();
                while (clave.next()){
                    odontologo.setId(clave.getInt(1));
                }
                logger.info(" odontologo guardado correctamente");


            }catch (Exception e){
                logger.error(e.getMessage());
            }finally {
                try{
                    if(connection != null) {
                        connection.close();
                    }
                }catch (SQLException ex){
                    logger.error(ex.getMessage());
                }
            }
            return odontologo;
        }

    @Override
    public Odontologo buscar(Integer id) {
        return null;
    }

    @Override
    public Odontologo buscarPorString(String email) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {
    }
    @Override
    public void actualizar(Odontologo odontologo) {
    }
    @Override
    public List<Odontologo> buscarTodos() {
        List<Odontologo> odontologos = new ArrayList<>();

        Connection connection = null;
        String SQL_BUSCAR_TODOS = "SELECT * FROM ODONTOLOGOS";

        try {
            connection = BD.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(SQL_BUSCAR_TODOS);

            while (result.next()) {
                int id = result.getInt("ID");
                Integer numeroMatricula = result.getInt("NUMEROMATRICULA");
                String nombre = result.getString("NOMBRE");
                String apellido = result.getString("APELLIDO");

                Odontologo odontologo = new Odontologo(numeroMatricula, nombre, apellido);
                odontologos.add(odontologo);
            }
            logger.info("Se han obtenido todos los odontólogos de manera exitosa");
        } catch (Exception e) {
            logger.error("Error al recuperar información de todos los odontólogos: " + e.getMessage());
        } finally {
            try {
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                logger.error("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
        return odontologos;
    }

}
