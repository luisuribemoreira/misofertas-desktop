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
    
}
