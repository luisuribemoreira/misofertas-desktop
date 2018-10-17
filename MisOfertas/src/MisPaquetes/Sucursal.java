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
public class Sucursal {
    private int id_sucur;
    private String nombre;
    private String direccion;
    private String fono;
    private String comuna;
    private String empresaRut;

    public int getId_sucur() {
        return id_sucur;
    }

    public void setId_sucur(int id_sucur) {
        this.id_sucur = id_sucur;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFono() {
        return fono;
    }

    public void setFono(String fono) {
        this.fono = fono;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getEmpresaRut() {
        return empresaRut;
    }

    public void setEmpresaRut(String empresaRut) {
        this.empresaRut = empresaRut;
    }

    public Sucursal() {
    }
    
    public ResultSet listadoSucursales(Conexion conn) throws SQLException{
        Statement stmt = conn.getConexion_base().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM SUCURSAL");
        return rs;
    }
    
    public Sucursal buscar(int id,Conexion conn){
        Sucursal suc = new Sucursal();
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call BUSCAR_SUCURSAL (?,?,?,?,?,?)}");
            
            cst.setInt(1, id);
            
            cst.registerOutParameter(2, java.sql.Types.VARCHAR);
            cst.registerOutParameter(3, java.sql.Types.VARCHAR);
            cst.registerOutParameter(4, java.sql.Types.VARCHAR);
            cst.registerOutParameter(5, java.sql.Types.VARCHAR);
            cst.registerOutParameter(6, java.sql.Types.VARCHAR);
            
            cst.execute();
            
            suc.setId_sucur(id);
            suc.setNombre(cst.getString(2));
            suc.setDireccion(cst.getString(3));
            suc.setFono(cst.getString(4));
            suc.setComuna(cst.getString(5));
            suc.setEmpresaRut(cst.getString(6));
            

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            suc = null;
        }
        return suc;
    }
    
    public int agregar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call AGREGAR_SUCURSAL (?,?,?,?,?,?)}");
            
            cst.setString(1, this.nombre);
            cst.setString(2, this.direccion);
            cst.setString(3, this.fono);
            cst.setString(4, this.comuna);
            cst.setString(5, this.empresaRut);
            
            cst.registerOutParameter(6, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            respuesta = cst.getInt(6);
            

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    public int modificar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call MODIFICAR_SUCURSAL (?,?,?,?,?,?,?)}");
            
            cst.setInt(1, this.id_sucur);
            cst.setString(2, this.nombre);
            cst.setString(3, this.direccion);
            cst.setString(4, this.fono);
            cst.setString(5, this.comuna);
            cst.setString(6, this.empresaRut);
            
            cst.registerOutParameter(7, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            respuesta = cst.getInt(7);
            

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    public int eliminar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call ELIMINAR_SUCURSAL (?,?)}");
            
            cst.setInt(1, this.id_sucur);
            
            cst.registerOutParameter(2, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            respuesta = cst.getInt(2);
            

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    

    @Override
    public String toString() {
        return "Sucursal{" + "id_sucur=" + id_sucur + ", nombre=" + nombre + '}';
    }
    
    
}