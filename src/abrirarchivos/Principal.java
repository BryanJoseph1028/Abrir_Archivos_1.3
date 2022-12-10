/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abrirarchivos;

import java.io.File;

/**
 *
 * @author Charly Ponce
 */
public class Principal {
    public static void main(String[] args) {
        String ruta = "C:/Users/24DD018LA/Documents/NetBeansProjects/AbrirArchivos_1.2/src/abrirarchivos/Lexer.flex";
        generarLexer(ruta);
    }
    public static void generarLexer(String ruta){
        File archivo = new File(ruta);
        JFlex.Main.generate(archivo);
    }
}
