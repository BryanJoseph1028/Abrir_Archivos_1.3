/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abrirarchivos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author 24DD018LA
 */
public class Conexion {
     public Connection coneccionBD;
    public final String bd ="db_proyecto";
    public final String urlconexion = String.format("jdbc:mysql://useSSL=false/ localhost/%s",bd);
    public final String usuario = "usr_proyrcto";
    public final String contra = "proyecto@123";
    public final String jdbc = "com.mysql.jdbc.Driver";
  
    public void abrir_conexion(){
        try{
         Class.forName(jdbc);
         coneccionBD = DriverManager.getConnection(urlconexion,usuario,contra);
         //JOptionPane.showMessageDialog(null,"Conexion Exitosa...","Exito",JOptionPane.INFORMATION_MESSAGE);
         
                        }catch(ClassNotFoundException | SQLException ex){
                            System.out.println("Error..." + ex.getMessage());
                                                }
                                        }
    
    public void cerrar_conexion(){
        try{
          coneccionBD.close();
                }catch(SQLException ex){
                     System.out.println("Error..." + ex.getMessage());
                                }
                            }
   
    
}
