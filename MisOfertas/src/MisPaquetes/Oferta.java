
package MisPaquetes;

import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Date;
import static java.sql.JDBCType.BLOB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Types.BLOB;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Luis
 */
public class Oferta {
    
    /**
     * Declaracion de atributos
     */
    int id;
    String descripcion;
    Date fecha_inicio;
    Date fecha_termino;
    byte[] imagen;
    int valoracion_total;
    int porc_descuento;
    int id_sucursal;
    int id_producto;
    
    /**
     * Mutadores y accesadores
     * @return 
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_termino() {
        return fecha_termino;
    }

    public void setFecha_termino(Date fecha_termino) {
        this.fecha_termino = fecha_termino;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public int getValoracion_total() {
        return valoracion_total;
    }

    public void setValoracion_total(int valoracion_total) {
        this.valoracion_total = valoracion_total;
    }

    public int getPorc_descuento() {
        return porc_descuento;
    }

    public void setPorc_descuento(int porc_descuento) {
        this.porc_descuento = porc_descuento;
    }

    public int getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
    
    /**
     * 
     */
    public ResultSet listadoOfertas(Conexion conn) throws SQLException{
        Statement stmt = conn.getConexion_base().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM OFERTA");
        return rs;
    }
    
    public Oferta buscar(int id,Conexion conn){
        Oferta ofer = new Oferta();
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call BUSCAR_OFERTA (?,?,?,?,?,?,?,?,?)}");
            
            cst.setInt(1, id);
            
            cst.registerOutParameter(2, java.sql.Types.VARCHAR);
            cst.registerOutParameter(3, java.sql.Types.DATE);
            cst.registerOutParameter(4, java.sql.Types.DATE);
            cst.registerOutParameter(5, java.sql.Types.BLOB);
            cst.registerOutParameter(6, java.sql.Types.NUMERIC);
            cst.registerOutParameter(7, java.sql.Types.NUMERIC);
            cst.registerOutParameter(8, java.sql.Types.NUMERIC);
            cst.registerOutParameter(9, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            ofer.setId(id);
            ofer.setDescripcion(cst.getString(2));
            ofer.setFecha_inicio(cst.getDate(3));
            ofer.setFecha_termino(cst.getDate(4));
            ofer.setImagen(cst.getBytes(5));
            ofer.setValoracion_total(cst.getInt(6));
            ofer.setPorc_descuento(cst.getInt(7));
            ofer.setId_sucursal(cst.getInt(8));
            ofer.setId_producto(cst.getInt(9));
           
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            ofer = null;
        }
        return ofer;
    }
    
    
    public int agregar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call AGREGAR_OFERTA (?,?,?,?,?,?,?,?,?)}");
            
            cst.setString(1, this.descripcion);
            cst.setDate(2, this.fecha_inicio);
            cst.setDate(3, this.fecha_termino);
            cst.setBytes(4, this.imagen);
            cst.setInt(5, this.valoracion_total);
            cst.setInt(6, this.porc_descuento);
            cst.setInt(7, this.id_sucursal);
            cst.setInt(8, this.id_producto);
            
            
            cst.registerOutParameter(9, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            respuesta = cst.getInt(9);
            

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    public int modificar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call MODIFICAR_OFERTA (?,?,?,?,?,?,?,?,?,?)}");
            
            cst.setInt(1, this.id);
            cst.setString(2, this.descripcion);
            cst.setDate(3, this.fecha_inicio);
            cst.setDate(4, this.fecha_termino);
            cst.setBytes(5, this.imagen);
            cst.setInt(6, this.valoracion_total);
            cst.setInt(7, this.porc_descuento);
            cst.setInt(8, this.id_sucursal);
            cst.setInt(9, this.id_producto);
            
            cst.registerOutParameter(10, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            respuesta = cst.getInt(10);
            

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    public int eliminar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call ELIMINAR_OFERTA (?,?)}");
            
            cst.setInt(1, this.id);
            
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
        return "Oferta{" + "id=" + id + ", descripcion=" + descripcion + ", fecha_inicio=" + fecha_inicio + ", fecha_termino=" + fecha_termino + ", valoracion_total=" + valoracion_total + ", porc_descuento=" + porc_descuento + '}';
    }
    
    
    
}