/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.mycompany.transur.system.Cconection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class CylinderController {
    
    public static void addCylinder(String type, int serial, String state, int number){
        Cconection conect = new Cconection();
        
        String sql = "INSERT INTO cylinder (type,serial,state,number) VALUES (?,?,?,?)";
        PreparedStatement ps = null;
        
        try {
            ps = conect.conect().prepareStatement(sql);
            ps.setString(1,type);
            ps.setInt(2,serial);
            ps.setString(3, state);
            ps.setInt(4, number);
            
            ps.execute();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error", "Hubo un error al resgistrar e cilindro: " + e.toString(), JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
            if (ps != null) ps.close();
            conect.closeConnection();
        } catch (Exception e) {
            System.err.println("Error cerrando recursos: " + e.getMessage());
        }
        }
    }
    
    public static String getCylinderNumber(String id_cylinder) {
    Cconection conect = new Cconection();
    String sql = "SELECT number FROM cylinder WHERE id_cylinder = ?";
    PreparedStatement ps = null;
    ResultSet rs = null;
    String cylinderNumber = null;

    try {
        ps = conect.conect().prepareStatement(sql);
        ps.setString(1, id_cylinder);  
        rs = ps.executeQuery();

        if (rs.next()) {
            cylinderNumber = rs.getString("number");  
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al obtener el cilindro: " + e.toString());
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            conect.closeConnection();
        } catch (Exception e) {
            System.err.println("Error cerrando recursos: " + e.getMessage());
        }
    }

    return cylinderNumber;
}

    public static String getCylinderId(String number) {
    Cconection conect = new Cconection();
    String sql = "SELECT id_cylinder FROM cylinder WHERE number = ?";
    PreparedStatement ps = null;
    ResultSet rs = null;
    String cylinderNumber = null;

    try {
        ps = conect.conect().prepareStatement(sql);
        ps.setString(1, number);  
        rs = ps.executeQuery();

        if (rs.next()) {
            cylinderNumber = rs.getString("id_cylinder");  
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al obtener el cilindro: " + e.toString());
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            conect.closeConnection();
        } catch (Exception e) {
            System.err.println("Error cerrando recursos: " + e.getMessage());
        }
    }

    return cylinderNumber;
}
}
