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
public class Usuario {
    private String username;
    private String password;
    private String perfil;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Usuario() {
    }
    
    public Usuario logear(String user, String pass, Conexion conn){
        Usuario user_conectado = new Usuario();
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call Login (?,?,?)}");
            
            cst.setString(1, user);
            cst.setString(2, pass);
            
            cst.registerOutParameter(3, java.sql.Types.VARCHAR);
            
            cst.execute();
            
            
            user_conectado.setPerfil(cst.getString(3));
            user_conectado.setUsername(user);
            user_conectado.setPassword(pass);
            

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            user_conectado = null;
        } 
        return user_conectado;
    }
    
    public ResultSet listadoUsuarios(Conexion conn) throws SQLException{
        Statement stmt = conn.getConexion_base().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO WHERE NOT PERFIL = 'CONSUMIDOR' ");
        return rs;
    }
    
    public Usuario buscar(String user,Conexion conn){
        Usuario usu = new Usuario();
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call BUSCAR_USUARIO (?,?,?)}");
            
            cst.setString(1, user);
            
            cst.registerOutParameter(2, java.sql.Types.VARCHAR);
            cst.registerOutParameter(3, java.sql.Types.VARCHAR);
            
            cst.execute();
            
            usu.setUsername(user);
            usu.setPassword(cst.getString(2));
            usu.setPerfil(cst.getString(3));
            

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            usu = null;
        }
        return usu;
    }
    
    public int agregar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call AGREGAR_USUARIO (?,?,?,?)}");
            
            cst.setString(1, this.username);
            cst.setString(2, this.password);
            cst.setString(3, this.perfil);
            
            cst.registerOutParameter(4, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            respuesta = cst.getInt(4);
            

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    public int modificar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call MODIFICAR_USUARIO (?,?,?,?)}");
            
            cst.setString(1, this.username);
            cst.setString(2, this.password);
            cst.setString(3, this.perfil);
            
            cst.registerOutParameter(4, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            respuesta = cst.getInt(4);
            

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    public int eliminar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call ELIMINAR_USUARIO (?,?)}");
            
            cst.setString(1, this.username);
            
            cst.registerOutParameter(2, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            respuesta = cst.getInt(2);
            

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
}
