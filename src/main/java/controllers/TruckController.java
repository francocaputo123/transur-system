/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.mycompany.transur.system.Cconection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class TruckController {
    
    public static void addTruck(String patentField, int internalField) {
       Cconection conect = new Cconection();
       
       
       String sql = "INSERT INTO truck(patent, internal) VALUES (?,?)";
       PreparedStatement ps = null;
       
        try {
            ps = conect.conect().prepareStatement(sql);
            ps.setString(1, patentField);
            ps.setInt(2,internalField);
            
            ps.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Fallo al insertar a la base de datos" + e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        } finally{
            try {
            if (ps != null) ps.close();
            conect.closeConnection();
        } catch (Exception e) {
            System.err.println("Error cerrando recursos: " + e.getMessage());
        }
        }
       //model.add
    }
    
    public static void modifyTruck(){
        
    }
}
