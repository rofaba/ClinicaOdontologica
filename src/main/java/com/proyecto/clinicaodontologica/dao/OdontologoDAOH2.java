package com.proyecto.clinicaodontologica.dao;
import com.proyecto.clinicaodontologica.model.Domicilio;
import com.proyecto.clinicaodontologica.model.Odontologo;
import com.proyecto.clinicaodontologica.model.Paciente;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements iDao<Odontologo>{
    private static final Logger logger= Logger.getLogger(OdontologoDAOH2.class);
    private static  final String SQL_INSERT="INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES(?,?,?)";
    private static final String SQL_SELECT_ALL="SELECT * FROM ODONTOLOGOS";
    private static final String SQL_SELECT_BY="SELECT * FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_SELECT_BY_MATRICULA="SELECT * FROM ODONTOLOGOS WHERE MATRICULA=?";
    private static final String SQL_UPDATE="UPDATE ODONTOLOGOS SET MATRICULA=?, NOMBRE=?, APELLIDO=? WHERE ID =?";
    private static final String SQL_DELETE="DELETE FROM ODONTOLOGOS WHERE ID=?";
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("Guardando odontólogo: "+ odontologo.getNombre() + " Apellido: "+odontologo.getApellido() + " Matricula :  "+ odontologo.getMatricula());
        Connection connection= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, odontologo.getMatricula());
            psInsert.setString(2, odontologo.getNombre());
            psInsert.setString(3,odontologo.getApellido());
            psInsert.execute();
            ResultSet rs= psInsert.getGeneratedKeys();
            while (rs.next()){
                odontologo.setId(rs.getInt(1));
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try{
                connection.close();
            }catch (SQLException ex){
                logger.error(ex.getMessage());
            }
        }
        return odontologo;
    }

    @Override
    public Odontologo buscar(Integer id) {
        logger.info("Buscando Odontólogo por ID: " + id);
        Connection connection= null;
        Odontologo odontologo= null;
        try{
            connection=BD.getConnection();
            PreparedStatement psSelect= connection.prepareStatement(SQL_SELECT_BY);
            psSelect.setInt(1,id);
            ResultSet rs= psSelect.executeQuery();
            while (rs.next()){
                //construir el odontologo que habiamos creado con anterioridad
                odontologo= new Odontologo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));

            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try{
                connection.close();
            }catch (SQLException ex){
                logger.error(ex.getMessage());
            }
        }
        return odontologo;
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("Eliminando Odontologo con ID:  " + id);
        Connection connection= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psDelete= connection.prepareStatement(SQL_DELETE);
            psDelete.setInt(1,id);
            psDelete.execute();

        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try{
                connection.close();
            }catch (SQLException ex){
                logger.error(ex.getMessage());
            }
        }

    }
    @Override
    public void actualizar(Odontologo odontologo) {
        logger.info("Actualizando odontologo");
        Connection connection = null;
        try {
            connection = BD.getConnection();
            PreparedStatement psUpdate = connection.prepareStatement(SQL_UPDATE);
            psUpdate.setString(1, odontologo.getMatricula());
            psUpdate.setString(2, odontologo.getNombre());
            psUpdate.setString(3, odontologo.getApellido());
            // ID como cuarto parámetro
            psUpdate.setInt(4, odontologo.getId());
            psUpdate.executeUpdate();

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
    }

    @Override
    public List<Odontologo> buscarTodos() {
        logger.info("Listando todos los odontolólogos");
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();
        Odontologo odontologo = null;
        try {
            connection = BD.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL);
            while (rs.next()) {
                odontologo = new Odontologo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                odontologos.add(odontologo); // Agrega el odontólogo a la lista
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
        // Para POSTMAN
        System.out.println(odontologos);
        return odontologos;
    }
    @Override
    public Odontologo buscarPorString(String palabraclave) {
        logger.info("Buscando odontólogo por Matrícula de : " + palabraclave);
        Connection connection= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psSelectOne= connection.prepareStatement(SQL_SELECT_BY_MATRICULA);
            psSelectOne.setString(1,palabraclave);
            ResultSet rs= psSelectOne.executeQuery();
            while (rs.next()){

                Odontologo odontologo= new Odontologo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
                System.out.println(odontologo);
                return odontologo;
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try{
                connection.close();
            }catch (SQLException ex){
                logger.error(ex.getMessage());
            }
        }
        return null;
    }
}
