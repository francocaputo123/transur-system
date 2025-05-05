/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.transur.system;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Cconection {
    
    Connection conect = null;
    
    String bd = "Transur.db"; 
    String string = "jdbc:sqlite:"+ System.getProperty("user.dir")+ "/"+ bd;
    
    public Connection conect() {
        
        Connection conect = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            conect = DriverManager.getConnection(string);
            //JOptionPane.showMessageDialog(null, "Se conecto correctamente");
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "No se conecto: " + e.toString());
        }
        
        return conect;
    }
    
    public void closeConnection(){
        try {
            if(conect != null){
                conect.close();
                JOptionPane.showMessageDialog(null, "Se cerro la conection correctamente");
            }
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se cerro la conection correctamente");

        }
    }
}
