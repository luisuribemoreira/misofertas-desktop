/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisPaquetes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicolás
 */
public class Persona {
    /**
     * Atributos de la clase Persona
     */
    private String run;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String sexo;
    private String email;
    private Date fec_nac;

    /**
     * Accesadores y mutadores
     */
    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFec_nac() {
        return fec_nac;
    }

    public void setFec_nac(Date fec_nac) {
        this.fec_nac = fec_nac;
    }

    /**
     * Constructor por defecto
     */
    public Persona() {
    }
    
    /**
     * Método el cual utiliza el procedimiento almacenado "Buscar Persona" el cual busca la persona
     * registrada en la base de datos asociado al run especificado para luego ser obtener su información
     * @param run Run asociado a la persona a buscar
     * @param conn Conexión a la base de datos
     * @return Persona asociada al run entregado
     */
    public Persona buscarPersona(String run, Conexion conn){
        Persona per = new Persona();
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call BUSCAR_PERSONA (?,?,?,?,?,?,?)}");
            
            cst.setString(1, run);
            
            cst.registerOutParameter(2, java.sql.Types.VARCHAR);
            cst.registerOutParameter(3, java.sql.Types.VARCHAR);
            cst.registerOutParameter(4, java.sql.Types.VARCHAR);
            cst.registerOutParameter(5, java.sql.Types.VARCHAR);
            cst.registerOutParameter(6, java.sql.Types.VARCHAR);
            cst.registerOutParameter(7, java.sql.Types.DATE);
            
            cst.execute();
            
            per.setRun(run);
            per.setNombre(cst.getString(2));
            per.setApellidoP(cst.getString(3));
            per.setApellidoM(cst.getString(4));
            per.setSexo(cst.getString(5));
            per.setEmail(cst.getString(6));
            per.setFec_nac(cst.getDate(7));
            

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            per = null;
        }
        return per;
    }
    
    
    /**
     * Método el cual utiliza el procedimiento almacenado "Buscar Persona User" el cual busca la persona
     * registrada en la base de datos acorde a su usuario para luego obtener su información
     * @param username usuario asociado a la persona a buscar
     * @param conn Conexión a la base de datos
     * @return Persona asociada al run entregado
     */
    public Persona buscarPersonaUser(String username, Conexion conn){
        Persona per = new Persona();
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call BUSCAR_PERSONA_USER (?,?,?,?,?,?,?,?)}");
            
            cst.setString(1, username);
            
            cst.registerOutParameter(2, java.sql.Types.VARCHAR);
            cst.registerOutParameter(3, java.sql.Types.VARCHAR);
            cst.registerOutParameter(4, java.sql.Types.VARCHAR);
            cst.registerOutParameter(5, java.sql.Types.VARCHAR);
            cst.registerOutParameter(6, java.sql.Types.VARCHAR);
            cst.registerOutParameter(7, java.sql.Types.VARCHAR);
            cst.registerOutParameter(8, java.sql.Types.DATE);
            
            cst.execute();
            
            per.setRun(cst.getString(2));
            per.setNombre(cst.getString(3));
            per.setApellidoP(cst.getString(4));
            per.setApellidoM(cst.getString(5));
            per.setSexo(cst.getString(6));
            per.setEmail(cst.getString(7));
            per.setFec_nac(cst.getDate(8));
            

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            per = null;
        }
        return per;
    }
    
    /**
     * Método que realiza un llamado al procedimiento almacenado "Agregar Persona" para así agregar
     * la información de la persona a registrar
     * @param conn Conexión a la base de datos
     * @return número que identifica si se completó la operación 0=No | 1=Si 
     */
    public Integer agregar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call AGREGAR_PERSONA (?,?,?,?,?,?,?,?)}");
            
            cst.setString(1, this.getRun());
            cst.setString(2, this.getNombre());
            cst.setString(3, this.getApellidoP());
            cst.setString(4, this.getApellidoM());
            cst.setString(5, this.getSexo());
            cst.setString(6, this.getEmail());
            cst.setDate(7, new java.sql.Date(this.getFec_nac().getTime()));
            
            cst.registerOutParameter(8, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            respuesta = cst.getInt(8);
            

        } catch (SQLException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    /**
     * Método que realiza un llamado al procedimiento almacenado "Modificar Persona" para así modificar
     * la información de la persona registrada
     * @param conn Conexión a la base de datos
     * @return número que identifica si se completó la operación 0=No | 1=Si 
     */
    public int modificar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call MODIFICAR_PERSONA (?,?,?,?,?,?)}");
            
            cst.setString(1, this.getRun());
            cst.setString(2, this.getNombre());
            cst.setString(3, this.getApellidoP());
            cst.setString(4, this.getApellidoM());
            cst.setString(5, this.getEmail());
            
            cst.registerOutParameter(6, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            respuesta = cst.getInt(6);
            

        } catch (SQLException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    /**
     * Funcion que retorna todas las personas que son consumidores 
     * @param conn Conexion con la base de datos
     * @return Resultset Consumidores ingresados en la base de datos
     * @throws SQLException 
     */
    public ResultSet listadoConsumidores(Conexion conn){
        
        ResultSet rs = null;
        try {
            Statement stmt = conn.getConexion_base().createStatement();
            rs = stmt.executeQuery("SELECT * FROM CONSUMIDOR co INNER JOIN PERSONA pe ON co.PERSONA_RUN = pe.RUN");
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
            return rs;
        }
    }
    
    /**
     * Funcion que retorna todas las personas
     * @param conn Conexion con la base de datos
     * @return Resultset Personas ingresadas en la base de datos
     * @throws SQLException 
     */
    public ResultSet listadoPersonas(Conexion conn){
        
        ResultSet rs = null;
        try {
            Statement stmt = conn.getConexion_base().createStatement();
            rs = stmt.executeQuery("SELECT * FROM PERSONA");
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
            return rs;
        }
    }
    
}
