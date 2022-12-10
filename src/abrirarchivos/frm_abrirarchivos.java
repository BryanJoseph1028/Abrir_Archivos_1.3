/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abrirarchivos;
import abrirarchivos.formulario;
import Alertas.AlertInformation;
import Alertas.AlertSucess;
import Alertas.AlertWarning;
import Alertas.AlertWarningSave;
import componentes.FadeEffect;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author 24DD018LA
 */
public class frm_abrirarchivos extends javax.swing.JFrame {
    JFileChooser seleccionar = new JFileChooser();
    File archivo;
    FileInputStream entrada;
    FileOutputStream salida;
    private int x, y;
    private boolean minimiza = false;
    private int contadorNuevo = 0;
    public static boolean crearNuevo = false;
    public static boolean abrirArchivo = false;
    public static boolean actualizar = false;
    List<identificador> tokenslist;
    

    /**
     * Creates new form frm_abrirarchivos
     */
    public frm_abrirarchivos() {
        initComponents();
        
    }
  
    
    public String AbrirArchivo(File archivo){
        String documento ="";
        try{
        entrada = new FileInputStream(archivo);    
        int ascci;
        while((ascci=entrada.read())!=-1){
            char caracter =(char)ascci;
            documento+=caracter;
        }
        }catch(Exception e){
        }
        return documento;
    }
     private frm_abrirarchivos frmA;
    public static String ruta = "";
    
