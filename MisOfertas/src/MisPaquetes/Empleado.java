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
    private String cargo;
    private String run;
    private String username;

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

    public Empleado() {
    }
    
    public void buscarEmpleado(String run, Conexion conn){
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call BUSCAR_EMPLEADO (?,?,?)}");
            
            cst.setString(1, run);
            
            cst.registerOutParameter(2, java.sql.Types.VARCHAR);
            cst.registerOutParameter(3, java.sql.Types.VARCHAR);
            
            cst.execute();
            
            this.setRun(run);
            this.setUsername(cst.getString(2));
            this.setCargo(cst.getString(3));
            

        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int agregar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call AGREGAR_EMPLEADO (?,?,?,?)}");
            
            cst.setString(1, this.username);
            cst.setString(2, this.run);
            cst.setString(3, this.cargo);
            
            cst.registerOutParameter(4, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            respuesta = cst.getInt(4);
            

        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    public int modificar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call MODIFICAR_EMPLEADO (?,?,?,?)}");
            
            cst.setString(1, this.username);
            cst.setString(2, this.run);
            cst.setString(3, this.cargo);
            
            cst.registerOutParameter(4, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            respuesta = cst.getInt(4);
            

        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
}
