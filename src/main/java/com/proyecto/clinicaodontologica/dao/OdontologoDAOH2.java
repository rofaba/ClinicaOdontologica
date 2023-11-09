package com.proyecto.clinicaodontologica.dao;
import com.proyecto.clinicaodontologica.model.Odontologo;
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
    private static final String SQL_DELETE="DELETE * FROM ODONTOLOGOS WHERE ID=?";
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("iniciando las operaciones de : guardado odontologo:"+odontologo.getNombre()+"Apellido: "+odontologo.getApellido()+" Matricula :  "+odontologo.getMatricula());
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
        logger.info("iniciando las operaciones de : buscado por ID "+id);
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
        logger.info("Actualizando odontologo ");
        Connection connection= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psUpdate= connection.prepareStatement(SQL_UPDATE);
            psUpdate.setString(1, odontologo.getMatricula());
            psUpdate.setString(2, odontologo.getNombre());
            psUpdate.setString(3,odontologo.getApellido());
            psUpdate.execute();

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
    public List<Odontologo> buscarTodos() {
        logger.info("iniciando las operaciones de : ");
        Connection connection= null;
        try{

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

    @Override
    public Odontologo buscarPorString(String valor) {
        logger.info("iniciando las operaciones de : ");
        Connection connection= null;
        try{

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
