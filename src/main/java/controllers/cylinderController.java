/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.mycompany.transur.system.Cconection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class CylinderController {
    
    public static void addCylinder(String type, int serial, String state, int number){
        Cconection conect = new Cconection();
        
        String sql = "INSERT INTO cylinder (type,serial,state,number) VALUES (?,?,?,?)";
        
        try {
            PreparedStatement ps = conect.conect().prepareStatement(sql);
            ps.setString(1,type);
            ps.setInt(2,serial);
            ps.setString(3, state);
            ps.setInt(4, number);
            
            ps.execute();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error", "Hubo un error al resgistrar e cilindro: " + e.toString(), JOptionPane.ERROR_MESSAGE);
        }
        
        finally {
            conect.closeConnection();
        }
    }
}
