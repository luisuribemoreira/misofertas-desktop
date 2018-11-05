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
 * Clase mensajeria
 * @author Luis
 */
public class Mensajeria {
    
    //Seccion de atributos de la clase
    private int id;
    private String asunto;
    private String mensaje;
    private byte[] cupon;
    private byte[] imgOfer;
    private int idSucur;
    private int ifOfer;
    private String nomConsumidor;
    private String runConsumidor;

    /**
     * Constructor por defecto de la clase mensajeria
     */
    public Mensajeria() {
    }

    //Seccion de mutadores y accesadores
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public byte[] getCupon() {
        return cupon;
    }

    public void setCupon(byte[] cupon) {
        this.cupon = cupon;
    }

    public byte[] getImgOfer() {
        return imgOfer;
    }

    public void setImgOfer(byte[] imgOfer) {
        this.imgOfer = imgOfer;
    }

    public int getIdSucur() {
        return idSucur;
    }

    public void setIdSucur(int idSucur) {
        this.idSucur = idSucur;
    }

    public int getIfOfer() {
        return ifOfer;
    }

    public void setIfOfer(int ifOfer) {
        this.ifOfer = ifOfer;
    }

    public String getNomConsumidor() {
        return nomConsumidor;
    }

    public void setNomConsumidor(String nomConsumidor) {
        this.nomConsumidor = nomConsumidor;
    }

    public String getRunConsumidor() {
        return runConsumidor;
    }

    public void setRunConsumidor(String runConsumidor) {
        this.runConsumidor = runConsumidor;
    }
    
    /***
     * Funcion que el registro de un mensaje enviado por correo electronico
     * @param conn Conexion con la base de datos
     * @return  retorna 1 positivo , 0 negativo
     */
    public int agregar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call AGREGAR_MENSAJERIA (?,?,?,?,?,?,?,?,?)}");
            cst.setString(1, this.asunto);
            cst.setString(2, this.mensaje);
            cst.setBytes(3, this.cupon);
            cst.setBytes(4,this.imgOfer);
            cst.setInt(5, this.idSucur);
            cst.setInt(6,this.ifOfer);
            cst.setString(7, this.nomConsumidor);
            cst.setString(8, this.runConsumidor);
            
            
            cst.registerOutParameter(9, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            respuesta = cst.getInt(9);

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
}
