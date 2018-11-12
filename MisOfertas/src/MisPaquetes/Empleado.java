/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisPaquetes;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicolás
 */
public class Empleado {
    /**
     * Atributos de la clase empleado
     */
    private String cargo;
    private String run;
    private String username;
    private int id_referencia;

    /**
     * Accesarodores y mutadores
     */
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId_referencia() {
        return id_referencia;
    }

    public void setId_referencia(int id_referencia) {
        this.id_referencia = id_referencia;
    }
    
    

    /**
     * Constructor por defecto
     */
    public Empleado() {
    }
    
    /**
     * Método el cual utiliza el procedimiento almacenado "Buscar Empleado" el cual busca el empleado
     * registrado en la base de datos y modifica la información de los atributos acorde a lo encontrado
     * @param run Run asociado al empleado a buscar
     * @param conn Conexión a la base de datos
     */
    public void buscarEmpleado(String run, Conexion conn){
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call BUSCAR_EMPLEADO (?,?,?,?)}");
            
            cst.setString(1, run);
            
            cst.registerOutParameter(2, java.sql.Types.VARCHAR);
            cst.registerOutParameter(3, java.sql.Types.VARCHAR);
            cst.registerOutParameter(4, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            this.setRun(run);
            this.setUsername(cst.getString(2));
            this.setCargo(cst.getString(3));
            this.setId_referencia(cst.getInt(4));
            

        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Método que realiza un llamado al procedimiento almacenado "Agregar Empleado" para así agregar
     * la información del empleado registrado
     * @param conn Conexión a la base de datos
     * @return número que identifica si se completó la operación 0=No | 1=Si 
     */
    public int agregar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call AGREGAR_EMPLEADO (?,?,?,?,?)}");
            
            cst.setString(1, this.username);
            cst.setString(2, this.run);
            cst.setString(3, this.cargo);
            cst.setInt(4, this.id_referencia);
            
            cst.registerOutParameter(5, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            respuesta = cst.getInt(5);
            

        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    /**
     * Método que realiza un llamado al procedimiento almacenado "Modificar Empleado" para así Modificar
     * la información del empleado registrado
     * @param conn Conexión a la base de datos
     * @return número que identifica si se completó la operación 0=No | 1=Si 
     */
    public int modificar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call MODIFICAR_EMPLEADO (?,?,?,?,?)}");
            
            cst.setString(1, this.username);
            cst.setString(2, this.run);
            cst.setString(3, this.cargo);
            cst.setInt(4, this.id_referencia);
            
            cst.registerOutParameter(5, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            respuesta = cst.getInt(5);
            

        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    /**
     * Método el cual utiliza el procedimiento almacenado "Buscar Empleado" el cual busca el empleado
     * registrado en la base de datos y modifica la información de los atributos acorde a lo encontrado
     * @param userName  asociado al empleado a buscar
     * @param conn Conexión a la base de datos
     */
    public void buscarEmpleadoPorUserName(String userName, Conexion conn){
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call BUSCAR_EMPLEADO_LOGEADO (?,?,?,?)}");
            
            cst.setString(1, userName);
            
            cst.registerOutParameter(2, java.sql.Types.VARCHAR);
            cst.registerOutParameter(3, java.sql.Types.VARCHAR);
            cst.registerOutParameter(4, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            this.setUsername(userName);
            this.setRun(cst.getString(2));
            this.setCargo(cst.getString(3));
            this.setId_referencia(cst.getInt(4));
            

        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
