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
public class Empresa {
    private String rut;
    private String nombre;
    private String direccion;
    private String razon_social;

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
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

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public Empresa() {
    }
    
    public ResultSet listadoEmpresas(Conexion conn) throws SQLException{
        Statement stmt = conn.getConexion_base().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM EMPRESA");
        return rs;
    }
    
    public Empresa buscar(String rut,Conexion conn){
        Empresa emp = new Empresa();
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call BUSCAR_EMPRESA (?,?,?,?)}");
            
            cst.setString(1, rut);
            
            cst.registerOutParameter(2, java.sql.Types.VARCHAR);
            cst.registerOutParameter(3, java.sql.Types.VARCHAR);
            cst.registerOutParameter(4, java.sql.Types.VARCHAR);
            
            cst.execute();
            
            emp.setRut(rut);
            emp.setNombre(cst.getString(2));
            emp.setDireccion(cst.getString(3));
            emp.setRazon_social(cst.getString(4));
            

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            emp = null;
        }
        return emp;
    }
    
    
    public Empresa buscarRef(String id,Conexion conn){
        Empresa emp = new Empresa();
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call BUSCAR_EMPRESA_REF (?,?,?,?,?)}");
            
            cst.setString(1, id);
            
            cst.registerOutParameter(2, java.sql.Types.VARCHAR);
            cst.registerOutParameter(3, java.sql.Types.VARCHAR);
            cst.registerOutParameter(4, java.sql.Types.VARCHAR);
            cst.registerOutParameter(5, java.sql.Types.VARCHAR);
            
            cst.execute();
            
            emp.setRut(cst.getString(2));
            emp.setNombre(cst.getString(3));
            emp.setDireccion(cst.getString(4));
            emp.setRazon_social(cst.getString(5));
            

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            emp = null;
        }
        return emp;
    }
    
    public int agregar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call AGREGAR_EMPRESA (?,?,?,?,?)}");
            
            cst.setString(1, this.rut);
            cst.setString(2, this.nombre);
            cst.setString(3, this.direccion);
            cst.setString(4, this.razon_social);
            
            cst.registerOutParameter(5, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            respuesta = cst.getInt(5);
            

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    public int modificar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call MODIFICAR_EMPRESA (?,?,?,?,?)}");
            
            cst.setString(1, this.rut);
            cst.setString(2, this.nombre);
            cst.setString(3, this.direccion);
            cst.setString(4, this.razon_social);
            
            cst.registerOutParameter(5, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            respuesta = cst.getInt(5);
            

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    public int eliminar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call ELIMINAR_EMPRESA (?,?)}");
            
            cst.setString(1, this.rut);
            
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
        return "Empresa " + this.nombre + " (" + this.rut + ")";
    }
    
    
   
}
