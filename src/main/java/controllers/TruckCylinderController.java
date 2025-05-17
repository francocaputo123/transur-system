/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.mycompany.transur.system.Cconection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class TruckCylinderController {
    
    public static void addCylinderForTruck(int id_truck, int id_cylinder, String position) {
        
        Cconection conect = new Cconection();
        String asignation_date = todayDate();
        
        String sql = "INSERT INTO truck_cylinder(id_truck, id_cylinder, active, asignation_date, position) VALUES(?,?,?,?,?)";
        PreparedStatement ps = null;
        
        try {
            ps = conect.conect().prepareStatement(sql);
            
            ps.setInt(1,id_truck);
            ps.setInt(2,id_cylinder);
            ps.setInt(3,1);
            ps.setString(4,asignation_date);
            ps.setString(5,position);
            ps.executeUpdate();
            
            CylinderHistoryController.addNewHistory(conect, id_truck, id_cylinder, position, asignation_date);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Fallo al insertar a la base de datos" + e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
            if (ps != null) ps.close();
            conect.closeConnection();
        } catch (Exception e) {
            System.err.println("Error cerrando recursos: " + e.getMessage());
        }
        }
    }
    
    public static Boolean verifyCylinderIsActive(int id_cylinder){
        
        Cconection conect = new Cconection();
        
        String sql = "SELECT active FROM truck_cylinder WHERE id_cylinder = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conect.conect().prepareStatement(sql);
            ps.setInt(1, id_cylinder);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int isActive = rs.getInt("active");
                return isActive == 1;
            } else {
            // No se encontró el cilindro
            return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error con la base de datos: " + e.toString());
            return false;
        } finally{
            try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            conect.closeConnection();
        } catch (Exception e) {
            System.err.println("Error cerrando recursos: " + e.getMessage());
        }
        }
    }
    
    public static Boolean verifyCylinderSideIsOcupped(int id_truck, String position) {
        Cconection conect = new Cconection();

        String sql = "SELECT COUNT(*) as total FROM truck_cylinder WHERE id_truck = ? AND position = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conect.conect().prepareStatement(sql);
            ps.setInt(1, id_truck);
            ps.setString(2, position);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                int count = rs.getInt("total");
                return count > 0;
            } 
                    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error en la verificacion de cilindro: " + e.toString());
        
        } finally{
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            conect.closeConnection();
        } catch (Exception e) {
            System.err.println("Error cerrando recursos: " + e.getMessage());
        }
        }
        
        return false;
    }
    
    public static String todayDate() {
       
        LocalDate today = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatToday = today.format(format);
        
        return formatToday;
    }
}