     public void LeerTxt(frm_abrirarchivos frmA) {
     this.frmA = frmA;
        seleccionar = new JFileChooser();
        seleccionar.setFileSelectionMode(0);
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("TXT", "txt");
        seleccionar.setFileFilter(filtroImagen);
        seleccionar.setDialogTitle("Abrir archivo");
        if (seleccionar.showOpenDialog(frmA) == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();
            frmA.lbl_nombre.setText(archivo.getName());
            ruta = seleccionar.getSelectedFile().toString();
            try {
                if (archivo.exists()) {
                    BufferedReader leeArchivo = new BufferedReader(new FileReader(archivo));
                    String Slinea, datos = "";
                    while ((Slinea = leeArchivo.readLine()) != null) {
                        datos = datos + Slinea + "\n";
                    }
                    frmA.txt_area.setText(datos);
                    frmA.txt_area.setEditable(true);
                    frmA.txt_area.requestFocus();
                    leeArchivo.close();
                    this.frmA.abrirArchivo = true;
                    this.frmA.crearNuevo = false;
                } else {
                    System.out.println("Fichero No Existe");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
     public void CrearTxt(frm_abrirarchivos frmA, String SCadena, String nombre) {
        this.frmA = frmA;
    
        seleccionar = new JFileChooser();
        seleccionar.setFileSelectionMode(0);
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("TXT", "txt");
        seleccionar.setFileFilter(filtroImagen);
        seleccionar.setDialogTitle("Guardar archivo " + nombre);
        seleccionar.setSelectedFile(new File(nombre));
        if (seleccionar.showSaveDialog(frmA) == JFileChooser.APPROVE_OPTION) {
            ruta = seleccionar.getSelectedFile().toString();
            archivo = new File(ruta);
            frmA.lbl_nombre.setText(archivo.getName());
            try {
                //Si Existe el fichero lo borra
                if (archivo.exists()) {
                    archivo.delete();
                }
                BufferedWriter wr = new BufferedWriter(new FileWriter(archivo));
                FileWriter escribirArchivo = new FileWriter(archivo, true);
                BufferedWriter buffer = new BufferedWriter(escribirArchivo);
                buffer.write(SCadena);
                buffer.newLine();
                buffer.close();
                wr.close();
                escribirArchivo.close();

                this.frmA.abrirArchivo = true;
                this.frmA.crearNuevo = false;
            } catch (Exception ex) {
            }
        }
    }
    public void GuardarTxt(String SCadena, String nombre) {

        System.out.println(ruta);
        archivo = new File(ruta);
        try {
            if (archivo.exists()) {
                archivo.delete();
            }
            BufferedWriter wr = new BufferedWriter(new FileWriter(archivo));
            FileWriter escribirArchivo = new FileWriter(archivo, true);
            BufferedWriter buffer = new BufferedWriter(escribirArchivo);
            buffer.write(SCadena);
            buffer.newLine();
            buffer.close();
            wr.close();
            escribirArchivo.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void Guardar2Txt(String SCadena, String nombre) {
        System.out.println(ruta);
        archivo = new File(ruta);
        try {
            if (archivo.exists()) {
                archivo.delete();
            }
            BufferedWriter wr = new BufferedWriter(new FileWriter(archivo));
            FileWriter escribirArchivo = new FileWriter(archivo, true);
            BufferedWriter buffer = new BufferedWriter(escribirArchivo);
            buffer.write(SCadena);
            buffer.newLine();
            buffer.close();
            wr.close();
            escribirArchivo.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void probarLexerFile() throws IOException{
        int contIDs=0;
        tokenslist = new LinkedList<identificador>();
        File fichero = new File ("fichero.txt");
        if (abrirArchivo) {
            System.out.println("Existente");
            new frm_abrirarchivos().GuardarTxt(this.txt_area.getText(), this.lbl_nombre.getText());
        }
        PrintWriter writer;
        try {
            writer = new PrintWriter(fichero);
            writer.print(txt_area.getText());
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(frm_abrirarchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        Reader reader = new BufferedReader(new FileReader("fichero.txt"));
        Lexer lexer = new Lexer (reader);
        String resultado="\n";
        while (true){
            Token token =lexer.yylex();
            if (token == null){
                for(int i=0;i<tokenslist.size();i++){
                    System.out.println(tokenslist.get(i).nombre + "=" + tokenslist.get(i).ID);
                }
                txt_area.setText(resultado);
                return;
            }
            switch (token){
                
             
                case ERROR:
                    JOptionPane.showMessageDialog(null, "El identificador no es correcto, ingrese de nuevo");
                    break;
              
                case CADENA : {
                    contIDs++;
                    identificador tokenitem=new identificador();
                    tokenitem.nombre=lexer.lexeme;
                    tokenitem.ID=contIDs;
                    tokenslist.add(tokenitem);
                    if(resultado.contains("$")){
                     JOptionPane.showMessageDialog(null, "Identificador");
                    }else{
                  JOptionPane.showMessageDialog(null, "Variable Incorrecta");
                    }
                    break;
                }
                case NUM:
                     JOptionPane.showMessageDialog(null, "Es un numero");
                    break;
                default:
                    resultado=resultado+ "TOKEN <"+ lexer.lexeme + ">\n ";
            }
    }
 }
    public void tablaResultado(){
        Object[][] matriz = new Object [tokenslist.size()][2];
        for(int i =0; i<tokenslist.size();i++){
            matriz[i][0] = tokenslist.get(i).nombre;
            matriz[i][1] = tokenslist.get(i).ID;
        }
      
    }
   
     public void BorrarTxt(frm_abrirarchivos frmA) {
        this.frmA = frmA;
        archivo = new File(ruta);
        try {
           
            if (archivo.exists()) {
           
                archivo.delete();
                Alertas.AlertSucess a = new AlertSucess(this.frmA, true);
                a.lblMensaje1.setText("Archivo eliminado.");
                a.lblMensaje2.setText("");
                a.setVisible(true);
            }
        } catch (Exception ex) {
            /*Captura un posible error y le imprime en pantalla*/
            System.out.println(ex.getMessage());
        }
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_area = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        btn_minimizar = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        btn_cerrar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl_nombre = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        btn_guardar_como = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        btn_abrirArchivo = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        btn_guardar = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        btn_eliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        btn_guardar1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        btn_hex = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        btn_numero = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btn_expr = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_area.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txt_areaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(txt_area);

        jScrollPane8.setViewportView(jScrollPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane8)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btn_minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/minimize.png"))); // NOI18N
        btn_minimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_minimizarActionPerformed(evt);
            }
        });
        jScrollPane7.setViewportView(btn_minimizar);

        btn_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dialog_close.png"))); // NOI18N
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });
        jScrollPane6.setViewportView(btn_cerrar);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Trajan Pro", 1, 14)); // NOI18N
        jLabel1.setText("Nombre del Archivo:");

        lbl_nombre.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        lbl_nombre.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1)))
                .addGap(27, 27, 27))
        );

        jLabel6.setFont(new java.awt.Font("Yu Gothic Medium", 3, 24)); // NOI18N
        jLabel6.setText("Editor de Texto ");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/text.png"))); // NOI18N

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btn_guardar_como.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/document_save_as.png"))); // NOI18N
        btn_guardar_como.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_comoActionPerformed(evt);
            }
        });
        jScrollPane3.setViewportView(btn_guardar_como);

        btn_abrirArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/folder_open.png"))); // NOI18N
        btn_abrirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_abrirArchivoActionPerformed(evt);
            }
        });
        jScrollPane2.setViewportView(btn_abrirArchivo);

        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/document_save.png"))); // NOI18N
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });
        jScrollPane4.setViewportView(btn_guardar);

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/delete.png"))); // NOI18N
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jScrollPane5.setViewportView(btn_eliminar);

        jLabel2.setFont(new java.awt.Font("Serif", 2, 10)); // NOI18N
        jLabel2.setText("Abir archivo");

        jLabel5.setFont(new java.awt.Font("Serif", 2, 10)); // NOI18N
        jLabel5.setText("Guardar como");

        jLabel3.setFont(new java.awt.Font("Serif", 2, 10)); // NOI18N
        jLabel3.setText("Crear");

        jLabel4.setFont(new java.awt.Font("Serif", 2, 10)); // NOI18N
        jLabel4.setText("Numero");

        btn_guardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save.png"))); // NOI18N
        btn_guardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar1ActionPerformed(evt);
            }
        });
        jScrollPane9.setViewportView(btn_guardar1);

        jLabel8.setFont(new java.awt.Font("Serif", 2, 10)); // NOI18N
        jLabel8.setText("Guardar ");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/automata.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Serif", 2, 10)); // NOI18N
        jLabel9.setText("Eliminar");

        jLabel10.setFont(new java.awt.Font("Serif", 2, 10)); // NOI18N
        jLabel10.setText("Automata");

        jLabel11.setFont(new java.awt.Font("Serif", 2, 10)); // NOI18N
        jLabel11.setText("Hexadecimal");

        btn_hex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8-base-64-50.png"))); // NOI18N
        btn_hex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hexActionPerformed(evt);
            }
        });
        jScrollPane10.setViewportView(btn_hex);

        btn_numero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/NUMBERS.png"))); // NOI18N
        btn_numero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_numeroActionPerformed(evt);
            }
        });
        jScrollPane11.setViewportView(btn_numero);

        btn_expr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/simbolos1.png"))); // NOI18N
        btn_expr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exprActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(btn_expr, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_expr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel12.setFont(new java.awt.Font("Serif", 2, 10)); // NOI18N
        jLabel12.setText("Expresiones Regulares");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel2)
                        .addGap(59, 59, 59)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(12, 12, 12)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel8)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel11)
                        .addGap(29, 29, 29)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(17, 17, 17))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(11, 11, 11))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane9)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(8, 8, 8))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // TODO add your handling code here:
         if (abrirArchivo) {
            System.out.println("Existente");
            new frm_abrirarchivos().GuardarTxt(this.txt_area.getText(), this.lbl_nombre.getText());
        }
        if (crearNuevo) {
            System.out.println("Nuevo");
            new frm_abrirarchivos().CrearTxt(this, this.txt_area.getText(), this.lbl_nombre.getText());
        }
        if (!crearNuevo && !abrirArchivo) {
            Alertas.AlertInformation a = new AlertInformation(this, true);
            a.lblMensaje1.setText("Debes crear o abrir un archvo");
            a.lblMensaje2.setText("para poder guardarlo.");
            a.setVisible(true);
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_abrirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_abrirArchivoActionPerformed
        // TODO add your handling code here:
         new frm_abrirarchivos().LeerTxt(this);
    }//GEN-LAST:event_btn_abrirArchivoActionPerformed

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        // TODO add your handling code here:
        Alertas.AlertWarning a = new AlertWarning(this, true);
        Alertas.AlertWarningSave a1 = new AlertWarningSave(this, true);
        if (crearNuevo && !abrirArchivo) {
            a1.lblMensaje1.setText("Aun no guardas el archivo nuevo.");
            a1.lblMensaje2.setText("¿Deseas guardarlo?.");
            a1.setVisible(true);
            if (AlertWarningSave.guardar) {
                new frm_abrirarchivos().CrearTxt(this, this.txt_area.getText(), this.lbl_nombre.getText());
                FadeEffect.fadeOutFrame(this, 50, 0.1f);
            }
            if (AlertWarningSave.noGuardar) {
                FadeEffect.fadeOutFrame(this, 50, 0.1f);
            }
        } else {
            a.lblMensaje1.setText("La aplicación terminara.");
            a.lblMensaje2.setText("");
            a.setVisible(true);
            if (AlertWarning.ok) {
                FadeEffect.fadeOutFrame(this, 50, 0.1f);
            }
        }
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void btn_minimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_minimizarActionPerformed
        // TODO add your handling code here:
         this.setExtendedState(ICONIFIED);
        if (!minimiza) {
            minimiza = false;
        } else {
            minimiza = true;
        }
    }//GEN-LAST:event_btn_minimizarActionPerformed

    private void btn_guardar_comoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_comoActionPerformed
        // TODO add your handling code here:
        contadorNuevo++;
        this.lbl_nombre.setText("Archivo" + contadorNuevo + ".txt");
        this.txt_area.setText("");
        this.txt_area.setEditable(true);
        this.txt_area.requestFocus();
        crearNuevo = true;
        abrirArchivo = false;
    }//GEN-LAST:event_btn_guardar_comoActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
        if (abrirArchivo) {
            new frm_abrirarchivos().BorrarTxt(this);
            this.lbl_nombre.setText("");
            this.txt_area.setText("");
        }else{
            Alertas.AlertInformation a = new AlertInformation(this, true);
            a.lblMensaje1.setText("Debes abrir un archvo");
            a.lblMensaje2.setText("para poder eliminarlo.");
            a.setVisible(true);
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void txt_areaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_areaMousePressed
        // TODO add your handling code here:
           
         if (!this.txt_area.isEditable()) {
            Alertas.AlertInformation a = new AlertInformation(this, true);
            a.lblMensaje1.setText("Para poder escribir debes");
            a.lblMensaje2.setText("crear un nuevo archivo.");
            a.setVisible(true);
        }
    }//GEN-LAST:event_txt_areaMousePressed

    private void btn_guardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar1ActionPerformed
        // TODO add your handling code here:
        if (abrirArchivo) {
            System.out.println("Existente");
            new frm_abrirarchivos().GuardarTxt(this.txt_area.getText(), this.lbl_nombre.getText());
        }
        if (crearNuevo) {
            System.out.println("Nuevo");
            new frm_abrirarchivos().CrearTxt(this, this.txt_area.getText(), this.lbl_nombre.getText());
        }
       
    }//GEN-LAST:event_btn_guardar1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
       
        
        File fichero = new File ("archivo.txt");
        PrintWriter writer;
        try {
            writer = new PrintWriter(fichero);
            writer.print(txt_area.getText());
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(frm_abrirarchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            txt_area.getText();
        Reader lector = new BufferedReader(new FileReader("archivo.txt"));
        Lexer lexer = new Lexer (lector);
        String resultado="";
        while (true){
            Token token;
                token = lexer.yylex();
            if (token == null){
                resultado +="fin";
                   JOptionPane.showMessageDialog(null, "Resultado" + resultado);
                return;
            }
            switch (token){
                  
                case ERROR:
                    txt_area.getText();
                    JOptionPane.showMessageDialog(null, "El identificador no es correcto, ingrese de nuevo" + token);
                    break;
              
                case CADENA : {
                  txt_area.getText();
                    identificador tokenitem=new identificador();
                    tokenitem.nombre=lexer.lexeme;
                   
                    if(resultado.contains("$")){
                     JOptionPane.showMessageDialog(null, "Identificador" +token);
                    }else{
                  JOptionPane.showMessageDialog(null, "Variable Incorrecta"+ token);
                    }
                    break;
                }
                case NUM:
                    
                     JOptionPane.showMessageDialog(null, "Es un numero" + token);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, " intentelo de nuevo");
            }
    }
        }catch (FileNotFoundException ex) {
            Logger.getLogger(frm_abrirarchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(frm_abrirarchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_numeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_numeroActionPerformed
        int inicio =1;
       int finalizar = 7;
       int estado = inicio;
        char  a = '1' ;
        char b = '2';
        char c = '3';
        char d = '4';
        char e = '5';
        char f = '6';
        char g = '7';
        char h = '8';
        char i = '9';
        char j = '0';
        char k='.';
        char l = '+';
        char m = '-';
         char n  = 'E';
        boolean fin = false ;
        int contador = 0;
        String palabra  = txt_area.getText();
        char[] caracteres = palabra.toCharArray();
            while(fin ==false){
            txt_area.getText();
                if(contador > caracteres.length-1){
                    fin =true; 
                    break;
                        }else{
                         switch(estado){
        case 1:{
                if(caracteres[contador]==b){
                    estado =2;
                    JOptionPane.showMessageDialog(null, "digito");
                    }else if(caracteres[contador]==a){
                            estado =2;
                            JOptionPane.showMessageDialog(null, "digito");
                    }else if (caracteres[contador]==c){
                            estado =2;
                             JOptionPane.showMessageDialog(null, "digito");
                    }else if (caracteres[contador]==d){
                         estado =2;
                         JOptionPane.showMessageDialog(null, "digito");
                    }else if (caracteres[contador]==e){
                         estado =2;
                          JOptionPane.showMessageDialog(null, "digito");
                    }else if (caracteres[contador]==f){
                         estado =2;
                           JOptionPane.showMessageDialog(null, "digito");
                    }else if (caracteres[contador]==g){
                         estado =2;
                         JOptionPane.showMessageDialog(null, "digito");
                    }else if (caracteres[contador]==h){
                         estado =2;
                          JOptionPane.showMessageDialog(null, "digito");
                    }else if (caracteres[contador]==i){
                         estado =2;
                       JOptionPane.showMessageDialog(null, "digito");
                    }else if (caracteres[contador]==j){
                         estado =2;
                          JOptionPane.showMessageDialog(null, "digito");
                    }else{
                          if(caracteres[contador]==m){
                            }else if(caracteres[contador]==l){
                            }else if(caracteres[contador]==n){
                            }else if(caracteres[contador]==k){
                            }
                                 JOptionPane.showMessageDialog(null, "Error en el ingreso, digitelo de nuevo");
                    } 
                contador++;
                continue;
                }
            case 2:{
    
                    if(caracteres[contador]==b){
                    estado =2;
                    }if(caracteres[contador]==a){
                            estado =2;
                          
                    }if (caracteres[contador]==c){
                            estado =2;
                          
                    }if (caracteres[contador]==d){
                         estado =2;
                         
                    }if (caracteres[contador]==e){
                         estado =2;
                         
                    }if (caracteres[contador]==f){
                         estado =2;

                    }if (caracteres[contador]==g){
                         estado =2;

                    }if (caracteres[contador]==h){
                         estado =2;

                    }if (caracteres[contador]==i){
                         estado =2;

                    }if (caracteres[contador]==j){
                         estado =2;

                    }else if(caracteres[contador]==k){
                         estado =3; 
                    }else if(caracteres[contador]==n){
                         estado =5; 
                    }else{
                         if(caracteres[contador]==m){
                            }else if(caracteres[contador]==l){
                            }else if(caracteres[contador]==n){
                            }else if(caracteres[contador]==k){
                            }
                                 
                    } 
                contador++;
                continue;
                }
             case 3:{
                if(caracteres[contador]==b){
                    estado =4;
                    }else if(caracteres[contador]==a){
                            estado =4;
                  JOptionPane.showMessageDialog(null, "NUMERO DECIMAL");
                    }else if (caracteres[contador]==c){
                            estado =4;
                      JOptionPane.showMessageDialog(null, "NUMERO DECIMAL");
                    }else if (caracteres[contador]==d){
                         estado =4;
                      JOptionPane.showMessageDialog(null, "NUMERO DECIMAL");
                    }else if (caracteres[contador]==e){
                         estado =4;
                      JOptionPane.showMessageDialog(null, "NUMERO DECIMAL");
                    }else if (caracteres[contador]==f){
                         estado =4;
                      JOptionPane.showMessageDialog(null, "NUMERO DECIMAL");
                    }else if (caracteres[contador]==g){
                         estado =4;
                       JOptionPane.showMessageDialog(null, "NUMERO DECIMAL");
                    }else if (caracteres[contador]==h){
                         estado =4;
                      JOptionPane.showMessageDialog(null, "NUMERO DECIMAL");
                    }else if (caracteres[contador]==i){
                         estado =4;
                      JOptionPane.showMessageDialog(null, "NUMERO DECIMAL");
                    }else if (caracteres[contador]==j){
                         estado =4;
                              JOptionPane.showMessageDialog(null, "NUMERO DECIMAL");
                    }else{
                          if(caracteres[contador]==m){
                            }else if(caracteres[contador]==l){
                            }else if(caracteres[contador]==n){
                            }else if(caracteres[contador]==k){
                            }
                 
                    } 
                contador++;
                continue;
                }
             case 4:{
                if(caracteres[contador]==b){
                    estado =4;
                    }if(caracteres[contador]==a){
                            estado =4;
                          
                    }if (caracteres[contador]==c){
                            estado =4;
                          
                    }if (caracteres[contador]==d){
                         estado =4;
                         
                    }if (caracteres[contador]==e){
                         estado =4;
                         
                    }if (caracteres[contador]==f){
                         estado =4;

                    }if (caracteres[contador]==g){
                         estado =4;

                    }if (caracteres[contador]==h){
                         estado =4;

                    }if (caracteres[contador]==i){
                         estado =4;

                    }if (caracteres[contador]==j){
                         estado =4;
                    }else if(caracteres[contador]==n){
                            estado =5;
                            JOptionPane.showMessageDialog(null, "Valor infinito");

                    }else{
                         if(caracteres[contador]==m){
                            }else if(caracteres[contador]==l){
                            }else if(caracteres[contador]==n){
                            }else if(caracteres[contador]==k){
                            }
                           
                    } 
                contador++;
                continue;
                }
             case 5:{
                 if(caracteres[contador]==b){
                    estado =7;
                    }if(caracteres[contador]==a){
                            estado =7;
                          
                    }if (caracteres[contador]==c){
                            estado =7;
                          
                    }if (caracteres[contador]==d){
                         estado =7;
                         
                    }if (caracteres[contador]==e){
                         estado =7;
                         
                    }if (caracteres[contador]==f){
                         estado =7;

                    }if (caracteres[contador]==g){
                         estado =7;

                    }if (caracteres[contador]==h){
                         estado =7;

                    }if (caracteres[contador]==i){
                         estado =7;

                    }if (caracteres[contador]==j){
                         estado =7;
                    }
                    if (caracteres[contador]==l){
                         estado =7;//
                    }
                    if (caracteres[contador]==m){
                         estado =7;//
                    }else{
                         if(caracteres[contador]==m){
                            }else if(caracteres[contador]==l){
                            }else if(caracteres[contador]==n){
                            }else if(caracteres[contador]==k){
                            }
                                
                    }
                  contador++;
                continue;
             }
             case 6:{
                 if(caracteres[contador]==b){
                    estado =7;
                    }if(caracteres[contador]==a){
                            estado =7;
                          
                    }if (caracteres[contador]==c){
                            estado =7;
                          
                    }if (caracteres[contador]==d){
                         estado =7;
                         
                    }if (caracteres[contador]==e){
                         estado =7;
                         
                    }if (caracteres[contador]==f){
                         estado =7;

                    }if (caracteres[contador]==g){
                         estado =7;

                    }if (caracteres[contador]==h){
                         estado =7;

                    }if (caracteres[contador]==i){
                         estado =7;

                    }if (caracteres[contador]==j){
                         estado =7;
                    }
                    else{
                        if(caracteres[contador]==m){
                            }else if(caracteres[contador]==l){
                            }else if(caracteres[contador]==n){
                            }else if(caracteres[contador]==k){
                            }
                            
                    } 
                 contador++;
                continue;
             }
             case 7:{
                    if(caracteres[contador]==b){
                    estado =7;
                    
                    }else if(caracteres[contador]==a){
                            estado =7;
                    
                    }else if (caracteres[contador]==c){
                            estado =7;
                    
                    }else if (caracteres[contador]==d){
                         estado =7;
                    
                    }else if (caracteres[contador]==e){
                         estado =7;
                    
                    }else if (caracteres[contador]==f){
                         estado =7;
                    
                    }else if (caracteres[contador]==g){
                         estado =7;
                    
                    }else if (caracteres[contador]==h){
                         estado =7;
                    
                    }else if (caracteres[contador]==i){
                         estado =7;
                    
                    }else if (caracteres[contador]==j){
                         estado =7;
                    
                    }
                 contador++;
                continue;
                   }
                }
        if(estado ==finalizar){
            JOptionPane.showMessageDialog(null, "el numero es correcto"); 
            }else{
              JOptionPane.showMessageDialog(null, "el numero es correcto"); 
            }
        } 
    }
    }//GEN-LAST:event_btn_numeroActionPerformed

    private void btn_hexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hexActionPerformed
        // TODO add your handling code here:
       int inicio =0;
       int finalizar = 8;
       int estado = inicio;
        char  a = '1' ;
        char b = '2';
        char c = '3';
        char d = '4';
        char e = '5';
        char f = '6';
        char g = '7';
        char h = '8';
        char i = '9';
        char j = '0';
        char k ='A';
        char l  = 'B';
        char m = 'C';
        char n  = 'D';
        char o  = 'E';
        char p  = 'F';
        char q = 'a';
        char r = 'b';
        char s = 'c';
        char t = 'd';
        char u = 'e';
        char v = 'f';
        char w ='z';
        char y = '.';
        char z = 'X';
        char a1 = 'x';
        char a2 = 'm';
        char a3 = 'n';
        char a4= 'k';
        boolean fin = false ;
        int contador = 0;
        String palabra  = txt_area.getText();
        char[] caracteres = palabra.toCharArray();
            while(fin ==false){
            txt_area.getText();
                if(contador > caracteres.length-1){
                    fin =true; 
                    break;
                        }else{
                         switch(estado){
         case 0:{
                   if(caracteres[contador]==w){
                    estado =0;
                    JOptionPane.showMessageDialog(null, "LAMDA");
                    } if(caracteres[contador]==j){
                            estado =1;
                            JOptionPane.showMessageDialog(null, "INICIO");
                    }
                    if(caracteres[contador]==a){
                            estado =1;
                            JOptionPane.showMessageDialog(null, "SISTEMA OCTAL");
                    }
                    if(caracteres[contador]==b){
                            estado =5;
                            JOptionPane.showMessageDialog(null, "SISTEMA OCTAL");
                    }if(caracteres[contador]==c){
                            estado =5;
                            JOptionPane.showMessageDialog(null, "SISTEMA OCTAL");
                    }if(caracteres[contador]==d){
                            estado =5;
                            JOptionPane.showMessageDialog(null, "SISTEMA OCTAL");
                    }if(caracteres[contador]==e){
                            estado =5;
                            JOptionPane.showMessageDialog(null, "SISTEMA OCTAL");
                    }if(caracteres[contador]==f){
                            estado =5;
                            JOptionPane.showMessageDialog(null, "SISTEMA OCTAL");
                    }if(caracteres[contador]==g){
                            estado =5;
                            JOptionPane.showMessageDialog(null, "SISTEMA OCTAL");
                    }if(caracteres[contador]==h){
                            estado =5;
                            JOptionPane.showMessageDialog(null, "SISTEMA OCTAL");
                    }if(caracteres[contador]==i){
                            estado =5;
                            JOptionPane.showMessageDialog(null, "SISTEMA OCTAL");
                    }if(caracteres[contador]==y){
                            estado =6;
                            JOptionPane.showMessageDialog(null, "PUNTO , ERROR ");
                    }else{
                          if(caracteres[contador]==a2){
                            }else if(caracteres[contador]==a3){
                            }else if(caracteres[contador]==a4){
                            }else if(caracteres[contador]==a3){
                            }
                                 JOptionPane.showMessageDialog(null, "Error en el ingreso, digitelo de nuevo");
                    } 
                contador++;
                continue;       
               }
        case 1:{
                if(caracteres[contador]==j){
                            estado =8;
                          
                    }
                    if(caracteres[contador]==a){
                            estado =8;
                          
                    }
                    if(caracteres[contador]==b){
                            estado =8;
                            
                    }if(caracteres[contador]==c){
                            estado =8;
                            
                    }if(caracteres[contador]==d){
                            estado =8;
                            
                    }if(caracteres[contador]==e){
                            estado =8;
                            
                    }if(caracteres[contador]==f){
                            estado =8;
                            
                    }if(caracteres[contador]==g){
                            estado =8;
                            
                    }if(caracteres[contador]==z){
                            estado =2;
                            JOptionPane.showMessageDialog(null, "X");
                    }if(caracteres[contador]==a1){
                            estado =2;
                            JOptionPane.showMessageDialog(null, "x");
                    }if(caracteres[contador]==a1){
                            estado =2;
                            JOptionPane.showMessageDialog(null, "SISTEMA DECIMAL");
                    }if(caracteres[contador]==j){
                            estado =6;
                            JOptionPane.showMessageDialog(null, "SISTEMA DECIMAL");
                    }if(caracteres[contador]==a){
                            estado =6;
                            JOptionPane.showMessageDialog(null, "SISTEMA DECIMAL");
                    }if(caracteres[contador]==b){
                            estado =6;
                            JOptionPane.showMessageDialog(null, "SISTEMA DECIMAL");
                    }if(caracteres[contador]==c){
                            estado =6;
                            JOptionPane.showMessageDialog(null, "SISTEMA DECIMAL");
                    }if(caracteres[contador]==d){
                            estado =6;
                            JOptionPane.showMessageDialog(null, "SISTEMA DECIMAL");
                    }if(caracteres[contador]==e){
                            estado =6;
                            JOptionPane.showMessageDialog(null, "SISTEMA DECIMAL");
                    }if(caracteres[contador]==f){
                            estado =6;
                            JOptionPane.showMessageDialog(null, "SISTEMA DECIMAL");
                    }if(caracteres[contador]==g){
                            estado =6;
                            JOptionPane.showMessageDialog(null, "SISTEMA DECIMAL");
                    }if(caracteres[contador]==h){
                            estado =6;
                            JOptionPane.showMessageDialog(null, "SISTEMA DECIMAL");
                    }if(caracteres[contador]==i){
                            estado =6;
                            JOptionPane.showMessageDialog(null, "SISTEMA DECIMAL");
                    }else{
                          if(caracteres[contador]==a2){
                            }else if(caracteres[contador]==a3){
                            }else if(caracteres[contador]==a4){
                            }else if(caracteres[contador]==a3){
                            }
                                
                    } 
                contador++;
                continue;       
               
                }
            case 2:{
    
                  if(caracteres[contador]==q){
                            estado =3;
                            JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }
                    if(caracteres[contador]==q){
                            estado =3;
                           JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }
                    if(caracteres[contador]==r){
                            estado =3;
                            JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }if(caracteres[contador]==s){
                            estado =3;
                            JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }if(caracteres[contador]==t){
                            estado =3;
                          JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }if(caracteres[contador]==u){
                            estado =3;
                          JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }if(caracteres[contador]==v){
                            estado =3;
                            JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }if(caracteres[contador]==k){
                            estado =3;
                            JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }if(caracteres[contador]==l){
                            estado =3;
                           JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }if(caracteres[contador]==m){
                            estado =3;
                           JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }if(caracteres[contador]==n){
                            estado =3;
                            JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }if(caracteres[contador]==o){
                            estado =3;
                          JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }if(caracteres[contador]==p){
                            estado =3;
                           JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }if(caracteres[contador]==j){
                            estado =3;
                            
                    }if(caracteres[contador]==a){
                            estado =3;
                            
                    }if(caracteres[contador]==b){
                            estado =3;
                            
                    }if(caracteres[contador]==c){
                            estado =3;
                            
                    }if(caracteres[contador]==d){
                            estado =3;
                            
                    }if(caracteres[contador]==e){
                            estado =3;
                            
                    }if(caracteres[contador]==f){
                            estado =3;
                            
                    }if(caracteres[contador]==g){
                            estado =3;
                            
                    }if(caracteres[contador]==h){
                            estado =3;
                            
                    }if(caracteres[contador]==i){
                            estado =3;
                            
                    }else{
                          if(caracteres[contador]==a2){
                            }else if(caracteres[contador]==a3){
                            }else if(caracteres[contador]==a4){
                            }else if(caracteres[contador]==a3){
                            }
                                
                    } 
                contador++;
                continue;       
                }
            case 3:{
                
                  if(caracteres[contador]==q){
                            estado =4;
                            JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }
                    if(caracteres[contador]==q){
                            estado =4;
                           JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }
                    if(caracteres[contador]==r){
                            estado =4;
                            JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }if(caracteres[contador]==s){
                            estado =4;
                            JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }if(caracteres[contador]==t){
                            estado =4;
                          JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }if(caracteres[contador]==u){
                            estado =4;
                          JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }if(caracteres[contador]==v){
                            estado =4;
                            JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }if(caracteres[contador]==k){
                            estado =4;
                            JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }if(caracteres[contador]==l){
                            estado =4;
                           JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }if(caracteres[contador]==m){
                            estado =4;
                           JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }else if(caracteres[contador]==n){
                            estado =4;
                            JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }else if(caracteres[contador]==o){
                            estado =4;
                          JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }else if(caracteres[contador]==p){
                            estado =4;
                           JOptionPane.showMessageDialog(null, "SISTEMA HEXADECIMAL");
                    }if(caracteres[contador]==j){
                            estado =4;
                            
                    }else if(caracteres[contador]==a){
                            estado =3;
                            
                    }else if(caracteres[contador]==b){
                            estado =3;
                            
                    }else if(caracteres[contador]==c){
                            estado =3;
                            
                    }else if(caracteres[contador]==d){
                            estado =3;
                            
                    }else if(caracteres[contador]==e){
                            estado =3;
                            
                    }else if(caracteres[contador]==f){
                            estado =3;
                            
                    }else if(caracteres[contador]==g){
                            estado =3;
                            
                    }else if(caracteres[contador]==h){
                            estado =3;
                            
                    }else if(caracteres[contador]==i){
                            estado =3;
                            
                    }else{
                          if(caracteres[contador]==a2){
                            }else if(caracteres[contador]==a3){
                            }else if(caracteres[contador]==a4){
                            }else if(caracteres[contador]==a3){
                            }
                        
                    } 
                contador++;
                continue;     
            }
            
             case 4:{
               if(caracteres[contador]==a){
                   JOptionPane.showMessageDialog(null, "Cerradura de Klenne");
                            }
                    else{
                         if(caracteres[contador]==a2){
                            }else if(caracteres[contador]==a3){
                            }else if(caracteres[contador]==a4){
                            }else if(caracteres[contador]==a3){
                            }
                              
                    } 
                contador++;
                continue;
                }
             case 5:{
                 if(caracteres[contador]==j){
                    estado =5;
                    }else if(caracteres[contador]==a){
                            estado =5;
                          
                    }else if (caracteres[contador]==b){
                            estado =5;
                          
                    }else if (caracteres[contador]==c){
                         estado =5;
                         
                    }else if (caracteres[contador]==d){
                         estado =5;
                         
                    }else if (caracteres[contador]==e){
                         estado =5;

                    }else if (caracteres[contador]==f){
                         estado =5;

                    }else if (caracteres[contador]==g){
                         estado =5;

                    }else if (caracteres[contador]==h){
                         estado =5;

                    }else if (caracteres[contador]==i){
                         estado =5;
              
                    }else if (caracteres[contador]==y){
                         estado =6;
              
                    }else{
                         if(caracteres[contador]==m){
                            }else if(caracteres[contador]==l){
                            }else if(caracteres[contador]==n){
                            }else if(caracteres[contador]==k){
                            }
                                
                    }
                  contador++;
                continue;
             }
             case 6:{
                  if(caracteres[contador]==j){
                    estado =7;
                    }else if(caracteres[contador]==a){
                            estado =7;
                          
                    }else if (caracteres[contador]==b){
                            estado =7;
                          
                    }else if (caracteres[contador]==c){
                         estado =7;
                         
                    }else if (caracteres[contador]==d){
                         estado =7;
                         
                    }else if (caracteres[contador]==e){
                         estado =7;

                    }else if (caracteres[contador]==f){
                         estado =7;

                    }else if (caracteres[contador]==g){
                         estado =7;

                    }else if (caracteres[contador]==h){
                         estado =7;

                    }else if (caracteres[contador]==i){
                         estado =7;
              
                    }else{
                         if(caracteres[contador]==m){
                            }else if(caracteres[contador]==l){
                            }else if(caracteres[contador]==n){
                            }else if(caracteres[contador]==k){
                            }
                                
                    }
                  contador++;
                continue;
             }
             case 7:{
                   
                   }
             case 8:{
                   
                   }
                }
        if(estado ==finalizar){
            JOptionPane.showMessageDialog(null, "el numero es correcto"); 
            }else{
              JOptionPane.showMessageDialog(null, "el numero es correcto"); 
            }
        } 
    }                         
    }//GEN-LAST:event_btn_hexActionPerformed

    private void btn_exprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exprActionPerformed
        // TODO add your handling code here:
        formulario form = new formulario();
        form.setVisible(true);
    }//GEN-LAST:event_btn_exprActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_abrirarchivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_abrirarchivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_abrirarchivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_abrirarchivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(frm_abrirarchivos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(frm_abrirarchivos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(frm_abrirarchivos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(frm_abrirarchivos.class.getName()).log(Level.SEVERE, null, ex);
                }
                new frm_abrirarchivos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_abrirArchivo;
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_expr;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_guardar1;
    private javax.swing.JButton btn_guardar_como;
    private javax.swing.JButton btn_hex;
    private javax.swing.JButton btn_minimizar;
    private javax.swing.JButton btn_numero;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    public static javax.swing.JLabel lbl_nombre;
    private javax.swing.JTextPane txt_area;
    // End of variables declaration//GEN-END:variables
}
