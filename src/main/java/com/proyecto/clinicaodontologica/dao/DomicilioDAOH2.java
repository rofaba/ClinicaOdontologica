package com.proyecto.clinicaodontologica.dao;

import com.proyecto.clinicaodontologica.model.Domicilio;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class DomicilioDAOH2 implements iDao<Domicilio>{
    private static final Logger logger= Logger.getLogger(DomicilioDAOH2.class);
    private static final String SQL_INSERT="INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?,?,?,?)";

    @Override
    public  Domicilio guardar(Domicilio domicilio) {
        Connection connection= null;
        try{
            connection = BD.getConnection();
            PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, domicilio.getCalle());
            psInsert.setInt(2,domicilio.getNumero());
            psInsert.setString(3, domicilio.getLocalidad());
            psInsert.setString(4, domicilio.getProvincia());
            psInsert.execute();
            ResultSet rs= psInsert.getGeneratedKeys();
            while (rs.next()){
                domicilio.setId(rs.getInt(1));
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
        return domicilio;
    }

    @Override
    public Domicilio buscar(Integer id) {
        return null;
    }

    @Override
    public Domicilio buscarPorString(String email) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void actualizar(Domicilio domicilio) {

    }

    @Override
    public List<Domicilio> buscarTodos() {
        return null;
    }
}
