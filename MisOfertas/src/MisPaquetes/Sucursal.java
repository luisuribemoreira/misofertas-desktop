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
    /**
     * Atributos de la clase sucursal
     */
    private int id_sucur;
    private String nombre;
    private String direccion;
    private String fono;
    private String comuna;
    private String empresaRut;

    /**
     * Accesadores y mutadores
     */
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

    /**
     * Constructor por defecto
     */
    public Sucursal() {
    }
    
    /**
     * Método que devuelve el listado de las sucursales registradas en la base de datos
     * @param conn Conexión a la base de datos
     * @return Resultset Listado de las sucursales registradas
     * @throws SQLException 
     */
    public ResultSet listadoSucursales(Conexion conn) throws SQLException{
        Statement stmt = conn.getConexion_base().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM SUCURSAL");
        return rs;
    }
    
    /**
     * Método que devuelve el listado de las sucursales registradas en la base de datos asociados al Rut de la empresa
     * @param conn Conexión a la base de datos
     * @return Resultset Listado de las sucursales registradas asociadas al rut de la empresa
     * @throws SQLException 
     */
    public ResultSet listadoSucursalesFiltro(Conexion conn, String rut) throws SQLException{
        Statement stmt = conn.getConexion_base().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM SUCURSAL WHERE EMPRESA_RUT = '"+ rut +"'");
        return rs;
    }
    public ResultSet reporteSucursal(Conexion conn, int id_sucursal) throws SQLException{
        Statement stmt = conn.getConexion_base().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT S.NOMBRE AS nombre,"
                + " COUNT(U.USERNAME) AS nro_usuarios,"
                + " COUNT(ME.ID_MSJ) as mensajes, "
                + "NVL(O.VALORACION_TOTAL,0) as valoracion,"
                + " NVL(SUM(P.VALOR/O.PORC_DESC),0) as desc_rubro\n" +
                "FROM SUCURSAL S\n" +
                "FULL JOIN MENSAJERIA ME\n" +
                "ON  S.ID_SUCUR = ME.SUCURSAL_ID_SUCUR\n" +
                "FULL JOIN CONSUMIDOR C\n" +
                "ON C.PERSONA_RUN = ME.CONSUMIDOR_RUN\n" +
                "FULL JOIN USUARIO U\n" +
                "ON C.USUARIO_USERNAME = U.USERNAME\n" +
                "FULL JOIN PERSONA P \n" +
                "ON P.RUN = C.PERSONA_RUN\n" +
                "FULL JOIN OFERTA O\n" +
                "ON O.SUCURSAL_ID_SUCUR = S.ID_SUCUR\n" +
                "FULL JOIN  PRODUCTO P\n" +
                "ON P.ID_PROD = O.PRODUCTO_ID_PROD\n" +
                "WHERE S.ID_SUCUR = '"+ id_sucursal +"'\n" +
                "GROUP BY S.NOMBRE, O.VALORACION_TOTAL, O.PORC_DESC");
                return rs;
                
    }
    
    /**
     * Método el cual utiliza el procedimiento almacenado "Buscar Sucursal" el cual busca la sucursal
     * registrada en la base de datos asociado al Id especificado para luego obtener su información
     * @param id Id asociado a la sucursal a buscar
     * @param conn Conexión a la base de datos
     * @return Persona asociada al run entregado
     */
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
    
    /**
     * Método que realiza un llamado al procedimiento almacenado "Agregar Sucursal" para así agregar
     * la información de la sucursal a registrar
     * @param conn Conexión a la base de datos
     * @return número que identifica si se completó la operación 0=No | 1=Si 
     */
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
    
    /**
     * Método que realiza un llamado al procedimiento almacenado "Modificar Sucursal" para así modificar
     * la información de la sucursal registrada
     * @param conn Conexión a la base de datos
     * @return número que identifica si se completó la operación 0=No | 1=Si 
     */
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
    
    
    /**
     * Método que realiza un llamado al procedimiento almacenado "Eliminar Sucursal" para así eliminar
     * la información de la sucursal registrada
     * @param conn Conexión a la base de datos
     * @return número que identifica si se completó la operación 0=No | 1=Si 
     */
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
    

    /**
     * Método que devuelve el String asociado al nombre de la sucursal para posterior visualización
     * @return String nombre de la sucursal
     */
    @Override
    public String toString() {
        return "" + nombre ;
    }
    
    
}
