package com.proyecto.clinicaodontologica.dao;
import com.proyecto.clinicaodontologica.model.Domicilio;
import com.proyecto.clinicaodontologica.model.Paciente;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOH2 implements iDao<Paciente>{
    private static final Logger logger= Logger.getLogger(PacienteDAOH2.class);
    private static  final String SQL_INSERT="INSERT INTO PACIENTES (NOMBRE, APELLIDO, CEDULA, FECHA_INGRESO, DOMICILIO_ID, EMAIL) VALUES(?,?,?,?,?,?)";
    private static final String SQL_SELECT_ALL="SELECT * FROM PACIENTES";
    private static final String SQL_SELECT_BY="SELECT * FROM PACIENTES WHERE ID=?";
    private static final String SQL_SELECT_BY_EMAIL="SELECT * FROM PACIENTES WHERE EMAIL=?";
    private static final String SQL_DELETE="DELETE FROM PACIENTES WHERE ID=?";
    private static final String SQL_UPDATE="UPDATE PACIENTES SET NOMBRE=?, APELLIDO=?, CEDULA=?, FECHA_INGRESO=?, DOMICILIO_ID=?, EMAIL=? WHERE ID =?";


    @Override
    public Paciente guardar(Paciente paciente) {
        logger.info("Guardando nuevo paciente");
        Connection connection= null;
        try{
            connection= BD.getConnection();
            DomicilioDAOH2 daoAux= new DomicilioDAOH2();
            Domicilio domicilio= daoAux.guardar(paciente.getDomicilio());
            PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, paciente.getNombre());
            psInsert.setString(2, paciente.getApellido());
            psInsert.setString(3, paciente.getCedula());
            psInsert.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            psInsert.setInt(5,domicilio.getId());
            psInsert.setString(6,paciente.getEmail());
            psInsert.execute();
            ResultSet clave= psInsert.getGeneratedKeys();
            while (clave.next()){
                paciente.setId(clave.getInt(1));
            }
            logger.info(" paciente guardado");


        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try{
                connection.close();
            }catch (SQLException ex){
                logger.error(ex.getMessage());
            }
        }
        return paciente;
    }


    @Override
    public Paciente buscar(Integer id) {
        logger.info("Buscando paciente con ID:" + id);
        Connection connection = null;
        Paciente paciente = null;
        Domicilio domicilio = null;
        try{
            connection= BD.getConnection();
            PreparedStatement psSelectOne= connection.prepareStatement(SQL_SELECT_BY);
            psSelectOne.setInt(1,id);
            ResultSet rs= psSelectOne.executeQuery();
            DomicilioDAOH2 daoAux= new DomicilioDAOH2();
            while (rs.next()){
                domicilio= daoAux.buscar(rs.getInt(6));
                paciente= new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),domicilio,rs.getString(7));
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
        return paciente;
    }

    @Override
     // confirmar cuando las dos operaciones esten completas (?)

    public void eliminar(Integer id) {
        logger.info("Eliminando paciente con ID" + id);
        Connection connection= null;
        Paciente pacienteaux =  null;
        Integer id_dom_aux = null;
        pacienteaux = buscar(id);
        id_dom_aux = pacienteaux.getDomicilio().getId();

        try{
            connection= BD.getConnection();
            // eliminar el paciente
            PreparedStatement psDelete= connection.prepareStatement(SQL_DELETE);
            psDelete.setInt(1,id);
            psDelete.execute();

            // eliminar el domicilio asociado al paciente
            DomicilioDAOH2 daoAux= new DomicilioDAOH2();
            daoAux.eliminar(id_dom_aux);

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
    public void actualizar(Paciente paciente) {
        logger.info("Actualizando Paciente " + paciente.getNombre());
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
    }

    @Override

    public List<Paciente> buscarTodos() {
        logger.info("Listando todos los pacientes");
        Connection connection= null;
        List<Paciente> pacientes= new ArrayList<>();
        Paciente paciente=null;
        Domicilio domicilio=null;
        try{

            connection= BD.getConnection();
            Statement statement= connection.createStatement();
            ResultSet rs= statement.executeQuery(SQL_SELECT_ALL);
            DomicilioDAOH2 daoAux= new DomicilioDAOH2();
            while(rs.next()){
                domicilio= daoAux.buscar(rs.getInt(6));
                paciente= new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),domicilio,rs.getString(7));
                pacientes.add(paciente);
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
        //Para POSTMAN
        System.out.println(pacientes);
        return pacientes;
    }
    @Override
    public Paciente buscarPorString(String valor) {
        logger.info("Buscando paciente por EMail: " + valor);
        Connection connection = null;
        Paciente paciente = null;
        Domicilio domicilio = null;
        try {
            connection = BD.getConnection();
            PreparedStatement psSelectOne = connection.prepareStatement(SQL_SELECT_BY_EMAIL);
            psSelectOne.setString(1, valor);
            ResultSet rs = psSelectOne.executeQuery();
            DomicilioDAOH2 daoAux = new DomicilioDAOH2();
            while (rs.next()) {
                domicilio = daoAux.buscar(rs.getInt(6));
                paciente = new Paciente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5).toLocalDate(),
                        domicilio,
                        rs.getString(7)
                );
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
        System.out.println(paciente);
        return paciente;
    }
}