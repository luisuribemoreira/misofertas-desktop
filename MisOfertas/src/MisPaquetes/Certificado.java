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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicolás
 */
public class Certificado {
    /**
     * Atributos de la clase Certificado
     */
    private int id_cert;
    private int pts_min;
    private int pts_max;
    private double descuento;
    private int tope;
    private String rubro;

    /**
     * Accesadores y mutadores 
     */
    public int getId_cert() {
        return id_cert;
    }

    public void setId_cert(int id_cert) {
        this.id_cert = id_cert;
    }

    public int getPts_min() {
        return pts_min;
    }

    public void setPts_min(int pts_min) {
        this.pts_min = pts_min;
    }

    public int getPts_max() {
        return pts_max;
    }

    public void setPts_max(int pts_max) {
        this.pts_max = pts_max;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public int getTope() {
        return tope;
    }

    public void setTope(int tope) {
        this.tope = tope;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    /**
     * Constructor por defecto
     */
    public Certificado() {
    }
    
    /**
     * Método que retorna el listado de los certificados generados en la base de datos, para posterior
     * visualización en la tabla del panel de certificado
     * @param conn  Conexión a la base de datos
     * @return Resultset del listado de certificados
     * @throws SQLException 
     */
    public ResultSet listadoCertificados(Conexion conn) throws SQLException{
        Statement stmt = conn.getConexion_base().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM CERTIFICADO");
        return rs;
    }
    
    /**
     * Método que retorna el certificado asociado al número identificador (ID) a buscar
     * @param id    Identificador del certificado a buscar
     * @param conn  Conexión a la base de datos
     * @return Certificado encontrado
     */
    public Certificado buscar(int id,Conexion conn){
        Certificado cer = new Certificado();
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call BUSCAR_CERTIFICADO (?,?,?,?,?,?)}");
            
            cst.setInt(1, id);
            
            cst.registerOutParameter(2, java.sql.Types.NUMERIC);
            cst.registerOutParameter(3, java.sql.Types.NUMERIC);
            cst.registerOutParameter(4, java.sql.Types.NUMERIC);
            cst.registerOutParameter(5, java.sql.Types.NUMERIC);
            cst.registerOutParameter(6, java.sql.Types.VARCHAR);
            
            cst.execute();
            
            cer.setId_cert(id);
            cer.setPts_min(cst.getInt(2));
            cer.setPts_max(cst.getInt(3));
            cer.setDescuento(cst.getDouble(4));
            cer.setTope(cst.getInt(5));
            cer.setRubro(cst.getString(6));
            

        } catch (SQLException ex) {
            Logger.getLogger(Certificado.class.getName()).log(Level.SEVERE, null, ex);
            cer = null;
        }
        return cer;
    }
    
    
    /**
     * Método que realiza un llamado al procedimiento almacenado "Agregar Certificado" para así agregar
     * la información del certificado creado
     * @param conn  Conexión a la base de datos
     * @return número que identifica si se completó la operación 0=No | 1=Si 
     */
    public int agregar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call AGREGAR_CERTIFICADO (?,?,?,?,?,?)}");
            
            cst.setInt(1, this.pts_min);
            cst.setInt(2, this.pts_max);
            cst.setDouble(3, this.descuento);
            cst.setInt(4, this.tope);
            cst.setString(5, this.rubro);
            
            cst.registerOutParameter(6, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            respuesta = cst.getInt(6);
            

        } catch (SQLException ex) {
            Logger.getLogger(Certificado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    /**
     * Método que realiza un llamado al procedimiento almacenado "Modificar Certificado" para así modificar
     * la información del certificado registrado
     * @param conn Conexión a la base de datos
     * @return número que identifica si se completó la operación 0=No | 1=Si 
     */
    public int modificar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call MODIFICAR_CERTIFICADO (?,?,?,?,?,?,?)}");
            
            cst.setInt(1, this.id_cert);
            cst.setInt(2, this.pts_min);
            cst.setInt(3, this.pts_max);
            cst.setDouble(4, this.descuento);
            cst.setInt(5, this.tope);
            cst.setString(6, this.rubro);
            
            cst.registerOutParameter(7, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            respuesta = cst.getInt(7);
            

        } catch (SQLException ex) {
            Logger.getLogger(Certificado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    /**
     * Método que realiza un llamado al procedimiento almacenado "Eliminar Certificado" para así eliminar
     * la información del certificado registrado
     * @param conn Conexión a la base de datos
     * @return número que identifica si se completó la operación 0=No | 1=Si 
     */
    public int eliminar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call ELIMINAR_CERTIFICADO (?,?)}");
            
            cst.setInt(1, this.id_cert);
            
            cst.registerOutParameter(2, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            respuesta = cst.getInt(2);
            

        } catch (SQLException ex) {
            Logger.getLogger(Certificado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
}
