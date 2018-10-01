/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import MisPaquetes.Conexion;
import MisPaquetes.Usuario;
import java.sql.SQLException;

/**
 *
 * @author Nicolás
 */
public class MainSistema {

    static MisPaquetes.Conexion conn = new Conexion();
    static MisPaquetes.Usuario user_conectado = new Usuario();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        conn.Conectar();
        SistemaMisOfertas sistema = new SistemaMisOfertas();
        sistema.setVisible(true);
    }
    
}
