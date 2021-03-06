package MisPaquetes;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis
 * Clase Producto
 */
public class Producto {
    
    /**
     * Atributos
     */
    private int id;
    private String nombre;
    private String descripcion;
    private Date fecha_ing;
    private char estado;
    private int stk_seguro;
    private String rubro;
    private String desc_rubro;
    private int valor;
    private int stk_sucur;
    private int id_sucur;

    /**
     * Constructor por defecto
     */
    public Producto() {
    }
    
    /**
     * Mutadores y accesadores
     */
    
    public int getId_sucur() {
        return id_sucur;
    }

    public void setId_sucur(int id_sucur) {
        this.id_sucur = id_sucur;
    }
   
    public int getStk_sucur() {
        return stk_sucur;
    }

    public void setStk_sucur(int stk_sucur) {
        this.stk_sucur = stk_sucur;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_ing() {
        return fecha_ing;
    }

    public void setFecha_ing(Date fecha_ing) {
        this.fecha_ing = fecha_ing;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public int getStk_seguro() {
        return stk_seguro;
    }

    public void setStk_seguro(int stk_seguro) {
        this.stk_seguro = stk_seguro;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getDesc_rubro() {
        return desc_rubro;
    }

    public void setDesc_rubro(String desc_rubro) {
        this.desc_rubro = desc_rubro;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    
    /**
     * 
     * @param conn conexion con la base de datos
     * @return listado de productos
     * @throws SQLException 
     */
    public ResultSet listadoProductos(Conexion conn) throws SQLException{
        Statement stmt = conn.getConexion_base().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCTO");
        return rs;
    }
     
    /**
     * 
     * @param id identificar del objeto producto
     * @param conn conexion con la base de datos
     * @return Resultado de la busqueda de productos en la base datos
     */
    public Producto buscar(int id,Conexion conn){
        Producto pro = new Producto();
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call BUSCAR_PRODUCTO (?,?,?,?,?,?,?,?,?,?,?)}");
            
            cst.setInt(1, id);
            
            cst.registerOutParameter(2, java.sql.Types.VARCHAR);
            cst.registerOutParameter(3, java.sql.Types.VARCHAR);
            cst.registerOutParameter(4, java.sql.Types.DATE);
            cst.registerOutParameter(5, java.sql.Types.CHAR);
            cst.registerOutParameter(6, java.sql.Types.NUMERIC);
            cst.registerOutParameter(7, java.sql.Types.NUMERIC);
            cst.registerOutParameter(8, java.sql.Types.VARCHAR);
            cst.registerOutParameter(9, java.sql.Types.VARCHAR);
            cst.registerOutParameter(10, java.sql.Types.NUMERIC);
            cst.registerOutParameter(11, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            pro.setId(id);
            pro.setNombre(cst.getString(2));
            pro.setDescripcion(cst.getString(3));
            pro.setFecha_ing(cst.getDate(4));
            pro.setEstado(cst.getString(5).charAt(0));
            pro.setStk_seguro(cst.getInt(6));
            pro.setStk_sucur(cst.getInt(7));
            pro.setRubro(cst.getString(8));
            pro.setDesc_rubro(cst.getString(9));
            pro.setValor(cst.getInt(10));
            pro.setId_sucur(cst.getInt(11));
           
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            pro = null;
        }
        return pro;
    }
    
    /**
     * Metodo que agrega un producto a la base de datos
     * @param conn Conexion de la base de datos 
     * @return retorna 1 positivo , 0 negativo
     */
    public int agregar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call AGREGAR_PRODUCTO (?,?,?,?,?,?,?,?,?,?,?)}");
            
            cst.setString(1, this.nombre);
            cst.setString(2, this.descripcion);
            cst.setDate(3, this.fecha_ing);
            cst.setString(4, String.valueOf(this.estado));
            cst.setInt(5, this.stk_seguro);
            cst.setInt(6, this.stk_sucur);
            cst.setString(7, this.rubro);
            cst.setString(8, this.desc_rubro);
            cst.setInt(9, this.valor);
            cst.setInt(10, this.id_sucur);
            
            cst.registerOutParameter(11, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            respuesta = cst.getInt(11);
            

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    /***
     * Funcion que modifica un producto de la base de datos
     * @param conn  Conexion con la base datos
     * @return retorna 1 positivo , 0 negativo 
     */
    public int modificar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call MODIFICAR_PRODUCTO (?,?,?,?,?,?,?,?,?,?,?,?)}");
            
            cst.setInt(1, this.id);
            cst.setString(2, this.nombre);
            cst.setString(3, this.descripcion);
            cst.setDate(4, this.fecha_ing);
            cst.setString(5, String.valueOf(this.estado));
            cst.setInt(6, this.stk_seguro);
            cst.setInt(7, this.stk_sucur);
            cst.setString(8, this.rubro);
            cst.setString(9, this.desc_rubro);
            cst.setInt(10, this.valor);
            cst.setInt(11, this.id_sucur);
            
            cst.registerOutParameter(12, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            respuesta = cst.getInt(12);
            

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    /**
     * Funcion que elimina un producto de la base de datos
     * @param conn Conexion con la base de datos
     * @return retorna 1 positivo , 0 negativo
     */
    public int eliminar(Conexion conn){
        int respuesta = 0;
        try {
            
            CallableStatement cst = conn.getConexion_base().prepareCall("{call ELIMINAR_PRODUCTO (?,?)}");
            
            cst.setInt(1, this.id);
            
            cst.registerOutParameter(2, java.sql.Types.NUMERIC);
            
            cst.execute();
            
            respuesta = cst.getInt(2);
            

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    /**
     * Funcion que retorna el listado de productos por rubro
     * @param conn Conexion con la base de datos
     * @param rubro Rubro por el cual se buscara en la base de datos
     * @return Retorna el listado de productos por rubro
     * @throws SQLException 
     */
    public ResultSet listadoProductosPorRubro(Conexion conn,String rubro) throws SQLException{
        Statement stmt = conn.getConexion_base().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCTO WHERE RUBRO = '"+ rubro+ "'"
                + " AND SUCURSAL_ID_SUCUR= '" + this.id_sucur+ "'");
        return rs;
    }
    
    /**
     * Funcion toString 
     * @return retorna el nombre del producto
     */
    @Override
    public String toString() {
        return "" + nombre ;
    }
    
    /**
     * 
     * @param conn conexion con la base de datos
     * @return listado de productos por sucursal
     * @throws SQLException 
     */
    public ResultSet listadoProductosSucur(Conexion conn) throws SQLException{
        Statement stmt = conn.getConexion_base().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCTO WHERE SUCURSAL_ID_SUCUR = '" + this.id_sucur+ "'");
        return rs;
    }
    
    
}
