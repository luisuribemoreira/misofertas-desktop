/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisPaquetes;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicolás
 */
public class Persona {
    private String run;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String sexo;
    private String email;
    private Date fec_nac;

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

    public Persona() {
    }
    
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
    
}
