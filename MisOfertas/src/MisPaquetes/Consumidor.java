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
 * @author Luis
 */
public class Consumidor {
    /**
     * Atributos
     */
    private int puntos;
    private String run;
    private String username;

    
    /**
     * Constructor por default
     */
    public Consumidor() {
    }
    /**
     * Mutadores y accesadores
    */
    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
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
    
    
    /**
     * Método el cual utiliza el procedimiento almacenado "Buscar consumidor" el cual busca el consumidor
     * registrado en la base de datos y modifica la información de los atributos acorde a lo encontrado
     * @param run  asociado al consumidor a buscar
     * @param conn Conexión a la base de datos
     */
    public void buscarConsumidorPorRun(String run, Conexion conn){
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call BUSCAR_CONSUMIDOR_RUN (?,?,?)}");
            
            cst.setString(1, run);
            
            cst.registerOutParameter(2, java.sql.Types.NUMERIC);
            cst.registerOutParameter(3, java.sql.Types.VARCHAR);
            
            cst.execute();
            
            this.setRun(run);
            this.setPuntos(cst.getInt(2));
            this.setUsername(cst.getString(3));
            

        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
