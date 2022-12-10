/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abrirarchivos;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author 24DD018LA
 */
public class tabla1 {
    private int id_tabla, tarjeta,id_pago,id_banco, id_empleado,anios,numero_transaccion ;
    private String moneda,identidad,hora,fecha,telefono,correo,monto,sitio_web,fecha_nacimiento;
    Conexion cn;
    
    public tabla1 () {
   
    }
    public tabla1(int id_tabla, int tarjeta, int id_pago, int id_banco, int id_empleado, int anios, int numero_transaccion, String moneda, String fecha_nacimiento ,String identidad, String hora, String fecha, String telefono, String correo, String monto, String sitio_web) {
        this.id_tabla = id_tabla;
        this.tarjeta = tarjeta;
        this.id_pago = id_pago;
        this.id_banco = id_banco;
        this.id_empleado = id_empleado;
        this.anios = anios;
        this.numero_transaccion = numero_transaccion;
        this.moneda = moneda;
        this.fecha_nacimiento = fecha_nacimiento;
        this.identidad = identidad;
        this.hora = hora;
        this.fecha = fecha;
        this.telefono = telefono;
        this.correo = correo;
        this.monto = monto;
        this.sitio_web = sitio_web;
    }

    public int getId_tabla() {
        return id_tabla;
    }

    public void setId_tabla(int id_tabla) {
        this.id_tabla = id_tabla;
    }

    public int getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(int tarjeta) {
        this.tarjeta = tarjeta;
    }

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public int getId_banco() {
        return id_banco;
    }

    public void setId_banco(int id_banco) {
        this.id_banco = id_banco;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public int getAnios() {
        return anios;
    }

    public void setAnios(int anios) {
        this.anios = anios;
    }

    public int getNumero_transaccion() {
        return numero_transaccion;
    }

    public void setNumero_transaccion(int numero_transaccion) {
        this.numero_transaccion = numero_transaccion;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getIdentidad() {
        return identidad;
    }

    public void setIdentidad(String identidad) {
        this.identidad = identidad;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getSitio_web() {
        return sitio_web;
    }

    public void setSitio_web(String sitio_web) {
        this.sitio_web = sitio_web;
    }
    
    public DefaultTableModel leer(){ 
        DefaultTableModel tabla = new DefaultTableModel();
         try{
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "select id_tabla as id, targeta, id_pago, moneda,numero_transacion, id_banco, identidad, hora, anios_experiencia, fecha_nacimiento, fecha,telefono, correo_electronico, sitio_web,monto, id_empleado from tabla1;";  
            ResultSet consulta = cn.coneccionBD.createStatement().executeQuery(query);
            String encabezado[] = {"id","targeta","id_pago","moneda","numero_transacion","id_banco","identidad","hora","anios_experiencia","fecha_nacimiento","fecha","telefono","correo_electronico","sitio_web","monto","id_empleado"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[16];
            while (consulta.next()){
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("targeta");
                datos[2] = consulta.getString("id_pago");
                datos[3] = consulta.getString("moneda");
                datos[4] = consulta.getString("numero_transacion");
                datos[5] = consulta.getString("id_banco");
                datos[6] = consulta.getString("identidad");
                datos[7] = consulta.getString("hora");
                datos[8] = consulta.getString("anios_experiencia");
                datos[9] = consulta.getString("fecha");
                datos[10] = consulta.getString("fecha");
                datos[11] = consulta.getString("telefono");
                datos[12] = consulta.getString("correo_electronico");
                datos[13] = consulta.getString("sitio_web");
                datos[14] = consulta.getString("monto");
                datos[15] = consulta.getString("id_empleado"); 
                tabla.addRow(datos);
                                          }
                 cn.cerrar_conexion();
                 }catch(SQLException ex){
                     cn.cerrar_conexion(); 
                    System.out.println("Error" + ex.getMessage());
                                 }
                            return tabla;
                    }
    
    
    public void agregar(){
         int retorno = 0; 
         try{
            PreparedStatement parametro;
            String query ="NSERT INTO `db_proyecto`.`tabla1` (id_tabla, targeta, id_pago, moneda, numero_transacion,id_banco, identidad, hora, anios_experiencia, fecha_nacimiento, fecha, telefono, correo_electronico, sitio_web, monto, id_empleado) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.coneccionBD.prepareStatement(query);
                parametro.setInt(1, this.getId_tabla());
                parametro.setInt(2, this.getTarjeta());
                parametro.setInt(3, this.getId_pago());
                parametro.setString(4, this.getMoneda());
                parametro.setInt(5, this.getNumero_transaccion());
                parametro.setInt(6, this.getId_banco());
                parametro.setString(7, this.getIdentidad());
                parametro.setString(8, this.getHora());
                parametro.setInt(9, this.getAnios());
                parametro.setString(11, this.getFecha_nacimiento());
                parametro.setString(12, this.getFecha());
                parametro.setString(13, this.getTelefono());
                parametro.setString(14, this.getSitio_web());
                parametro.setString(15, this.getMonto());
                parametro.setInt(16, this.getId_empleado());
                int executar = parametro.executeUpdate();
                cn.cerrar_conexion();
                             }catch(HeadlessException | SQLException ex){                                 
                                    System.out.println("Error..." + ex.getMessage());
                                    retorno=0;
                                                    }
                                    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
                       }
