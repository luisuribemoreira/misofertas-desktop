/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisPaquetes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Nicolás
 */
public class Conexion {
    
    private Connection conexion_base;

    public Connection getConexion_base() {
        return conexion_base;
    }

    public void setConexion_base(Connection conexion_base) {
        this.conexion_base = conexion_base;
    }
    
    public Conexion Conectar() throws ClassNotFoundException, SQLException{
        Class.forName("oracle.jdbc.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        conexion_base = DriverManager.getConnection(url, "MISOFERTAS", "MOS");
        return this;
    }
    
}
